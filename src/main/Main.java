package main;
import visao.*;
import jogoCataFrutas.*;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        Jogo novoJogo = new Jogo();
        final int SIZE = 800;

        boolean conseguiuGerar = novoJogo.getFloresta().gerarTerreno();
        if (!conseguiuGerar) {
            JOptionPane.showMessageDialog(null, "As especificações informadas no arquivo de configuração não permitem uma criação consistente do mapa.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;

        }


        VisaoPrincipal visaoPrincipal = new VisaoPrincipal(SIZE);
        VisaoTerreno visaoTerreno = new VisaoTerreno(novoJogo.getFloresta(), SIZE);


        visaoPrincipal.add(visaoTerreno);
        visaoPrincipal.setVisible(true);
        visaoPrincipal.setLocationRelativeTo(null);


    }

}
