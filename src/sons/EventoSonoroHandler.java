package sons;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 * Classe responsável por gerenciar eventos sonoros no jogo.
 */
public abstract class EventoSonoroHandler {
    private static PropertyChangeSupport SUPORTE_MUDANCA = new PropertyChangeSupport(EfeitoSonoro.class);

    /**
     * Adiciona um listener para mudanças de eventos sonoros.
     *
     * @param listener O listener a ser adicionado.
     */
    public static void adicionarEvento(PropertyChangeListener listener) {
        SUPORTE_MUDANCA.addPropertyChangeListener(listener);
    }

    /**
     * Dispara um evento sonoro vazio com o nome especificado.
     *
     * @param nome O nome do evento sonoro.
     */
    private static void dispararVazio(String nome) {
        SUPORTE_MUDANCA.firePropertyChange(nome, null, null);
    }

    /**
     * Dispara o som correspondente ao movimento para a esquerda.
     */
    public static void somAndarEsquerda() {
        dispararVazio("sfx" + File.separator + "Esquerda.wav");
    }

    /**
     * Dispara o som correspondente ao movimento para a direita.
     */
    public static void somAndarDireita() {
        dispararVazio("sfx" + File.separator + "Direita.wav");
    }

    /**
     * Dispara o som correspondente ao movimento para cima.
     */
    public static void somAndarCima() {
        dispararVazio("sfx" + File.separator + "Cima.wav");
    }

    /**
     * Dispara o som correspondente ao movimento para baixo.
     */
    public static void somAndarBaixo() {
        dispararVazio("sfx" + File.separator + "Baixo.wav");
    }

    /**
     * Dispara o som correspondente a um buff sendo aplicado.
     */
    public static void somBuff() {
        dispararVazio("sfx" + File.separator + "Buffado.wav");
    }

    /**
     * Dispara o som correspondente a um nerf sendo aplicado.
     */
    public static void somNerf() {
        dispararVazio("sfx" + File.separator + "Nerfado.wav");
    }

    /**
     * Dispara o som correspondente ao ato de comer uma fruta.
     */
    public static void somComer() {
        dispararVazio("sfx" + File.separator + "Comer.wav");
    }

    /**
     * Dispara o som correspondente ao ato de catar uma fruta.
     */
    public static void somCatar() {
        dispararVazio("sfx" + File.separator + "Frutinha.wav");
    }

    /**
     * Dispara o som correspondente ao hover do mouse.
     */
    public static void somMouseHover() {
        dispararVazio("sfx" + File.separator + "MouseHover.wav");
    }

    /**
     * Dispara o som correspondente à pontuação sendo obtida.
     */
    public static void somPontuacao() {
        dispararVazio("sfx" + File.separator + "Pontuação.wav");
    }

    /**
     * Dispara o som correspondente ao ato de pular.
     */
    public static void somPular() {
        dispararVazio("sfx" + File.separator + "Pular.wav");
    }

    /**
     * Dispara o som correspondente à vitória.
     */
    public static void somVitoria() {
        dispararVazio("sfx" + File.separator + "Vitoria.wav");
    }

    /**
     * Dispara o som correspondente a um empurrão.
     */
    public static void somEmpurrao() {
        dispararVazio("sfx" + File.separator + "Empurrão.wav");
    }


    /**
     * Dispara a música de fundo para a abstração.
     */
    public static void musicaAbstracao() {
        dispararVazio("msc" + File.separator + "Abstração.wav");
    }


    /**
     * Dispara a música de fundo para exceção.
     */
    public static void musicaExcecao() {
        dispararVazio("msc" + File.separator + "Exceção.wav");
    }
}
