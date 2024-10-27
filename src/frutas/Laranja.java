package frutas;

import elementos.Jogador;
import utilitarios.Transmissor;

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
     */

    public Laranja(String nome) {
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
    		jogador.setNerfBichada(false);
    		Transmissor.avisoEfeito(jogador, 3);
    		return true;
    	}
        return false;
    }
}
