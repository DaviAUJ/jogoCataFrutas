package utilitarios;

import jogoCataFrutas.Jogo;
import frutas.Fruta;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import elementos.Jogador;

public abstract class Transmissor {
    private static HashMap<String, Object> DADOS = new HashMap<>();
    private static Jogo JOGO;
    private static PropertyChangeSupport SUPORTE_MUDANCA = new PropertyChangeSupport(Jogo.class);

    public static void setJogoDoMomento(Jogo jogo) {
        JOGO = jogo;
    }

    public static Jogo getJogoDoMomento() {
        return JOGO;
    }

    public static void adicionarEvento(PropertyChangeListener listener) {
        SUPORTE_MUDANCA.addPropertyChangeListener(listener);
    }

    public static void solicitacaoNovoJogo() {
        SUPORTE_MUDANCA.firePropertyChange("solicitacaoNovoJogo", null, DADOS);
    }

    public static void buscarSalvamentos(){
        SUPORTE_MUDANCA.firePropertyChange("solicitacaoListaSalvamentos", null, DADOS);
    }

    public static void adicionarDados(String identificador, Object dados) {
        if (!DADOS.containsKey(identificador)) {
            DADOS.put(identificador, dados);
        }
    }

    public static void alterarDados(String identificador, Object dados) {
        if (DADOS.containsKey(identificador)) {
            DADOS.put(identificador, dados);
        }
        else{
            System.out.println("Identificador não encontrado!");
        }
    }

    public static Object getDados(String identificador) {
        return DADOS.get(identificador);
    }

    public static void removerDados(String identificador) {
        DADOS.remove(identificador);
    }

    public static void avisoMovimentacaoJogador(int[] antigos, int[] novos) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoMovimentacaoJogador", antigos, novos);
    }
    
    public static void avisoMudouMochila(Class<?extends Fruta> fruta, int quantidade) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoMudouMochila",fruta, quantidade);
    }
    
    public static void avisoPasseiRodada() {
        SUPORTE_MUDANCA.firePropertyChange("passarRodada", null, null);
    }

    public static void avisoApareceuNoJogo(String nome, int posX, int posY) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoApareceuNoJogo", null, null);
    }
    
    public static void avisoPegouFrutaArvore(int posX, int posY) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoPegouFrutaArvore", null, null);
    }

    public static void avisoBichada(Jogador jogador) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoBichada", null, null);
    }
    
    public static void avisoEfeito(Jogador jogador) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoEfeito", null, null);
    }
    
    public static void avisoFimDeJogo(Jogador jogador) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoFimDeJogo", null, null);
    }
}
