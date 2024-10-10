package frutas;

import elementos.Jogador;

public class Laranja extends Fruta {
	public Laranja() {
        
    }

	public Laranja(String nome, int posX, int posY) {
        super();
        
        this.nome = nome;
    	this.posicaoX = posX;
    	this.posicaoY = posY;
    }

    @Override
    public boolean buffar(Jogador jogador) {
        return false;
    }
}
