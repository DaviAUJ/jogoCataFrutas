package elementos;

/**
 * Esta classe abstrata representa um elemento no terreno, contendo informações
 * básicas como nome e posição.
 */
public abstract class Elemento {
	protected String nome = "";
	protected int posicaoX = 0;
	protected int posicaoY = 0;

	/** Construtor padrão da classe Elemento. */

	public Elemento() {
	}

	/**
     * Construtor da classe Elemento que define o nome e a posição do elemento.
     *
     * @param nome     O nome do elemento.
     * @param posicaoX A coordenada x da posição do elemento.
     * @param posicaoY A coordenada y da posição do elemento.
     */

	public Elemento(String nome, int posicaoX, int posicaoY) {
		this.nome = nome;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}

	/**
     * Retorna o nome do elemento.
     *
     * @return O nome do elemento.
     */

	public String getNome() {
		return nome;
	}

	 /**
     * Define o nome do elemento.
     *
     * @param nome O novo nome do elemento.
     */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
     * Retorna a coordenada x da posição do elemento.
     *
     * @return A coordenada x do elemento.
     */

	public int getPosicaoX() {
		return posicaoX;
	}

	/**
     * Define a coordenada X da posição do elemento.
     *
     * @param posicaoX A nova coordenada X do elemento.
     */

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	/**
     * Retorna a coordenada y da posição do elemento.
     *
     * @return A coordenada y do elemento.
     */

	public int getPosicaoY() {
		return posicaoY;
	}

	/**
     * Define a coordenada y da posição do elemento.
     *
     * @param posicaoY A nova coordenada y do elemento.
     */

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
}
