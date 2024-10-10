package frutas;

import elementos.Jogador;

public class Coco extends Fruta {
	public Coco() {
        
    }

    public Coco(String nome, int posX, int posY) {
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
