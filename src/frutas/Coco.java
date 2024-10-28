package frutas;

import elementos.Jogador;
import sons.EventoSonoroHandler;
import utilitarios.Transmissor;

/**
 * Esta classe representa a fruta Coco, que é um tipo específico de Fruta.
 */

public class Coco extends Fruta {

    /**
     * Construtor padrão da classe Coco.
     */

    public Coco() {

    }

    /**
     * Construtor da classe Coco que define o nome e a posição da fruta.
     *
     * @param nome     O nome da fruta Coco.
     */

    public Coco(String nome) {
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
    		jogador.setPontosMovimento(2 * jogador.getPontosMovimento());
    		Transmissor.avisoEfeito(jogador, 1);
            EventoSonoroHandler.somBuff();
    		return true;
    	}
        return false;
    }
}
