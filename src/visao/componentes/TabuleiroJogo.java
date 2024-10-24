package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TabuleiroJogo extends JPanel {
    private int TAMANHO;
    private int DIMENSAO;
    public ArrayList< ArrayList <QuadradinhoTabuleiro> > malha;


    public TabuleiroJogo(int tamanho, int dimensao) {
        this.TAMANHO = tamanho;
        this.DIMENSAO = dimensao;

        this.malha = new ArrayList<>(this.DIMENSAO);

        for (int i = 0; i < this.DIMENSAO; i++) {
            ArrayList<QuadradinhoTabuleiro> coluna = new ArrayList<>(this.DIMENSAO);
            for (int j = 0; j < this.DIMENSAO; j++) {
                QuadradinhoTabuleiro quad = new QuadradinhoTabuleiro(this.TAMANHO/this.DIMENSAO);
                coluna.add(quad);
            }

            this.malha.add(coluna);
        }



        EstiloComponentes.aplicarEstiloTabuleiro(this);
    }

    public int getTamanho() {
        return TAMANHO;
    }

    public int getDimensao() {
        return DIMENSAO;
    }
}
