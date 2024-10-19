package visao.estilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FundoAnimado extends JLabel {

    private Image imagemFundo;
    private int[] POS_XY = new int[2];

    public FundoAnimado(Image imagemFundo, int posX, int posY) {
        this.imagemFundo = imagemFundo;
        this.POS_XY[0] = posX;
        this.POS_XY[1] = posY;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, POS_XY[0], 0, this);
    }

    public void iniciarAnimacao(int delayContador) {
        FundoAnimado fundo = this;
        ActionListener acaoDoContador = new ActionListener() {
            int velocidadeMovimento = 1;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (POS_XY[0] == 0 || POS_XY[0] == -imagemFundo.getWidth(null)+imagemFundo.getWidth(null)/2){
                    velocidadeMovimento *= -1;


                }
                POS_XY[0] = POS_XY[0] + velocidadeMovimento;
                fundo.repaint();
                fundo.revalidate();

            }
        };

        Timer contador = new Timer(delayContador, acaoDoContador);
        contador.start();
    }

}
