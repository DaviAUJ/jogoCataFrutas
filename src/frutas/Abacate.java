package frutas;

import jogoCataFrutas.Jogador;

public class Abacate extends Fruta {
	public Abacate() {
        
    }

    public Abacate(String nome, int posicaoX, int posicaoY, boolean bichada) {
        super(nome, posicaoX, posicaoY, bichada);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
