package frutas;

import jogoCataFrutas.Elemento;
import jogoCataFrutas.Jogador;
import utilitarios.GerenciadorArquivo;

public abstract class Fruta extends Elemento {
	private float chanceBichada = 0.25f;
    private boolean bichada = false;

    public Fruta() {
    }

    public Fruta(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);
    	GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
    	
    	chanceBichada = (float) arquivo.pegarChanceBichadas() / 100;
    }

    public boolean isBichada() {
        return bichada;
    }

    public abstract boolean buffar(Jogador jogador);

    public boolean nerfar(Jogador jogador) {
        return false;
    }
}
