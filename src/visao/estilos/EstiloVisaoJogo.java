package visao.estilos;

import visao.VisaoJogo;
import visao.componentes.TabuleiroJogo;

public abstract class EstiloVisaoJogo {

    public static void aplicarEstilo(VisaoJogo tela){
        tela.setBounds(0, 0, 1280, 720);
        tela.setLayout(null);

        tela.tabuleiro = new TabuleiroJogo(800);

        tela.add(tela.tabuleiro);
    }
}
