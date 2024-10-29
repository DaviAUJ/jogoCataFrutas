package visao.estilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa um fundo animado em um componente JLabel.
 */
public class FundoAnimado extends JLabel {

    private Image imagemFundo;
    private int[] POS_XY = new int[2];

    /**
     * Construtor da classe FundoAnimado.
     *
     * @param imagemFundo A imagem que será usada como fundo animado.
     * @param posX        A posição inicial X da imagem.
     * @param posY        A posição inicial Y da imagem.
     */
    public FundoAnimado(Image imagemFundo, int posX, int posY) {
        this.imagemFundo = imagemFundo;
        this.POS_XY[0] = posX;
        this.POS_XY[1] = posY;
        
        this.setFocusable(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, POS_XY[0], 0, this);
    }

    /**
     * Inicia a animação do fundo, movendo-o para a esquerda ou direita.
     *
     * @param delayContador O atraso em milissegundos entre cada atualização da animação.
     */
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

            }
        };

        Timer contador = new Timer(delayContador, acaoDoContador);
        contador.start();
    }
}
