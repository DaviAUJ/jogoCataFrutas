package visao;

import utilitarios.GerenciadorArquivo;
import visao.estilos.EstiloVisaoInicialOpcoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        btnCarregarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciadorDeTelas.adicionarNovaTela("CARREGAR JOGO", new VisaoCarregarJogo(gerenciadorDeTelas));
                gerenciadorDeTelas.irParaTela("CARREGAR JOGO");
            }
        });
    }
}
