package frutas;

import jogoCataFrutas.Elemento;
import jogoCataFrutas.Jogador;

public abstract class Fruta extends Elemento {
    private boolean bichada = false;

    public Fruta() {
    }

    public Fruta(String nome, int posicaoX, int posicaoY, boolean bichada) {
        super(nome, posicaoX, posicaoY);
        this.bichada = bichada;
    }

    public boolean isBichada() {
        return bichada;
    }

    public void setBichada(boolean bichada) {
        this.bichada = bichada;
    }

    public abstract boolean buffar(Jogador jogador);

    public boolean nerfar(Jogador jogador) { }
}
