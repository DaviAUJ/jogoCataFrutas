package cenario;

public class Arvore extends ElementoEstatico {
    private String tipo;
    
    public Arvore(String nome, int posX, int posY, String tipo) {
        super(nome, posX, posY);
        
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean derrubarFruta() {
        return false;
    }
    

}
