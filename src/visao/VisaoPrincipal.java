package visao;

import visao.estilos.Estilos;

import javax.swing.*;

/**
 * Classe que representa a janela principal do jogo Cata-Frutas.
 * Esta classe estende JFrame e configura a janela do jogo.
 */

public class VisaoPrincipal extends JFrame {
    private GerenciadorDeTelas gerenciador;

    public VisaoPrincipal() {
        setLocationRelativeTo(null);
        this.gerenciador = null;
    }

    public void setGerenciador(GerenciadorDeTelas gerenciador) {
        this.gerenciador = gerenciador;
    }

    public GerenciadorDeTelas getGerenciador() {
        return gerenciador;
    }
}
