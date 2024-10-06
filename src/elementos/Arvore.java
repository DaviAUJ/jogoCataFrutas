package elementos;

import frutas.Fruta;

public class Arvore extends ElementoEstatico {
    private Class<? extends Fruta> tipo;
 
    public Arvore() {  }
    
    public Arvore(String nome, int posX, int posY) {
    	super(nome, posX, posY);
    }
    
    public Arvore(String nome, int posX, int posY, Class<? extends Fruta> tipo) {
        super(nome, posX, posY);
        
        this.tipo = tipo;
    }

    public Class<?> getTipo() {
        return tipo;
    }
    
    public void setTipo(Class<? extends Fruta> classe) {
    	tipo = classe;
    }
}
