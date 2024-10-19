package visao;

import visao.estilos.EstiloVisaoInicialOpcoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisaoInicialOpcoes extends JPanel {

    public JButton btnCarregarJogo;
    public JButton btnJogarDoInicio;
    public JButton btnOk;
    public JButton btnVoltar;


    public VisaoInicialOpcoes(GerenciadorDeTelas gerenciadorDeTelas) {

        EstiloVisaoInicialOpcoes.aplicarEstilo(this);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciadorDeTelas.voltarTela();
            }
        });

        btnJogarDoInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciadorDeTelas.irParaTela("NOMES DOS JOGADORES");
            }
        });
    }
}
