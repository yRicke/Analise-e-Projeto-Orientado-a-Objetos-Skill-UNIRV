import java.awt.geom.Point2D;
import java.nio.file.Path;
import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.*;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.*;

/** Modelo original do cenário de agendamento, construído pela API nativa do Astah. */
class GerarSequencia {
    static SequenceDiagramEditor editor;
    static java.util.Map<IPresentation, Double> instantes = new java.util.LinkedHashMap<>();
    static java.util.Map<INodePresentation, Double> finais = new java.util.HashMap<>();
    static java.util.Map<Integer, java.util.List<INodePresentation>> execucoes = new java.util.HashMap<>();
    static INodePresentation[] participantes = new INodePresentation[7];

    static ILinkPresentation mensagem(int origem, int destino, String nome, double y) throws Exception {
        int inicio = nome.indexOf('(');
        String operacao = inicio < 0 ? nome : nome.substring(0, inicio);
        String argumentos = inicio < 0 ? "" : nome.substring(inicio + 1, nome.length() - 1);
        ILinkPresentation seta = editor.createMessage(operacao, origemAtiva(origem, y), participantes[destino], y);
        ((IMessage)seta.getModel()).setArgument(argumentos);
        instantes.put(seta, y);
        execucoes.get(origem).add(seta.getSource());
        execucoes.get(destino).add(seta.getTarget());
        return seta;
    }
    // Reutiliza a execução emissora em vez de criar barras isoladas para cada chamada.
    static INodePresentation origemAtiva(int indice, double y) {
        for (int i = execucoes.get(indice).size() - 1; i >= 0; i--) {
            INodePresentation barra = execucoes.get(indice).get(i);
            var r = barra.getRectangle();
            if (r.getY() <= y && finais.getOrDefault(barra, r.getMaxY()) > y) return barra;
        }
        return participantes[indice];
    }
    static ILinkPresentation executar(ILinkPresentation chamada, double fim) throws Exception {
        var barra = chamada.getTarget();
        finais.put(barra, fim);
        return chamada;
    }
    static void retorno(ILinkPresentation chamada, String nome) throws Exception {
        var resposta = editor.createReturnMessage(nome, chamada);
        instantes.put(resposta, instantes.get(chamada) + 25);
    }
    static IPresentation[] trecho(double inicio, double fim) {
        return instantes.entrySet().stream().filter(e -> e.getValue() >= inicio && e.getValue() < fim)
            .map(java.util.Map.Entry::getKey).toArray(IPresentation[]::new);
    }
    static INodePresentation quadro(String operador, String guarda, IPresentation[] mensagens, int... vidas) throws Exception {
        java.util.List<IPresentation> alvos = new java.util.ArrayList<>(java.util.Arrays.asList(mensagens));
        for (int vida : vidas) alvos.add(participantes[vida]);
        var resultado = editor.createCombinedFragment(operador, alvos.toArray(IPresentation[]::new));
        ((ICombinedFragment)resultado.getModel()).getInteractionOperands()[0].setGuard(guarda);
        return resultado;
    }
    public static void main(String[] args) throws Exception {
        var projeto = AstahAPI.getAstahAPI().getProjectAccessor();
        try {
            projeto.create(Path.of(args[0]).toAbsolutePath().toString());
            TransactionManager.beginTransaction();
            IModel raiz = projeto.getProject();
            raiz.setName("Clínica Bem Estar");
            BasicModelEditor modelos = ModelEditorFactory.getBasicModelEditor();
            editor = projeto.getDiagramEditorFactory().getSequenceDiagramEditor();
            ISequenceDiagram diagrama = editor.createSequenceDiagram(raiz, "Agendar consulta");
            String[] instancias = {"", "tela", "controle", "cadastro", "corpoClinico", "agenda", "sms"};
            String[] classes = {"Recepcionista", "TelaAgendamento", "ControleAgendamento",
                "CadastroPacientes", "CorpoClinico", "AgendaConsultas", "ServicoSMS"};
            String[] estereotipos = {"", "boundary", "control", "entity", "entity", "entity", ""};
            double[] x = {80, 440, 880, 1330, 1690, 2070, 2400};
            for (int i=0; i<classes.length; i++) {
                IClass tipo = i==0 ? ModelEditorFactory.getUseCaseModelEditor().createActor(raiz, classes[i])
                    : modelos.createClass(raiz, classes[i]);
                if (!estereotipos[i].isEmpty()) tipo.addStereotype(estereotipos[i]);
                execucoes.put(i, new java.util.ArrayList<>());
                participantes[i] = editor.createLifeline(instancias[i], x[i]);
                ((ILifeline)participantes[i].getModel()).setBase(tipo);
                participantes[i].setProperty("lifeline_length", "2400");
            }
            executar(mensagem(0,1,"informarCPF(cpf)",140), 790);
            executar(mensagem(1,2,"identificarPaciente(cpf)",200), 780);
            retorno(mensagem(2,3,"buscarPorCPF(cpf)",260),"paciente ou null");
            executar(mensagem(2,1,"solicitarDadosPaciente()",405), 500);
            mensagem(1,0,"exibirFormularioCadastro()",465);
            executar(mensagem(0,1,"informarDados(nome, telefone, dataNascimento)",525), 745);
            executar(mensagem(1,2,"cadastrarPaciente(cpf, nome, telefone, dataNascimento)",585), 735);
            retorno(mensagem(2,3,"registrarPaciente(cpf, nome, telefone, dataNascimento)",645), "paciente registrado");
            executar(mensagem(0,1,"selecionarEspecialidade(especialidade)",805), 1140);
            executar(mensagem(1,2,"listarMedicos(especialidade)",865), 1130);
            retorno(mensagem(2,4,"buscarPorEspecialidade(especialidade)",925),"todos os medicos da especialidade, com seus nomes");
            executar(mensagem(2,1,"apresentarMedicos(medicos)",1030), 1120);
            mensagem(1,0,"exibirNomesDosMedicos(medicos)",1090);
            mensagem(0,1,"selecionarMedico(medico)",1150);
            executar(mensagem(0,1,"informarDataHorario(data, horario)",1210), 2260);
            executar(mensagem(1,2,"agendarConsulta(paciente, medico, data, horario)",1270), 2240);
            retorno(mensagem(2,5,"verificarDisponibilidade(medico, data, horario)",1330),"disponivel : boolean");
            retorno(mensagem(2,5,"registrarConsulta(paciente, medico, data, horario)",1485), "consulta registrada");
            executar(mensagem(2,1,"confirmarAgendamento(consulta)",1590), 1690);
            mensagem(1,0,"exibirConfirmacao(paciente, medico, data, horario)",1650);
            ILinkPresentation envio = mensagem(2,6,"enviarLembreteSMS(paciente.telefone, consulta)",1820);
            ((IMessage)envio.getModel()).setAsynchronous(true);
            executar(mensagem(2,1,"informarHorarioOcupado()",2020), 2120);
            mensagem(1,0,"exibirHorarioOcupado()",2080);
            quadro("opt", "paciente == null", trecho(300,760), 0,1,2,3);
            INodePresentation lembrete = quadro("opt", "consulta registrada e paciente.autorizouSMS", trecho(1710,1890), 2,6);
            var ramos = new java.util.ArrayList<IPresentation>(java.util.Arrays.asList(trecho(1410,2210)));
            ramos.removeAll(java.util.Arrays.asList(trecho(1710,1890)));
            ramos.add(lembrete);
            INodePresentation alternativas = quadro("alt", "disponivel", ramos.toArray(IPresentation[]::new), 0,1,2,5,6);
            editor.addInteractionOperand(alternativas, trecho(1950,2210), "", "else");
            var sucesso = new java.util.ArrayList<IPresentation>(java.util.Arrays.asList(trecho(1410,1710)));
            sucesso.add(lembrete);
            editor.setInteractionOperandTargets(alternativas, new IPresentation[][] {sucesso.toArray(IPresentation[]::new), trecho(1950,2210)});
            editor.createNote("SMS assíncrono: a recepcionista não aguarda o envio.\nA autorização é uma informação do paciente;\na coleta desse consentimento não é descrita no cenário.",new Point2D.Double(1730,2280));
            TransactionManager.endTransaction();
            projeto.save();
            projeto.close();
            System.out.println("Arquivo Astah salvo: " + args[0]);
        } catch (Exception erro) {
            TransactionManager.abortTransaction();
            try (var log = new java.io.PrintWriter("tmp/erro-modelagem.txt", java.nio.charset.StandardCharsets.UTF_8)) {
                erro.printStackTrace(log);
            }
            throw erro;
        }
        System.exit(0);
    }
}
