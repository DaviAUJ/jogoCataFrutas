package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa uma barrinha de configuração na interface do usuário.
 * Esta classe estende JPanel e pode conter um ou dois campos de entrada formatados.
 */
public class BarrinhaConfiguracoes extends JPanel {
    public JLabel texto;
    public JFormattedTextField valor;
    public JFormattedTextField valor2 = null;

    /**
     * Construtor da classe BarrinhaConfiguracoes.
     * Configura a barrinha com base na quantidade de campos de entrada.
     *
     * @param imagemTextoBarrinha A imagem do texto a ser exibido na barrinha.
     * @param doisInputs Indica se a barrinha deve ter dois campos de entrada.
     */
    public BarrinhaConfiguracoes(ImageIcon imagemTextoBarrinha, boolean doisInputs) {

        if (doisInputs){
            EstiloComponentes.aplicarEstiloBarrinhaConfiguracoes2(this, imagemTextoBarrinha);
        }
        else{
            EstiloComponentes.aplicarEstiloBarrinhaConfiguracoes(this, imagemTextoBarrinha);
        }

    }


}
