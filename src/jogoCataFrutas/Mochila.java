package jogoCataFrutas;

import frutas.Fruta;

public class Mochila {
    private int capacidade = 1;
    private int quantFrutas = 0;
    private Fruta[] listaFrutas = new Fruta[capacidade];

    public Mochila() {  }

    public Mochila(int capacidade) {
        this.capacidade = capacidade;
        listaFrutas = new Fruta[capacidade];
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
