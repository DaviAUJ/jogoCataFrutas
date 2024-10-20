package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.util.ArrayList;

public class TabuleiroJogo extends JPanel {
    public int TAMANHO;
    public ArrayList<QuadradinhoTabuleiro> malha;


    public TabuleiroJogo(int tamanho) {
        this.TAMANHO = tamanho;

        EstiloComponentes.aplicarEstiloTabuleiro(this);
    }
}
