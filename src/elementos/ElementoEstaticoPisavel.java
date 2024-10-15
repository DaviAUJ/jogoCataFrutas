package elementos;

public abstract class ElementoEstaticoPisavel extends ElementoEstatico {
    protected Jogador espacoJogador;

    protected void setJogador(Jogador espacoJogador) {
        this.espacoJogador = espacoJogador;
    }

    public Boolean temJogador() {
        return espacoJogador != null;
    }
}
