package elementos;

import frutas.Fruta;

/**
 * Esta classe representa um elemento de grama no terreno, que é um tipo
 * específico de ElementoEstatico.
 */

public class Grama extends ElementoEstaticoPisavel {
    private Fruta espacoFruta = null;

    /**
     * Construtor da classe Grama que define o nome e a posição da grama.
     *
     * @param nome O nome do elemento de grama.
     * @param posicaoX A coordenada x da posição da grama.
     * @param posicaoY A coordenada y da posição da grama.
     */

    public Grama(String nome, int posicaoX, int posicaoY) {
    	this.nome = nome;
    	this.posicaoX = posicaoX;
    	this.posicaoY = posicaoY;
    }

    /**
     * Construtor da classe Grama que define o nome, posição e jogador associado à grama.
     *
     * @param nome O nome do elemento de grama.
     * @param posicaoX A coordenada x da posição da grama.
     * @param posicaoY A coordenada y da posição da grama.
     * @param espaJogador O jogador associado ao espaço da grama.
     */
    public Grama(String nome, int posicaoX, int posicaoY, Jogador espaJogador) {
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.espacoJogador = espaJogador;
    }

    /**
     * Retorna a fruta associada ao espaço da grama.
     *
     * @return A fruta no espaço da grama, ou null se não houver.
     */
    public Fruta getEspacoFruta() {
        return espacoFruta;
    }

    /**
     * Define a fruta associada ao espaço da grama.
     *
     * @param espacoFruta A fruta a ser associada ao espaço da grama.
     */
    public void setEspacoFruta(Fruta espacoFruta) {
        this.espacoFruta = espacoFruta;
    }

    /**
     * Verifica se há uma fruta associada ao espaço da grama.
     *
     * @return true se há uma fruta, false caso contrário.
     */
    public boolean temFruta() {
        return espacoFruta != null;
    }
}
