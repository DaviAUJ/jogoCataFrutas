package frutas;

import elementos.Jogador;

/**
 * Esta classe representa a fruta Laranja, que é um tipo específico de Fruta.
 */

public class Laranja extends Fruta {

    /**
     * Construtor padrão da classe Laranja.
     */

    public Laranja() {

    }

    /**
     * Construtor da classe Laranja que define o nome e a posição da fruta.
     *
     * @param nome     O nome da fruta Laranja.
     * @param posicaoX A coordenada x da posição da fruta.
     * @param posicaoY A coordenada y da posição da fruta.
     */

    public Laranja(String nome, int posicaoX, int posicaoY) {
        super();
        
        this.nome = nome;
    	this.posicaoX = posicaoX;
    	this.posicaoY = posicaoY;
    }

    /**
     * Aplica um buff ao jogador ao consumir a fruta.
     *
     * @param jogador O jogador que receberá o buff.
     * @return True se o buff foi aplicado com sucesso, false caso contrário.
     */

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
