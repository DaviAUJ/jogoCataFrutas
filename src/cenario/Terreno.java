package cenario;

import jogoCataFrutas.Elemento;

public class Terreno {
	private int dimensao = 3;
    private Elemento[][] tabuleiro = new Elemento[dimensao][dimensao];
    
    public Terreno() { }

    // Para quando for gerar um terreno novo
    public Terreno(int dimensao) {
        this.setDimensao(dimensao);
    }

    // Para quando for pegar um terreno já feito
    public Terreno(Elemento[][] tabuleiro) {
        this.setTabuleiro(tabuleiro);
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
        tabuleiro = new Elemento[dimensao][dimensao];
    }

    public Elemento[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Elemento[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
        dimensao = tabuleiro.length;
    }

    public boolean mudarPosicao(Elemento elemento, int X, int Y) {
        return false;
    }
}
