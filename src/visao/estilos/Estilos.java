package visao.estilos;
import visao.VisaoPrincipal;

public abstract class Estilos {

    public static void visaoPrincipal(VisaoPrincipal principal) {
        System.out.println(principal.getGerenciador().getNomeTelaAtual());
        switch (principal.getGerenciador().getNomeTelaAtual()){
            case "INICIO":{
                principal.setBounds(0, 0, 1280, 740);
                break;
            }
            case "OPCOES INICIAIS":{
                principal.setBounds(0, 0, 800, 490);
                break;
            }

            default:{
                principal.setBounds(0, 0, 800, 300);
                break;
            }
        }


    }
}
