package visao;

import javax.swing.*;
import elementos.*;

import java.awt.*;

public class Quadradinho extends JButton {
    private Object backgroundImage; //futuramente vai armazenar uma imagem do item estático.
    private Elemento elemento;
    private int tamanho;


    public Quadradinho(Elemento elemento, int tamanho) {
        this.elemento = elemento;
        this.tamanho = tamanho;

        setText(elemento.getNome());
        setSize(tamanho, tamanho);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        switch (elemento.getNome().substring(0, 2)){
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
