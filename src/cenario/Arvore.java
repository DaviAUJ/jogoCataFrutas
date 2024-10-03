package cenario;

import java.util.ArrayList;
import java.util.Arrays;
import frutas.Fruta;
import utilitarios.*;

public class Arvore extends ElementoEstatico {
    private static ArrayList<Integer> faltando = new ArrayList<Integer>(Arrays.asList(Extras.colunaMatriz(new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao).pegarFrutas(), 1)));
    private String tipo;

    public Arvore(String nome, int posX, int posY) {
        super(nome, posX, posY);
        
        
    }

    public String getTipo() {
        return tipo;
    }

    public boolean derrubarFruta() {
        return false;
    }
}
