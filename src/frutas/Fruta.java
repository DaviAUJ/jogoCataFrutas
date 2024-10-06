package frutas;

import java.util.Random;

import elementos.Elemento;
import elementos.Jogador;
import utilitarios.GerenciadorArquivo;

public abstract class Fruta extends Elemento {
    private boolean bichada = false;

    public Fruta() {
    }

    public Fruta(String nome, int posicaoX, int posicaoY) {
        super(nome, posicaoX, posicaoY);
        
    	GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
    	Random gerador = new Random();
    	
    	if(gerador.nextInt(100) + 1 <= arquivo.pegarChanceBichadas()) {
    		bichada = true;
    	}
    }

    public boolean isBichada() {
        return bichada;
    }

    public abstract boolean buffar(Jogador jogador);

    public boolean nerfar(Jogador jogador) {
        return false;
    }
}
