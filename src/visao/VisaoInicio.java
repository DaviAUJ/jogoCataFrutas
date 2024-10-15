package visao;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import visao.estilos.EstiloVisaoInicio;
import visao.estilos.Estilos;

public class VisaoInicio extends JPanel {
    public JPanel painelPrincipal;
    public JPanel painelDeConteudo;
    public JButton btnIniciar;
    public JButton btnCarregar;
    public JLabel conteudoFundo;


    public VisaoInicio(GerenciadorDeTelas gerenciador) {

        this.painelPrincipal = new JPanel();
        this.painelDeConteudo = new JPanel();
        this.btnIniciar = new JButton("Iniciar Jogo");
        this.btnCarregar = new JButton("Carregar Jogo");


        this.btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(painelDeConteudo, "Iniciando Jogo");
                gerenciador.mostrarTela("NOVO JOGO");
            }
        });

        this.btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(painelDeConteudo, "Carregando Jogo");
            }
        });

        EstiloVisaoInicio.aplicarEstilo(this);




    }


}
