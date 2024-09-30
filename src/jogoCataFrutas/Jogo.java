package jogoCataFrutas;

import cenario.Terreno;

public class Jogo {
    private Terreno floresta = new Terreno();
    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private int contadorRodada = 0;
    private String estado = "";

    // Aqui era para ser em outra classe de configuração, mas
    // classes estaticas não funcionam do jeito
    // que a gente tava pensando
    private int[] quantFrutas = new int[5];
    private int[] quantFrutasChao = new int[5];
    private int quantPedras = 0;
    private float chanceBichada = 0.25f;
    
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
