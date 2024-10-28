package jogoCataFrutas;

import utilitarios.Transmissor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import elementos.Jogador;
import elementos.Terreno;

import frutas.*;

/**
 * Representa o jogo Cata Frutas.
 * Esta classe gerencia os jogadores, o terreno e o estado do jogo.
 */

public class Jogo{
    private int contadorTurno = 0;

    protected Terreno floresta = new Terreno();
    Jogador jogadorDaVez = floresta.getJogador1();
    Jogador outroJogador = floresta.getJogador2();

    /**
     * Construtor da classe Jogo.
     * Inicializa um novo jogo com um terreno e dois jogadores.
     */

    public Jogo() {
        floresta.gerarTerreno();
        floresta.imprimirTerreno();

        configurarListeners();
    }
    
    private void configurarListeners() {
        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("pedirMovJogador")) {
                    movimentarJogador((String) evt.getNewValue());
                }
            }
        });

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        });

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("iniciarPartida")) {
                    jogadorDaVez.gerarPontos(true);
                }
            }
        });
    }

    public void movimentarJogador(String entrada) {
        try {
            switch (entrada) {
                case "W":
                    jogadorDaVez.moverCima();
                    break;
                case "A":
                    jogadorDaVez.moverEsquerda();
                    break;
                case "S":
                    jogadorDaVez.moverBaixo();
                    break;
                case "D":
                    jogadorDaVez.moverDireita();
                    break;
                case "F":
                    jogadorDaVez.catarFruta();
                    break;
                case "P":
                    passarTurno();
                    break;
                case "1":
                    jogadorDaVez.comerFruta(Laranja.class);
                    break;
                case "2":
                    jogadorDaVez.comerFruta(Generica.class);
                    break;
                case "3":
                    jogadorDaVez.comerFruta(Coco.class);
                    break;
                case "4":
                    jogadorDaVez.comerFruta(Abacate.class);
                    break;
            }
        }
        catch (Exception _) {

        }
    }

    public void passarTurno() {
        try {
            jogadorDaVez.pegarFrutaArvore();
        }
        catch (Exception e) {
            System.out.println("" + e);
        }

        jogadorDaVez.resetarMovimento();
        jogadorDaVez.setPontosMovimento(0);
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
            contadorTurno++;
        }

        jogadorDaVez.gerarPontos(false);
        contadorTurno++;

        if(((contadorTurno) / 2 + 1) % 2 == 0) {
            try {
                floresta.spawnarMaracuja();
            }
            catch (Exception _) {

            }
        }

        Transmissor.avisoMudarRodada(((contadorTurno) / 2 + 1));

        if(jogadorDaVez.getPontosOuro() > floresta.getTotalMaracujas() / 2) {
       		Transmissor.avisoFimDeJogo(jogadorDaVez.getNome());
        }
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

        Transmissor.avisoTrocaJogador(outroJogador.getID(), jogadorDaVez.getID());
    }
}

    /**
     * Inicia o jogo.
     */

    /*public void iniciarPartida() {
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
    }*/

