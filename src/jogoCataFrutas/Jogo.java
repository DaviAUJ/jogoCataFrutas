package jogoCataFrutas;

import elementos.Jogador;
import elementos.Terreno;
import utilitarios.GerenciadorArquivo;

public class Jogo {
    protected Terreno floresta = new Terreno();
    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private int contadorRodada = 0;
    private String estado = "";
    
    public Jogo() {  }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean iniciarJogo() {
        return false;
    }

    public boolean passarVez() {
        return false;
    }
}
