import java.awt.geom.Point2D;
import java.nio.file.Path;
import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.*;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.*;

/** Processo original de exames laboratoriais, com partições e documentos UML. */
class GerarAtividades {
    static ActivityDiagramEditor editor;
    static INodePresentation[] raias = new INodePresentation[5];
    static Point2D ponto(double x, double y) { return new Point2D.Double(x,y); }
    static INodePresentation acao(String nome,double x,double y,double largura) throws Exception {
        INodePresentation n=editor.createAction(nome,ponto(x,y));
        n.setWidth(largura); n.setHeight(56);
        n.setProperty("fill.color","#EDF4FC");
        return n;
    }
    static INodePresentation objeto(String nome,IClass tipo,double x,double y) throws Exception {
        INodePresentation n=editor.createObjectNode(nome,tipo,ponto(x,y));
        n.setWidth(240); n.setHeight(65);
        n.setProperty("fill.color","#FFF5CD");
        return n;
    }
    static ILinkPresentation fluxo(INodePresentation a, INodePresentation b, String guarda,
                                    double... coordenadas) throws Exception {
        ILinkPresentation f=editor.createFlow(a,b);
        f.setProperty("line.shape","line_right_angle");
        if(!guarda.isEmpty()) ((IFlow)f.getModel()).setGuard(guarda);
        if(coordenadas.length>0) {
            Point2D[] pontos=new Point2D[coordenadas.length/2];
            for(int i=0;i<pontos.length;i++) pontos[i]=ponto(coordenadas[i*2],coordenadas[i*2+1]);
            try {f.setAllPoints(pontos);} catch(Exception e) {
                throw new Exception("Origem="+a.getRectangle()+" destino="+b.getRectangle()+" pontos="+java.util.Arrays.toString(f.getAllPoints()),e);
            }
        }
        return f;
    }
    static void fluxo(INodePresentation a,INodePresentation b) throws Exception {fluxo(a,b,"");}
    public static void main(String[] args) throws Exception {
        var projeto=AstahAPI.getAstahAPI().getProjectAccessor();
        try {
            projeto.create(Path.of(args[0]).toAbsolutePath().toString());
            TransactionManager.beginTransaction();
            var raiz=projeto.getProject(); raiz.setName("Clínica Bem Estar — Exames");
            editor=projeto.getDiagramEditorFactory().getActivityDiagramEditor();
            editor.createActivityDiagram(raiz,"Atender exame laboratorial");
            String[] nomes={"Paciente","Recepcionista","Técnico de laboratório","Laboratório","Médico"};
            for(int i=0;i<raias.length;i++) {
                raias[i]=editor.createPartition(null,i==0?null:raias[i-1],nomes[i],false);
                raias[i].setWidth(420); raias[i].setHeight(2280);
            }
            var modelos=ModelEditorFactory.getBasicModelEditor();
            IClass tipoPedido=modelos.createClass(raiz,"PedidoExame"); tipoPedido.addStereotype("documento");
            IClass tipoLaudo=modelos.createClass(raiz,"LaudoExame"); tipoLaudo.addStereotype("documento");
            var inicio=editor.createInitialNode("Início",ponto(200,80));
            var chegada=acao("Chegar e apresentar\npedido de exame",70,125,270);
            var pedido=objeto("pedido",tipoPedido,85,230);
            var conferir=acao("Conferir cobertura do\nconvênio para o exame",490,230,280);
            var cobertura=editor.createDecisionMergeNode(raias[1],ponto(620,350));
            var informar=acao("Informar valor do\nexame particular",490,410,280);
            var escolha=editor.createDecisionMergeNode(raias[0],ponto(200,500));
            var desistir=acao("Desistir do exame",30,590,160);
            var pagar=acao("Pagar exame\nparticular",230,590,160);
            var encerrar=acao("Encerrar atendimento",465,670,230);
            var fimDesistencia=editor.createFinalNode("Fim por desistência",ponto(570,755));
            var liberado=editor.createDecisionMergeNode(raias[1],ponto(740,800));
            var registrar=acao("Registrar atendimento",490,870,280);
            var encaminhar=acao("Encaminhar paciente\npara a coleta",490,960,280);
            var repetir=editor.createDecisionMergeNode(raias[2],ponto(1040,1020));
            var coletar=acao("Coletar amostra",910,1080,280);
            var adequacao=acao("Conferir adequação\nda amostra",910,1180,280);
            var adequada=editor.createDecisionMergeNode(raias[2],ponto(1040,1300));
            var bifurcacao=editor.createForkNode(raias[2],ponto(1000,1390),100,10);
            var agendar=acao("Agendar consulta de\nretorno com o médico",490,1460,280);
            var analisar=acao("Realizar análise\nlaboratorial",1325,1460,280);
            var sincronizacao=editor.createJoinNode(raias[3],ponto(1370,1590),120,10);
            var emitir=acao("Emitir laudo do exame",1325,1650,280);
            var laudo=objeto("laudo",tipoLaudo,1340,1740);
            var dataRetorno=editor.createAcceptTimeEventAction("Dia da consulta\nde retorno",ponto(190,1825));
            dataRetorno.setWidth(40); dataRetorno.setHeight(50);
            var comparecer=acao("Comparecer à consulta\nde retorno",70,1940,280);
            var ler=acao("Analisar laudo\njunto com o paciente",1745,2040,280);
            var fim=editor.createFinalNode("Fim",ponto(1875,2160));

            fluxo(inicio,chegada); fluxo(chegada,pedido); fluxo(pedido,conferir);
            fluxo(conferir,cobertura);
            fluxo(cobertura,liberado,"convênio cobre",650,365,805,365,805,815,770,815);
            var naoCoberto=fluxo(cobertura,informar,"convênio não cobre");
            naoCoberto.setProperty("name.point.x","455.0");
            naoCoberto.setProperty("name.point.y","385.0");
            fluxo(informar,escolha,"",490,438,215,438,215,500);
            fluxo(escolha,desistir,"desistir",200,515,110,515,110,590);
            fluxo(escolha,pagar,"pagar particular",230,515,310,515,310,590);
            fluxo(desistir,encerrar,"",110,646,110,698,465,698);
            fluxo(encerrar,fimDesistencia);
            fluxo(pagar,liberado,"",310,646,310,815,740,815);
            fluxo(liberado,registrar,"",755,810,755,845,630,845,630,870);
            fluxo(registrar,encaminhar);
            fluxo(encaminhar,repetir,"",770,988,1055,988,1055,1020);
            fluxo(repetir,coletar); fluxo(coletar,adequacao); fluxo(adequacao,adequada);
            var recoleta=fluxo(adequada,repetir,"amostra inadequada",1070,1315,1220,1315,1220,1035,1070,1035);
            recoleta.setProperty("name.point.x","1110.0");
            recoleta.setProperty("name.point.y","1340.0");
            fluxo(adequada,bifurcacao,"amostra adequada");
            fluxo(bifurcacao,agendar); fluxo(bifurcacao,analisar);
            fluxo(agendar,sincronizacao); fluxo(analisar,sincronizacao);
            fluxo(sincronizacao,emitir); fluxo(emitir,laudo);
            fluxo(emitir,dataRetorno,"",1325,1678,1280,1678,1280,1850,210,1850);
            fluxo(dataRetorno,comparecer);
            fluxo(comparecer,ler,"",210,1996,210,2068,1745,2068);
            fluxo(laudo,ler,"",1580,1772,1885,1772,1885,2040);
            fluxo(ler,fim);
            editor.createNote("Pedido e laudo: documentos\n(nós de objeto UML).\nA junção exige análise concluída\nE retorno agendado.\nPedido recebido antes da chegada.",ponto(1730,90));
            TransactionManager.endTransaction();
            projeto.validateProject(); projeto.save(); projeto.close();
            System.out.println("Atividades salvas: "+args[0]);
        } catch(Exception erro) {
            TransactionManager.abortTransaction();
            try(var log=new java.io.PrintWriter("tmp/erro-atividades.txt",java.nio.charset.StandardCharsets.UTF_8)){erro.printStackTrace(log);}
            throw erro;
        }
        System.exit(0);
    }
}

