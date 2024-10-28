package frutas;

import elementos.Jogador;
import sons.EventoSonoroHandler;

/**
 * Esta classe representa a fruta Maracujá, que é um tipo específico de Fruta.
 */

public class Maracuja extends Fruta {

    /**
     * Construtor padrão da classe Maracuja.
     */

    public Maracuja() {

    }

    /**
     * Construtor da classe Maracuja que define o nome e a posição da fruta.
     *
     * @param nome     O nome da fruta Maracujá.
     */

    public Maracuja(String nome) {
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
        return false;
    }
}
