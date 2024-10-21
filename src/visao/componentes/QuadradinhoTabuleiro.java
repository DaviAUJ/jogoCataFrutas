package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;

public class QuadradinhoTabuleiro extends JLabel {
    public JLabel jogador;
    private boolean temJogador;

    public QuadradinhoTabuleiro() {
        this.jogador = new JLabel();
        temJogador = false;

        EstiloComponentes.aplicarEstiloQuadradinhoTabuleiro(this);
    }

    public void setTemJogador(boolean temJogador) {
        this.temJogador = temJogador;
    }

    public boolean getTemJogador() {
        return temJogador;
    }
}
