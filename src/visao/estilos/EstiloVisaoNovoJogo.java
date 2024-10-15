package visao.estilos;
import visao.VisaoNovoJogo;

import javax.swing.*;
import java.awt.*;

public abstract class EstiloVisaoNovoJogo {

    private static ImageIcon IMAGEM = new ImageIcon("./assets/imgs/inicio-background.jpg");

    public static void aplicarEstilo(VisaoNovoJogo tela) {
        tela.setLayout(new BorderLayout());
        tela.setBounds(0, 0, 1280, 720);
        tela.setBackground(Color.YELLOW);

        tela.painelEsquerda = new JPanel();
        tela.painelDireita = new JPanel();
        tela.imgGrandeEsquerda = new JLabel(IMAGEM);

        painelEsquerda(tela.painelEsquerda);
        painelDireita(tela.painelDireita);
        imagemFundoPainelEsquerda(tela.imgGrandeEsquerda);



        tela.painelEsquerda.add(tela.imgGrandeEsquerda);
        tela.add(tela.painelEsquerda, BorderLayout.WEST);
        tela.add(tela.painelDireita, BorderLayout.EAST);




    }

    private static void painelEsquerda (JPanel painel) {
        painel.setPreferredSize(new Dimension(450, 720));
        painel.setBackground(Color.WHITE);
        painel.setLayout(null);
    }

    private static void painelDireita(JPanel painel) {
        painel.setPreferredSize(new Dimension(830, 720));
        painel.setBackground(new Color(37, 37, 37));
    }

    private static void imagemFundoPainelEsquerda(JLabel fundo) {
        fundo.setBounds(0, 0, 450, 720);
        fundo.setIcon(new ImageIcon(IMAGEM.getImage().getScaledInstance(450, 720, Image.SCALE_AREA_AVERAGING)));
    }
}
