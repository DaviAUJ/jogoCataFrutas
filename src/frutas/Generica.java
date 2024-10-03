package frutas;

import jogoCataFrutas.Jogador;

public class Generica extends Fruta {
    public Generica() {
        
    }

    public Generica(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
