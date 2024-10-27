package utilitarios;

import jogoCataFrutas.Jogo;
import frutas.Fruta;
import java.util.ArrayList;

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

    public static void pedirMovJogador(String entrada) {
        SUPORTE_MUDANCA.firePropertyChange("pedirMovJogador", null, entrada);
    }

    public static void avisoMovimentacaoJogador(ArrayList<Integer> antigos, ArrayList<Integer> novos) {
        SUPORTE_MUDANCA.firePropertyChange("avisoMovimentacaoJogador", antigos, novos);
    }
    
    public static void avisoMudouMochila(Class<?extends Fruta> fruta, int quantidade) {
    	HashMap <String, Object> info = new HashMap <>();
    	info.put("fruta", fruta);
    	info.put("quantidade", quantidade);
    	SUPORTE_MUDANCA.firePropertyChange("avisoMudouMochila",null, info);
    }
    
    public static void avisoPasseiTurno(int rodadaAtual, Jogador jogadorDaVez) {
    	HashMap <String, Object> info = new HashMap <>();
    	info.put("rodada atual", rodadaAtual);
    	info.put("jogador", jogadorDaVez);
        SUPORTE_MUDANCA.firePropertyChange("avisoPasseiTurno", null, info);
    }

    public static void avisoApareceuNoJogo(String nome, int posX, int posY) {
    	HashMap <String, Object> info = new HashMap <>();
    	info.put("nome", nome);
    	info.put("posicao x", posX);
    	info.put("posicao y", posY);
    	SUPORTE_MUDANCA.firePropertyChange("avisoApareceuNoJogo", null, info);
    }
    
    public static void avisoPegouFrutaArvore(int ArvoreX, int ArvoreY) {
    	HashMap <String, Integer> info = new HashMap <>();
    	info.put("posicao da arvore x", ArvoreX);
    	info.put("posicao ArvoreY", ArvoreY);
    	SUPORTE_MUDANCA.firePropertyChange("avisoPegouFrutaArvore", null, info );
    }

    public static void avisoBichada(Jogador jogador) {
    	HashMap <String, Jogador> info = new HashMap <>();
    	info.put("jogador", jogador);
    	SUPORTE_MUDANCA.firePropertyChange("avisoBichada", null, info);
    }
    
    public static void avisoEfeito(Jogador jogador, int tipoEfeito) { // 0 é o nerf, 1 é mais pontos de ação, 2 é o de mais força e 3 é o de tirar nerf
    	 HashMap <String, Object> info = new HashMap <>();
    	 info.put("jogador", jogador);
    	 info.put("tipoEfeito", tipoEfeito);
    	 // Jogador jogador2 =(Jogador) info.get("jogador");
    	SUPORTE_MUDANCA.firePropertyChange("avisoEfeito", null, info);
    }
    
    public static void avisoFimDeJogo(Jogador jogador) {
    	HashMap <String, Jogador> info = new HashMap <>();
    	info.put("jogador", jogador);
    	SUPORTE_MUDANCA.firePropertyChange("avisoFimDeJogo", null, jogador);
    }
}
