package visao;

import javax.swing.*;
import elementos.*;

import java.awt.*;

/**
 * Classe que representa um botão personalizado (Quadradinho) no jogo.
 * Este botão exibe um elemento do jogo com uma cor de fundo específica.
 */

public class Quadradinho extends JButton {
    private Object backgroundImage; // futuramente vai armazenar uma imagem do item estático.
    private Elemento elemento;
    private int tamanho;

    /**
     * Construtor da classe Quadradinho.
     *
     * @param elemento O elemento a ser exibido no quadradinho.
     * @param tamanho  O tamanho do quadradinho (largura e altura).
     */

    public Quadradinho(Elemento elemento, int tamanho) {
        this.elemento = elemento;
        this.tamanho = tamanho;

        setText(elemento.getNome());
        setSize(tamanho, tamanho);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        switch (elemento.getNome().substring(0, 2)) {
            case "Gr":
                setBackground(Color.GREEN);
                break;
            case "Ar":
                setBackground(Color.CYAN);
                break;
            case "Pe":
                setBackground(Color.GRAY);
                break;
            case "Ma":
                setBackground(Color.YELLOW);
                break;
            case "Jo":
                setBackground(Color.RED);
                break;
            default:
                setBackground(Color.WHITE);
                break;

        }
    }

}
