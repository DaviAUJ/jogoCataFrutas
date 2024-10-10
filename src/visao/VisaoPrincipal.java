package visao;

import javax.swing.*;

/**
 * Classe que representa a janela principal do jogo Cata-Frutas.
 * Esta classe estende JFrame e configura a janela do jogo.
 */

public class VisaoPrincipal extends JFrame {

    /**
     * Construtor da classe VisaoPrincipal.
     *
     * @param SIZE_CONSTANT O tamanho (largura e altura) da janela do jogo.
     */

    public VisaoPrincipal(int SIZE_CONSTANT) {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(SIZE_CONSTANT, SIZE_CONSTANT);
        setResizable(false);
        setTitle("Jogo Cata-Frutas --> v0.1");

    }
}
