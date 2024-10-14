package main;

import elementos.Jogador;
import visao.*;
import jogoCataFrutas.*;

import javax.swing.*;
import java.util.Scanner;

/**
 * Classe principal do jogo Cata Frutas.
 * Esta classe inicializa o jogo e a interface do usuário.
 */

public class Main {

    /**
     * Método principal que inicia a execução do programa.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */

    public static void main(String[] args) {
        Jogo novoJogo = new Jogo();
        Jogador jogador1 = novoJogo.getFloresta().getJogador1();
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

        while(true) {
        	novoJogo.contarRodada();
        	System.out.println("Rodada: " + novoJogo.getContadorRodada());
        	
            char input;
            boolean deuCerto = false;

            // Gambiarra temporaria
            visaoTerreno = new VisaoTerreno(novoJogo.getFloresta(), SIZE);

            visaoPrincipal.add(visaoTerreno);
            visaoPrincipal.setVisible(true);
            
            while(!deuCerto) {
            	Scanner scanner = new Scanner(System.in);
                System.out.println("Comando: ");
                input = scanner.next().charAt(0);

                switch (input) {
                    case 'd':
                        deuCerto = jogador1.moverDireita();
                        break;
                    case 'a':
                        deuCerto = jogador1.moverEsquerda();
                        break;
                    case 'w':
                        deuCerto = jogador1.moverCima();
                        break;
                    case 's':
                        deuCerto = jogador1.moverBaixo();
                        break;
                }
            }
        }
    }
}
