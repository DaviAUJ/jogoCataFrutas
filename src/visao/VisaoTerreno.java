package visao;

import javax.swing.*;
import java.awt.*;
import elementos.*;

/**
 * Classe que representa a visualização do terreno no jogo Cata-Frutas.
 * Esta classe estende JPanel e organiza a exibição do tabuleiro do jogo.
 */

public class VisaoTerreno extends JPanel {
    Quadradinho[][] grade;

    /**
     * Construtor da classe VisaoTerreno.
     *
     * @param floresta      O objeto Terreno que contém as informações sobre o
     *                      tabuleiro.
     * @param SIZE_CONSTANT O tamanho total da visualização do terreno.
     */

    public VisaoTerreno(Terreno floresta, int SIZE_CONSTANT) {
        setLayout(new GridLayout(floresta.getDimensao(), floresta.getDimensao()));
        setSize(SIZE_CONSTANT, SIZE_CONSTANT);

        this.grade = new Quadradinho[floresta.getDimensao()][floresta.getDimensao()];
        for (int i = 0; i < floresta.getDimensao(); i++) {
            for (int j = 0; j < floresta.getDimensao(); j++) {
                this.grade[i][j] = new Quadradinho(floresta.getTabuleiro()[i][j],
                        (int) SIZE_CONSTANT / floresta.getDimensao());
                add(this.grade[i][j]);
            }
        }
    }
}
