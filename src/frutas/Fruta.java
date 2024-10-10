package frutas;

import java.util.Random;

import elementos.Elemento;
import elementos.Jogador;
import utilitarios.GerenciadorArquivo;

/**
 * Esta classe abstrata representa uma fruta, que é um tipo de Elemento.
 */

public abstract class Fruta extends Elemento {
    private boolean bichada = false;

    /**
     * Construtor padrão da classe Fruta.
     */

    public Fruta() {
    }

    /**
     * Construtor da classe Fruta que define o nome e a posição da fruta.
     * Verifica aleatoriamente se a fruta está bichada com base na configuração.
     *
     * @param nome     O nome da fruta.
     * @param posicaoX A coordenada x da posição da fruta.
     * @param posicaoY A coordenada y da posição da fruta.
     */

    public Fruta(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);

        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
        Random gerador = new Random();

        if (gerador.nextInt(100) + 1 <= arquivo.pegarChanceBichadas()) {
            bichada = true;
        }
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

    public boolean nerfar(Jogador jogador) {
        return false;
    }
}
