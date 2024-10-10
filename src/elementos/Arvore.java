package elementos;

/**
 * Esta classe representa uma árvore no terreno, que pode ter um tipo específico de fruta associada.
 */

import frutas.Fruta;

public class Arvore extends ElementoEstatico {
    private Class<? extends Fruta> tipo;

    /** Construtor padrão da classe Arvore. */
    public Arvore() {
    }

    /**
     * Construtor da classe Arvore que define o nome e a posição da árvore.
     *
     * @param nome O nome, que será de qual fruta será a árvore.
     * @param posX A coordenada x da posição da árvore.
     * @param posY A coordenada y da posição da árvore.
     */

    public Arvore(String nome, int posX, int posY) {
        super(nome, posX, posY);
    }

    /**
     * Construtor da classe Arvore que define o nome, posição e tipo da árvore.
     *
     * @param nome O nome da árvore.
     * @param posX A coordenada x da posição da árvore.
     * @param posY A coordenada y da posição da árvore.
     * @param tipo A classe do tipo de fruta associada à árvore.
     */

    public Arvore(String nome, int posX, int posY, Class<? extends Fruta> tipo) {
        super(nome, posX, posY);

        this.tipo = tipo;
    }

    /**
     * Retorna o tipo da fruta associada à árvore.
     *
     * @return A classe do tipo de fruta.
     */

    public Class<?> getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da fruta associada à árvore.
     *
     * @param classe A classe do tipo de fruta a ser associada à árvore.
     */

    public void setTipo(Class<? extends Fruta> classe) {
        tipo = classe;
    }
}
