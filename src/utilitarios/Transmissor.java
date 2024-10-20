package utilitarios;

import jogoCataFrutas.Jogo;
import visao.GerenciadorDeTelas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class Transmissor {
    private HashMap<String, Object> dados;
    private GerenciadorDeTelas gerenciador;
    private Jogo jogo;
    private PropertyChangeSupport suporteAMudanca;

    public Transmissor() {
        this.dados = new HashMap<>();
        this.suporteAMudanca = new PropertyChangeSupport(this);
    }

    public void setGerenciador(GerenciadorDeTelas gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void setJogoDoMomento(Jogo jogo) {
        this.jogo = jogo;
    }

    public Jogo getJogoDoMomento() {
        return this.jogo;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        suporteAMudanca.addPropertyChangeListener(listener);
    }

    public void solicitacaoNovoJogo() {
        this.suporteAMudanca.firePropertyChange("solicitacaoNovoJogo", null, dados);
    }


}
