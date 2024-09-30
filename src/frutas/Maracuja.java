package frutas;

import jogoCataFrutas.Jogador;

public class Maracuja extends Fruta {
	public Maracuja() {
        
    }

    public Maracuja(String nome, int posicaoX, int posicaoY, boolean bichada) {
        super(nome, posicaoX, posicaoY, bichada);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
