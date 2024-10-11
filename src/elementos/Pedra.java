package elementos;

public class Pedra extends ElementoEstatico {
	public Pedra() {  }
	
    public Pedra(String nome, int posX, int posY) {
    	this.nome = nome;
    	this.posicaoX = posX;
    	this.posicaoY = posY;
    }
}
