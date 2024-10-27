package visao;

import utilitarios.Transmissor;
import visao.estilos.Estilos;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Classe que representa a janela principal do jogo Cata-Frutas.
 * Esta classe estende JFrame e configura a janela do jogo.
 */

public class VisaoPrincipal extends JFrame implements KeyListener {
    private GerenciadorDeTelas gerenciador;
    private boolean ativarControles;

    public VisaoPrincipal() {
        setLocationRelativeTo(null);
        this.gerenciador = null;
        addKeyListener(this);

        configurarListeners();
    }

    public void setGerenciador(GerenciadorDeTelas gerenciador) {
        this.gerenciador = gerenciador;
    }

    public GerenciadorDeTelas getGerenciador() {
        return gerenciador;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(ativarControles) {
            String c = KeyEvent.getKeyText(e.getKeyCode());
            Transmissor.pedirMovJogador(KeyEvent.getKeyText(e.getKeyCode()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void configurarListeners() {
        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("iniciarPartida")) {
                    ativarControles = true;
                }
            }
        });
    }
}
