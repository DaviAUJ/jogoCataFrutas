package frutas;

import elementos.Jogador;

public class Laranja extends Fruta {
	public Laranja() {
        
    }

    public Laranja(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
