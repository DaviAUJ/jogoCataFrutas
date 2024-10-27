package elementos;

public abstract class ElementoEstaticoPisavel extends ElementoEstatico {
    protected Jogador espacoJogador;


    public Jogador getJogador() {
        return espacoJogador;
    }

    protected void setJogador(Jogador espacoJogador) {
        this.espacoJogador = espacoJogador;
    }

    public Boolean temJogador() {
        return espacoJogador != null;
    }

}
