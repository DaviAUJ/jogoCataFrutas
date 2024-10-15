package visao.estilos;
import javax.swing.*;
import java.awt.*;
import visao.*;

public abstract class EstiloVisaoInicio {
    public static final int LARGURA = 800;
    public static final int ALTURA = 300;


    public static void aplicarEstilo(VisaoInicio visaoInicio){

        visaoInicio.setBounds(0, 0, LARGURA, ALTURA);


        ImageIcon imagemFundoIcon = new ImageIcon("./assets/imgs/inicio-background.jpg");
        imagemFundoIcon.setImage(
                imagemFundoIcon.getImage().getScaledInstance(LARGURA, ALTURA, Image.SCALE_SMOOTH)
        );

        visaoInicio.conteudoFundo = new JLabel(imagemFundoIcon);
        JLabel conteudoFundo = visaoInicio.conteudoFundo;
        conteudoFundo.setBounds(0, 0, LARGURA, ALTURA);


        JPanel painelPrincipal = visaoInicio.painelPrincipal;
        JPanel painelConteudo = visaoInicio.painelDeConteudo;
        JButton btnInicio = visaoInicio.btnIniciar;
        JButton btnCarregar = visaoInicio.btnCarregar;

        painelPrincipal.setBounds(0, 0, LARGURA, ALTURA);

        painelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints configGrid = new GridBagConstraints();

        painelConteudo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        painelPrincipal.setOpaque(false);
        painelConteudo.setOpaque(false);

        //btnInicio.setPreferredSize(new Dimension(200, 50));
        //btnCarregar.setPreferredSize(new Dimension(200, 50));
        botoes(btnInicio);
        botoes(btnCarregar);

        botoes(btnCarregar);
        painelConteudo.add(btnInicio);
        painelConteudo.add(btnCarregar);
        painelConteudo.setPreferredSize(new Dimension(410, 110));

        configGrid.gridx = 0;
        configGrid.gridy = 0;
        painelPrincipal.add(painelConteudo, configGrid);

        conteudoFundo.add(painelPrincipal);
        visaoInicio.add(conteudoFundo);







    }

    public static void botoes(JButton botao){
        botao.setBackground(new Color(0, 0, 0, 150));
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setFont(new Font("Arial", Font.BOLD, 20));



    }
}
