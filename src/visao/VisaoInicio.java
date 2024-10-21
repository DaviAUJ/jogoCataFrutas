package visao;


import javax.swing.*;
import visao.estilos.EstiloVisaoInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VisaoInicio extends JPanel {
    public JLabel tituloJogo;
    public JButton iniciarJogo;
    public int tituloJogoAnimacao;



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
