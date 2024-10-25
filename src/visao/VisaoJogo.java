package visao;

import jogoCataFrutas.Configuracoes;
import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloVisaoJogo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VisaoJogo extends JPanel {

    public TabuleiroJogo tabuleiro;
    public JLabel rodada;

    public ArrayList <JLabel> valoresInventario1;
    public ArrayList <JLabel> valoresInventario2;

    public VisaoJogo(GerenciadorDeTelas gerenciador){

        int dimensao = Configuracoes.dimensao;
        this.tabuleiro = new TabuleiroJogo(600);
        this.rodada = new JLabel("0");
        this.valoresInventario1 = new ArrayList<>(8);
        this.valoresInventario2 = new ArrayList<>(8);

        for (int i = 0; i < 8; i++){
            valoresInventario1.add(new JLabel("0"));
            valoresInventario2.add(new JLabel("0"));
        }

        EstiloVisaoJogo.aplicarEstilo(this);

    }
}
