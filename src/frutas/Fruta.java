package frutas;

import java.util.Random;

import elementos.Elemento;
import elementos.Jogador;
import jogoCataFrutas.Configuracoes;
import sons.EventoSonoroHandler;
import utilitarios.GerenciadorArquivo;
import utilitarios.Transmissor;

/**
 * Esta classe abstrata representa uma fruta, que é um tipo de Elemento.
 */

public abstract class Fruta {
    protected boolean bichada = false;
    protected String nome;

    public Fruta() {
        Random gerador = new Random();

        if(gerador.nextInt(100) + 1 <= Configuracoes.chanceFrutaBichada) {
            bichada = true;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setBichada(boolean bichada) {
    	this.bichada = bichada;
    }

    /**
     * Verifica se a fruta está bichada.
     *
     * @return True se a fruta está bichada, false caso contrário.
     */

    public boolean isBichada() {
        return bichada;
    }

    /**
     * Aplica um buff ao jogador ao consumir a fruta.
     *
     * @param jogador O jogador que receberá o buff.
     * @return True se o buff foi aplicado com sucesso, false caso contrário.
     */

    public abstract boolean buffar(Jogador jogador);

    /**
     * Aplica um nerf ao jogador.
     *
     * @param jogador O jogador que receberá o nerf.
     * @return True se o nerf foi aplicado com sucesso, false caso contrário.
     */

    public void nerfar(Jogador jogador) {
        jogador.setNerfBichada(bichada);
    }
}
