package frutas;

import jogoCataFrutas.Jogador;

public class Laranja extends Fruta {
	public Laranja() {
        
    }

    public Laranja(String nome, int posicaoX, int posicaoY, boolean bichada) {
        super(nome, posicaoX, posicaoY, bichada);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
