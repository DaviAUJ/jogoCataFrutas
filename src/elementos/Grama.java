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

    public Grama(String nome, int posicaoX, int posicaoY, Jogador espaJogador) {
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.espacoJogador = espaJogador;
    }

    public Fruta getEspacoFruta() {
        return espacoFruta;
    }

    public void setEspacoFruta(Fruta espacoFruta) {
        this.espacoFruta = espacoFruta;
    }

    public boolean temFruta() {
        return espacoFruta != null;
    }
}
