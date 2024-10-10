package frutas;

import elementos.Jogador;

public class Maracuja extends Fruta {
	public Maracuja() {
        
    }

	public Maracuja(String nome, int posX, int posY) {
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
