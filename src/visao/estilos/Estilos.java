package visao.estilos;
import visao.VisaoNovoJogo;
import visao.VisaoPrincipal;

import javax.swing.*;

public abstract class Estilos {

    public static void visaoPrincipal(VisaoPrincipal principal) {
        System.out.println(principal.getGerenciador().getNomeTelaAtual());
        switch (principal.getGerenciador().getNomeTelaAtual()){
            case "INICIO":{
                principal.setBounds(0, 0, 800, 300);
                break;
            }
            case "NOVO JOGO":{
                principal.setBounds(0, 0, 1280, 720);
                break;
            }

            default:{
                principal.setBounds(0, 0, 800, 300);
                break;
            }
        }

        principal.setResizable(false);
        principal.setLocationRelativeTo(null);
    }

    public static void visaoNovoJogo(VisaoNovoJogo tela) {
        tela.setBounds(0, 0, 1280, 720);

    }
}
