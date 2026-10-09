import java.awt.geom.Point2D;
import java.nio.file.Path;
import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.*;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.*;

/** Modelo original do cenário de agendamento, construído pela API nativa do Astah. */
class GerarSequencia {
    static SequenceDiagramEditor editor;
    static INodePresentation[] participantes = new INodePresentation[7];

    static ILinkPresentation mensagem(int origem, int destino, String nome, double y) throws Exception {
        int inicio = nome.indexOf('(');
        String operacao = inicio < 0 ? nome : nome.substring(0, inicio);
        String argumentos = inicio < 0 ? "" : nome.substring(inicio + 1, nome.length() - 1);
        ILinkPresentation seta = editor.createMessage(operacao, participantes[origem], participantes[destino], y);
        ((IMessage)seta.getModel()).setArgument(argumentos);
        return seta;
    }
    static void retorno(ILinkPresentation chamada, String nome) throws Exception {
        editor.createReturnMessage(nome, chamada);
    }
    static INodePresentation fragmento(String operador, String guarda, double x, double y,
                                      double largura, double altura) throws Exception {
        INodePresentation quadro = editor.createCombinedFragment("", operador,
            new Point2D.Double(x, y), largura, altura);
        ((ICombinedFragment) quadro.getModel()).getInteractionOperands()[0].setGuard(guarda);
        quadro.setProperty("auto_resize", "false");
        return quadro;
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
                participantes[i] = editor.createLifeline(instancias[i], x[i]);
                ((ILifeline)participantes[i].getModel()).setBase(tipo);
                participantes[i].setProperty("lifeline_length", "2400");
            }
            mensagem(0,1,"informarCPF(cpf)",140);
            mensagem(1,2,"identificarPaciente(cpf)",200);
            retorno(mensagem(2,3,"buscarPorCPF(cpf)",260),"paciente ou null");
            fragmento("opt", "paciente == null",20,300,1510,380);
            mensagem(2,1,"solicitarDadosPaciente()",405);
            mensagem(1,0,"exibirFormularioCadastro()",465);
            mensagem(0,1,"informarDados(nome, telefone, dataNascimento)",525);
            mensagem(1,2,"cadastrarPaciente(cpf, nome, telefone, dataNascimento)",585);
            ((IMessage)mensagem(2,3,"registrarPaciente(cpf, nome, telefone, dataNascimento)",645).getModel()).setReturnValue("paciente registrado");
            mensagem(0,1,"selecionarEspecialidade(especialidade)",805);
            mensagem(1,2,"listarMedicos(especialidade)",865);
            retorno(mensagem(2,4,"buscarPorEspecialidade(especialidade)",925),"medicos com seus nomes");
            mensagem(2,1,"apresentarMedicos(medicos)",1030);
            mensagem(1,0,"exibirNomesDosMedicos(medicos)",1090);
            mensagem(0,1,"selecionarMedico(medico)",1150);
            mensagem(0,1,"informarDataHorario(data, horario)",1210);
            mensagem(1,2,"agendarConsulta(paciente, medico, data, horario)",1270);
            retorno(mensagem(2,5,"verificarDisponibilidade(medico, data, horario)",1330),"disponivel : boolean");
            INodePresentation alternativas = fragmento("alt","disponivel",20,1410,2600,800);
            ((ICombinedFragment)alternativas.getModel()).addInteractionOperand("","else");
            alternativas.setProperty("operand.1.length", "540");
            ((IMessage)mensagem(2,5,"registrarConsulta(paciente, medico, data, horario)",1485).getModel()).setReturnValue("consulta registrada");
            mensagem(2,1,"confirmarAgendamento(consulta)",1590);
            mensagem(1,0,"exibirConfirmacao(paciente, medico, data, horario)",1650);
            fragmento("opt","consulta registrada e paciente.autorizouSMS",800,1710,1780,180);
            ILinkPresentation envio = mensagem(2,6,"enviarLembreteSMS(paciente.telefone, consulta)",1820);
            ((IMessage)envio.getModel()).setAsynchronous(true);
            mensagem(2,1,"informarHorarioOcupado()",2020);
            mensagem(1,0,"exibirHorarioOcupado()",2080);
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
