package frutas;

import elementos.Jogador;

public class Maracuja extends Fruta {
	public Maracuja() {
        
    }

    public Maracuja(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
