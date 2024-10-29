package visao;

import jogoCataFrutas.Configuracoes;
import sons.Tocador;
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

    /**
     * Construtor da classe VisaoPrincipal.
     * Inicializa a janela e configura os listeners de teclado.
     */
    public VisaoPrincipal() {
        setLocationRelativeTo(null);
        this.gerenciador = null;
        addKeyListener(this);

        configurarListeners();
    }

    /**
     * Define o gerenciador de telas para esta visão.
     *
     * @param gerenciador O gerenciador de telas a ser configurado.
     */
    public void setGerenciador(GerenciadorDeTelas gerenciador) {
        this.gerenciador = gerenciador;
    }

    /**
     * Retorna o gerenciador de telas associado a esta visão.
     *
     * @return O gerenciador de telas.
     */
    public GerenciadorDeTelas getGerenciador() {
        return gerenciador;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(ativarControles && !Configuracoes.tipo.equals("preview")) {
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

    /**
     * Configura os listeners para eventos do jogo.
     * Ativa ou desativa os controles com base em eventos recebidos.
     */
    public void configurarListeners() {
        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("iniciarPartida")) {
                    ativarControles = true;
                }
            }
        });

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("avisoFimDeJogo")) {
                    ativarControles = false;
                }
            }
        });
    }
}
