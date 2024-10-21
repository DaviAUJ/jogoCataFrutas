package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.awt.*;

public class BarrinhaConfiguracoes extends JPanel {
    public JLabel texto;
    public JFormattedTextField valor;
    public JFormattedTextField valor2 = null;

    public BarrinhaConfiguracoes(ImageIcon imagemTextoBarrinha, boolean doisInputs) {

        if (doisInputs){
            EstiloComponentes.aplicarEstiloBarrinhaConfiguracoes2(this, imagemTextoBarrinha);
        }
        else{
            EstiloComponentes.aplicarEstiloBarrinhaConfiguracoes(this, imagemTextoBarrinha);
        }

    }


}
