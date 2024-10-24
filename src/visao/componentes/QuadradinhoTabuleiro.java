package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;

public class QuadradinhoTabuleiro extends JButton {
    public JLabel fundo;
    public JLabel spriteJog;
    private int TAMANHO;

    public QuadradinhoTabuleiro(int tamanho) {
        this.TAMANHO = tamanho;
        EstiloComponentes.aplicarEstiloQuadradinhoTabuleiro(this);
    }

    public int getTamanho() {
        return TAMANHO;
    }
}
