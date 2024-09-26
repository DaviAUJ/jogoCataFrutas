package jogoCataFrutas;

public abstract class Elemento {
	private String nome = "";
	private int posicaoX = 0;
	private int posicaoY = 0;

	public Elemento() {
	}

	public Elemento(String nome, int posicaoX, int posicaoY) {
		this.nome = nome;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
}
