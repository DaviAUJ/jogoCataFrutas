package jogoCataFrutas;

import cenario.Terreno;
import utilitarios.GerenciadorArquivo;

public class Jogo {
    protected Terreno floresta = new Terreno();
    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private int contadorRodada = 0;
    private String estado = "";
    private int espacoMochila = 1;
    
    public Jogo() {
    	GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
    	
    	chanceBichada = (float) arquivo.pegarChanceBichadas() / 100;
    	espacoMochila = arquivo.pegarEspacoMochila();
    }

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
