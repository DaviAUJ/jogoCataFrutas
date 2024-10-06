package elementos;

import frutas.Fruta;
import utilitarios.GerenciadorArquivo;

public class Mochila {
    private int capacidade = 1;
    private Fruta[] listaFrutas = new Fruta[capacidade];
    private int quantFrutas = 0;

    public Mochila() {
    	GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
    	
    	capacidade = arquivo.pegarEspacoMochila();
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getQuantFrutas() {
        return quantFrutas;
    }

    public void setQuantFrutas(int quantFrutas) {
        if(quantFrutas > capacidade) {
            quantFrutas = capacidade;
        } 
        else {
            this.quantFrutas = quantFrutas;
        }
    }

    public boolean removerFruta(String nomeFruta) {
        return false;
    }

    public boolean adicionarFruta(String nomeFruta) {
        return false;
    }
}
