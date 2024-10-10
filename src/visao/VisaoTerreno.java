package visao;

import javax.swing.*;
import java.awt.*;
import elementos.*;

public class VisaoTerreno extends JPanel {
    Quadradinho[][] grade;


    public VisaoTerreno(Terreno floresta, int SIZE_CONSTANT) {
        setLayout(new GridLayout(floresta.getDimensao(), floresta.getDimensao()));
        setSize(SIZE_CONSTANT, SIZE_CONSTANT);

        this.grade = new Quadradinho[floresta.getDimensao()][floresta.getDimensao()];
        for (int i = 0; i < floresta.getDimensao(); i++) {
            for (int j = 0; j < floresta.getDimensao(); j++) {
                this.grade[i][j] = new Quadradinho(floresta.getTabuleiro()[i][j], (int) SIZE_CONSTANT / floresta.getDimensao());
                add(this.grade[i][j]);
            }
        }

    }
}
