package frutas;

import elementos.Jogador;

/**
 * Esta classe representa a fruta Abacate, que é um tipo específico de Fruta.
 */

public class Abacate extends Fruta {

    /**
     * Construtor padrão da classe Abacate.
     */

    public Abacate() {

    }

    /**
     * Construtor da classe Abacate que define o nome e a posição da fruta.
     *
     * @param nome     O nome da fruta Abacate.
     */

    public Abacate(String nome) {
        super();
        
        this.nome = nome;
    }

    /**
     * Aplica um buff ao jogador ao consumir a fruta.
     *
     * @param jogador O jogador que receberá o buff.
     * @return True se o buff foi aplicado com sucesso, false caso contrário.
     */

    @Override
    public boolean buffar(Jogador jogador) {
    	if (jogador != null) {
    		jogador.setBuffForca(true);
    		return true;
    	}
        return false;
    }
}
