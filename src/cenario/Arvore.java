package cenario;

import frutas.Fruta;

public class Arvore extends ElementoEstatico {
    // Talvez esteja meio errado isso aqui de Fruta, talvez era pra ser uma string com o nome da fruta ao inves da classe dela
    private Fruta tipo = null;

    public Arvore(Fruta tipo) {
        this.tipo = tipo;
    }

    public Fruta getTipo() {
        return tipo;
    }

    public boolean derrubarFruta() {
        return false;
    }
}
