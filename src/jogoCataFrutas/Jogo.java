package jogoCataFrutas;

import java.util.Scanner;

import elementos.Jogador;
import elementos.Terreno;

import frutas.*;

/**
 * Representa o jogo Cata Frutas.
 * Esta classe gerencia os jogadores, o terreno e o estado do jogo.
 */

public class Jogo {
    private int contadorTurno = 0;
    private String estado;

    protected Terreno floresta = new Terreno();

    Jogador jogadorDaVez = floresta.getJogador1();
    Jogador outroJogador = floresta.getJogador2();
    


    /**
     * Construtor da classe Jogo.
     * Inicializa um novo jogo com um terreno e dois jogadores.
     */

    public Jogo() {
    	estado = "NoMenuInicial";
    }
    
    public int getcontadorTurno() {
		return contadorTurno;
	}

    public void contarTurno() {
        contadorTurno++;
    }

    /**
     * Obtém o estado atual do jogo.
     *
     * @return uma String representando o estado do jogo.
     */

    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado do jogo.
     *
     * @param estado uma String representando o novo estado do jogo.
     */

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtém o terreno do jogo.
     *
     * @return o objeto Terreno que representa a floresta do jogo.
     */

    public Terreno getFloresta() {
        return this.floresta;
    }

    private void trocarJogadores() {
        if (jogadorDaVez == floresta.getJogador1()) {
            jogadorDaVez = floresta.getJogador2();
            outroJogador = floresta.getJogador1();
        } else {
            jogadorDaVez = floresta.getJogador1();
            outroJogador = floresta.getJogador2();
        }
    }
    

    
    private char pegarInput() {
    	char input;
    	
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Comando: ");
        input = scanner.next().charAt(0);
        
        return input;
    }

    private Class<? extends Fruta>  pegarInputFruta() {
        char input;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Fruta: ");
        input = scanner.next().charAt(0);

        return switch (input) {
            case 'a' -> Abacate.class;
            case 'l' -> Laranja.class;
            case 'g' -> Generica.class;
            case 'c' -> Coco.class;
            default -> null;
        };
    }

    /**
     * Inicia o jogo.
     */

    public void iniciarPartida() {
    	estado = "EmPartida";
        
        while(estado.equals("EmPartida")) {
            contarTurno();
        	jogadorDaVez.gerarPontos();
            jogadorDaVez.resetarMovimento();
        	
        	System.out.println(
                    "Rodada: "
                            + (contadorTurno / 2 + 1) + " - "
                            + jogadorDaVez.getNome() + ": "
                            + jogadorDaVez.getPontosMovimento()
            );
        	
           	while(jogadorDaVez.getPontosMovimento() != 0) {
               try {
                   switch (pegarInput()) {
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
                           jogadorDaVez.pegarFrutaArvore();
                           break;
                       case 'f':
                           jogadorDaVez.catarFruta();
                           break;
                       case 'c':
                           System.out.println("Abacates: " + jogadorDaVez.abrirMochila().getQuantAbacates());
                           System.out.println("Laranjas: " + jogadorDaVez.abrirMochila().getQuantLaranjas());
                           System.out.println("Genericas: " + jogadorDaVez.abrirMochila().getQuantGenericas());
                           System.out.println("Cocos: " + jogadorDaVez.abrirMochila().getQuantCocos());
                           jogadorDaVez.comerFruta(pegarInputFruta());
                           break;
                   }


               } catch (Exception e) {
                   System.out.println(e.getMessage());
               }
            }

            jogadorDaVez.atualizarCooldowns();
            jogadorDaVez.setBuffForca(false);
            jogadorDaVez.setJaFoiEmpurrado(false);

            if(!outroJogador.getNerfBichada()) {
                trocarJogadores();
            }
            else {
                outroJogador.setNerfBichada(false);
                //  Isso tem que ser feito pra garantir que o cooldown do jogador paralizado não paralize junto dele
                outroJogador.atualizarCooldowns();
                contarTurno();
            }

            try {
                if(((contadorTurno) / 2 + 1) % 2 == 0) {
                    floresta.spawnarMaracuja();
                }
            }
            catch (Exception _) {}

           	// Vendo se o jogador ganhou
           	if(jogadorDaVez.getPontosOuro() > floresta.getTotalMaracujas() / 2) {
           		estado = "Vitoria" + jogadorDaVez.getNome();
           	}
        }
    }
}
