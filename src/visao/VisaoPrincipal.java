package visao;

import visao.estilos.Estilos;

import javax.swing.*;

/**
 * Classe que representa a janela principal do jogo Cata-Frutas.
 * Esta classe estende JFrame e configura a janela do jogo.
 */

public class VisaoPrincipal extends JFrame {

    public VisaoPrincipal() {
        setLocationRelativeTo(null);
    }


    public void predefInicio(){
        Estilos.principal(this);
    }
}
