package elementos;

/**
 * Esta classe representa um elemento de pedra no terreno,
 * que é um tipo específico de ElementoEstatico.
 */

public class Pedra extends ElementoEstatico {

    /**
     * Construtor padrão da classe Pedra.
     */

    public Pedra() {
    }

    /**
     * Construtor da classe Pedra que define o nome e a posição da pedra.
     *
     * @param nome O nome do elemento de pedra.
     * @param posX A coordenada x da posição da pedra.
     * @param posY A coordenada y da posição da pedra.
     */

    public Pedra(String nome, int posX, int posY) {
    	this.nome = nome;
    	this.posicaoX = posX;
    	this.posicaoY = posY;
    }
}
