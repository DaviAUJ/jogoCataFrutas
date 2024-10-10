package main;
import visao.*;
import jogoCataFrutas.*;

public class Main {


    public static void main(String[] args) {
        Jogo novoJogo = new Jogo();
        final int SIZE = 800;

        novoJogo.getFloresta().gerarTerreno();


        VisaoPrincipal visaoPrincipal = new VisaoPrincipal(SIZE);
        VisaoTerreno visaoTerreno = new VisaoTerreno(novoJogo.getFloresta(), SIZE);


        visaoPrincipal.add(visaoTerreno);
        visaoPrincipal.setVisible(true);
        visaoPrincipal.setLocationRelativeTo(null);

    }

}
