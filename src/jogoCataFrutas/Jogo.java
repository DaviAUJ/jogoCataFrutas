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
    }
    
    public void configurarListeners() {
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
                if(evt.getPropertyName().equals("pedirPassarRodada")) {
                    passarTurno();
                }
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
        catch (Exception _) {  }

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

    public int getIDJogadorDaVez(){
        return jogadorDaVez.getID();
    }
}
