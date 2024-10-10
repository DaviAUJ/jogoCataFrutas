package visao;

import javax.swing.*;

public class VisaoPrincipal extends JFrame {


    public VisaoPrincipal (int SIZE_CONSTANT) {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(SIZE_CONSTANT, SIZE_CONSTANT);
        setResizable(false);
        setTitle("Jogo Cata-Frutas --> v0.1");



    }
}
