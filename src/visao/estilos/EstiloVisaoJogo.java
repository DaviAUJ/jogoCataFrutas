package visao.estilos;

import visao.VisaoJogo;
import visao.componentes.TabuleiroJogo;

import java.awt.*;

public abstract class EstiloVisaoJogo {
    private static int TELA_LARGURA = 1280;
    private static int TELA_ALTURA = 720;

    public static void aplicarEstilo(VisaoJogo tela){
        tela.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        tela.setLayout(null);
        aplicarEstiloTabuleiro(tela.tabuleiro);

        tela.add(tela.tabuleiro);
    }


    public static void aplicarEstiloTabuleiro(TabuleiroJogo tabuleiro){
        tabuleiro.setLocation((TELA_LARGURA - tabuleiro.getTamanho()) / 2, 0);
    }
}
