package visao;

import visao.estilos.EstiloVisaoInicialOpcoes;

import javax.swing.*;

public class VisaoInicialOpcoes extends JPanel {

    public JButton btnCarregarJogo;
    public JButton btnJogarDoInicio;
    public JButton btnOk;
    public JButton btnVoltar;


    public VisaoInicialOpcoes(GerenciadorDeTelas gerenciadorDeTelas) {

        EstiloVisaoInicialOpcoes.aplicarEstilo(this);
    }
}
