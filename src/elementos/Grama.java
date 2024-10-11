package elementos;

/**
 * Esta classe representa um elemento de grama no terreno, que é um tipo
 * específico de ElementoEstatico.
 */

public class Grama extends ElementoEstatico {

    /**
     * Construtor da classe Grama que define o nome e a posição da grama.
     *
     * @param nome O nome do elemento de grama.
     * @param posX A coordenada x da posição da grama.
     * @param posY A coordenada y da posição da grama.
     */

    public Grama(String nome, int posX, int posY) {
    	this.nome = nome;
    	this.posicaoX = posX;
    	this.posicaoY = posY;
    }
}
