package visao;

import visao.estilos.EstiloVisaoNomesJogadores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisaoNomesJogadores extends JPanel {
    public JTextField nomeJogador1;
    public JTextField nomeJogador2;

    public JButton btnVoltar;
    public JButton btnOk;


    public VisaoNomesJogadores(GerenciadorDeTelas gerenciador) {
        EstiloVisaoNomesJogadores.aplicarEstilo(this);

        this.btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gerenciador.voltarTela();

            }
        });

        this.btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                gerenciador.addNoCache("nomeJogador1", nomeJogador1);
                gerenciador.addNoCache("nomeJogador2", nomeJogador2);
                gerenciador.irParaTela("NOVO JOGO");

            }
        });
    }
}
