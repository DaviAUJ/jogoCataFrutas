package frutas;

import java.util.Random;

import elementos.Elemento;
import elementos.Jogador;
import utilitarios.GerenciadorArquivo;

public abstract class Fruta extends Elemento {
    protected boolean bichada = false;

    public Fruta() {
    	GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
    	Random gerador = new Random();
    	
    	if(gerador.nextInt(100) + 1 <= arquivo.pegarChanceBichadas()) {
    		bichada = true;
    	}
    }
    
    public void setBichada(boolean bichada) {
    	this.bichada = bichada;
    }

    public boolean isBichada() {
        return bichada;
    }

    public abstract boolean buffar(Jogador jogador);

    public boolean nerfar(Jogador jogador) {
        return false;
    }
}
