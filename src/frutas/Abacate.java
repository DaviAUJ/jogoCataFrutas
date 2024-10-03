package frutas;

import jogoCataFrutas.Jogador;

public class Abacate extends Fruta {
	public Abacate() {
        
    }

    public Abacate(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
