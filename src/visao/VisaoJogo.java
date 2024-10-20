package visao;

import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloVisaoJogo;

import javax.swing.*;

public class VisaoJogo extends JPanel {

    public TabuleiroJogo tabuleiro;

    public VisaoJogo(GerenciadorDeTelas gerenciador){

        EstiloVisaoJogo.aplicarEstilo(this);

    }
}
