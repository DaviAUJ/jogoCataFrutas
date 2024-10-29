package visao;

import utilitarios.GerenciadorArquivo;
import visao.estilos.EstiloVisaoInicialOpcoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe que representa a visão inicial de opções do jogo.
 * Permite ao usuário escolher entre iniciar um novo jogo ou carregar um jogo salvo.
 */
public class VisaoInicialOpcoes extends JPanel {

    public JButton btnCarregarJogo;
    public JButton btnJogarDoInicio;
    public JButton btnOk;
    public JButton btnVoltar;

    /**
     * Construtor da classe VisaoInicialOpcoes.
     * Inicializa os componentes da visão e configura os listeners para os botões.
     *
     * @param gerenciadorDeTelas O gerenciador de telas que controla a navegação entre as diferentes visões.
     */

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
