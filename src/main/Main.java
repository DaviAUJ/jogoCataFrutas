package main;

import elementos.Jogador;
import visao.*;
import jogoCataFrutas.*;

import javax.swing.*;
import java.util.Random;
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

        // Gambiarra temporaria
        char input;
        boolean podeContinuar = false;
        Jogador jogadorDaVez;

        while(true) {
        	novoJogo.contarRodada();

            if(novoJogo.getContadorRodada() % 2 == 0) {
                jogadorDaVez = novoJogo.getFloresta().getJogador2();
            }
            else {
                jogadorDaVez = novoJogo.getFloresta().getJogador1();
            }

            jogadorDaVez.gerarPontos();

            while(jogadorDaVez.getPontosMovimento() != 0) {
                System.out.println(
                        "Rodada: "
                                + (novoJogo.getContadorRodada() + 1) / 2 + " - "
                                + jogadorDaVez.getNome() + ": "
                                + jogadorDaVez.getPontosMovimento()
                );

                visaoTerreno = new VisaoTerreno(novoJogo.getFloresta(), SIZE);

                visaoPrincipal.add(visaoTerreno);
                visaoPrincipal.setVisible(true);

            	Scanner scanner = new Scanner(System.in);
                System.out.println("Comando: ");
                input = scanner.next().charAt(0);

                switch (input) {
                    case 'd':
                        jogadorDaVez.moverDireita();
                        break;
                    case 'a':
                        jogadorDaVez.moverEsquerda();
                        break;
                    case 'w':
                        jogadorDaVez.moverCima();
                        break;
                    case 's':
                        jogadorDaVez.moverBaixo();
                        break;
                    case 'p':
                        jogadorDaVez.setPontosMovimento(0);
                        break;
                    case 'f':
                        jogadorDaVez.catarFruta();
                        break;
                }
            }
        }
    }
}
