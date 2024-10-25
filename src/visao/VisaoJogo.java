package visao;

import jogoCataFrutas.Configuracoes;
import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloVisaoJogo;

import javax.swing.*;
import java.util.HashMap;

public class VisaoJogo extends JPanel {

    public TabuleiroJogo tabuleiro;
    public JLabel rodada;

    public VisaoJogo(GerenciadorDeTelas gerenciador){

        int dimensao = Configuracoes.dimensao;
        this.tabuleiro = new TabuleiroJogo(600);
        this.rodada = new JLabel("0");
        EstiloVisaoJogo.aplicarEstilo(this);

    }
}
