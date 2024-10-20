package utilitarios;

import jogoCataFrutas.Jogo;
import visao.GerenciadorDeTelas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
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

    public void buscarSalvamentos(){
        this.suporteAMudanca.firePropertyChange("solicitacaoListaSalvamentos", null, dados);
    }

    public void adicionarDados(String identificador, Object dados) {
        if (!this.dados.containsKey(identificador)) {
            this.dados.put(identificador, dados);
        }
    }

    public void alterarDados(String identificador, Object dados) {
        if (this.dados.containsKey(identificador)) {
            this.dados.put(identificador, dados);
        }
        else{
            System.out.println("Identificador não encontrado!");
        }
    }

    public Object getDados(String identificador) {
        return this.dados.get(identificador);
    }

    public void removerDados(String identificador) {
        this.dados.remove(identificador);
    }


}
