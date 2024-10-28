package sons;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

public abstract class EventoSonoroHandler {
    private static PropertyChangeSupport SUPORTE_MUDANCA = new PropertyChangeSupport(EfeitoSonoro.class);

    public static void adicionarEvento(PropertyChangeListener listener) {
        SUPORTE_MUDANCA.addPropertyChangeListener(listener);
    }

    private static void dispararVazio(String nome) {
        SUPORTE_MUDANCA.firePropertyChange(nome, null, null);
    }

    public static void somAndarEsquerda() {
        dispararVazio("sfx" + File.separator + "Esquerda.wav");
    }

    public static void somAndarDireita() {
        dispararVazio("sfx" + File.separator + "Direita.wav");
    }

    public static void somAndarCima() {
        dispararVazio("sfx" + File.separator + "Cima.wav");
    }

    public static void somAndarBaixo() {
        dispararVazio("sfx" + File.separator + "Baixo.wav");
    }

    public static void somBuff() {
        dispararVazio("sfx" + File.separator + "Buffado.wav");
    }

    public static void somNerf() {
        dispararVazio("sfx" + File.separator + "Nerfado.wav");
    }

    public static void somComer() {
        dispararVazio("sfx" + File.separator + "Comer.wav");
    }

    public static void somCatar() {
        dispararVazio("sfx" + File.separator + "Frutinha.wav");
    }

    public static void somMouseHover() {
        dispararVazio("sfx" + File.separator + "MouseHover.wav");
    }

    public static void somPontuacao() {
        dispararVazio("sfx" + File.separator + "Pontuação.wav");
    }

    public static void somPular() {
        dispararVazio("sfx" + File.separator + "Pular.wav");
    }

    public static void somVitoria() {
        dispararVazio("sfx" + File.separator + "Vitoria.wav");
    }

    public static void somEmpurrao() {
        dispararVazio("sfx" + File.separator + "Empurrão.wav");
    }

    public static void musicaAbstracao() {
        dispararVazio("msc" + File.separator + "Abstração.wav");
    }

    public static void musicaExcecao() {
        dispararVazio("msc" + File.separator + "Exceção.wav");
    }
}
