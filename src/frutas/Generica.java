package frutas;

import elementos.Jogador;
import sons.EventoSonoroHandler;

/**
 * Esta classe representa uma fruta genérica, que é um tipo específico de Fruta.
 */

public class Generica extends Fruta {

    /**
     * Construtor padrão da classe da fruta Generica.
     */

    public Generica() {

    }

    /**
     * Construtor da classe Generica que define o nome e a posição da fruta.
     *
     * @param nome     O nome da fruta genérica.
     */

    public Generica(String nome) {
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
        EventoSonoroHandler.somComer();
        return false;
    }
}
