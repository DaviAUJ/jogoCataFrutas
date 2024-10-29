package visao;

import visao.estilos.EstiloVisaoNomesJogadores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a visão para inserção dos nomes dos jogadores.
 * Permite que os jogadores insiram seus nomes antes de iniciar um novo jogo.
 */
public class VisaoNomesJogadores extends JPanel {
    public JTextField nomeJogador1;
    public JTextField nomeJogador2;

    public JButton btnVoltar;
    public JButton btnOk;


    /**
     * Construtor da classe VisaoNomesJogadores.
     * Inicializa os componentes da visão e configura os listeners para os botões.
     *
     * @param gerenciador O gerenciador de telas que controla a navegação entre as diferentes visões.
     */
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
