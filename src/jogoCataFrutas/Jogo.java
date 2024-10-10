package jogoCataFrutas;

import elementos.Jogador;
import elementos.Terreno;
import utilitarios.GerenciadorArquivo;

/**
 * Representa o jogo Cata Frutas.
 * Esta classe gerencia os jogadores, o terreno e o estado do jogo.
 */

public class Jogo {
    protected Terreno floresta = new Terreno();
    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private int contadorRodada = 0;
    private String estado = "";

    /**
     * Construtor da classe Jogo.
     * Inicializa um novo jogo com um terreno e dois jogadores.
     */

    public Jogo() {
    }

    /**
     * Obtém o estado atual do jogo.
     *
     * @return uma String representando o estado do jogo.
     */

    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado do jogo.
     *
     * @param estado uma String representando o novo estado do jogo.
     */

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtém o terreno do jogo.
     *
     * @return o objeto Terreno que representa a floresta do jogo.
     */

    public Terreno getFloresta() {
        return this.floresta;
    }

    /**
     * Inicia o jogo.
     *
     * @return true se o jogo foi iniciado com sucesso, false caso contrário.
     */

    public boolean iniciarJogo() {
        return false;
    }

    /**
     * Passa a vez para o próximo jogador.
     *
     * @return true se a vez foi passada com sucesso, false caso contrário.
     */

    public boolean passarVez() {
        return false;
    }
}
