package elementos;

/**
 * Esta classe abstrata representa um elemento estático no terreno,
 * que é um tipo específico de Elemento.
 */

public abstract class ElementoEstatico extends Elemento {

    /**
     * Construtor padrão da classe ElementoEstatico.
     */

    public ElementoEstatico() {
    }

    /**
     * Construtor da classe ElementoEstatico que define o nome e a posição do
     * elemento estático.
     *
     * @param nome O nome do elemento estático.
     * @param posX A coordenada x da posição do elemento.
     * @param posY A coordenada y da posição do elemento.
     */

    public ElementoEstatico(String nome, int posX, int posY) {
        super(nome, posX, posY);
    }
}
