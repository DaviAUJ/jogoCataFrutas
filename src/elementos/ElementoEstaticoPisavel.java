package elementos;

/**
 * Esta classe abstrata representa um elemento estático pisável no terreno,
 * que é um tipo específico de ElementoEstatico.
 */
public abstract class ElementoEstaticoPisavel extends ElementoEstatico {
    protected Jogador espacoJogador;

    /**
     * Retorna o jogador associado ao elemento.
     *
     * @return O jogador que ocupa o espaço do elemento, ou null se não houver.
     */
    public Jogador getJogador() {
        return espacoJogador;
    }

    /**
     * Define o jogador associado ao elemento.
     *
     * @param espacoJogador O jogador a ser associado ao elemento.
     */
    protected void setJogador(Jogador espacoJogador) {
        this.espacoJogador = espacoJogador;
    }

    /**
     * Verifica se há um jogador associado ao elemento.
     *
     * @return true se há um jogador, false caso contrário.
     */
    public Boolean temJogador() {
        return espacoJogador != null;
    }

}
