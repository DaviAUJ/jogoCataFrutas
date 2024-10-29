package visao;


import javax.swing.*;
import visao.estilos.EstiloVisaoInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a visão inicial do jogo.
 * Contém um título e um botão para iniciar o jogo.
 */
public class VisaoInicio extends JPanel {
    public JLabel tituloJogo;
    public JButton iniciarJogo;
    public int tituloJogoAnimacao;


    /**
     * Construtor da classe VisaoInicio.
     * Inicializa os componentes da visão e configura o listener para o botão de iniciar o jogo.
     *
     * @param gerenciador O gerenciador de telas que controla a navegação entre as diferentes visões.
     */
    public VisaoInicio(GerenciadorDeTelas gerenciador) {





        EstiloVisaoInicio.aplicarEstilo(this);

        this.iniciarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciador.irParaTela("OPCOES INICIAIS");
            }
        });



    }
}
