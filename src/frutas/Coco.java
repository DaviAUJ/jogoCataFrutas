package frutas;

import jogoCataFrutas.Jogador;

public class Coco extends Fruta {
	public Coco() {
        
    }

    public Coco(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
