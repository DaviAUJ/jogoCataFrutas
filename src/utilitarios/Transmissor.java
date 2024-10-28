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
        SUPORTE_MUDANCA.firePropertyChange("solicitacaoNovoJogo", null, null);
    }

    public static void iniciarPartida() {
        SUPORTE_MUDANCA.firePropertyChange("iniciarPartida", null, null);
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
    
    public static void avisoMudouMochila(Class<?extends Fruta> fruta, int quantidade, int idJogador) {
    	HashMap <String, Object> info = new HashMap <>();
    	info.put("fruta", fruta);
    	info.put("quantidade", quantidade);
        info.put("jogador", idJogador);
    	SUPORTE_MUDANCA.firePropertyChange("avisoMudouMochila",null, info);
    }

    public static void avisoMudancaFruta(Class<?extends Fruta> fruta, int posX, int posY) {
        HashMap <String, Object> info = new HashMap <>();
        info.put("classe", fruta);
        info.put("x", posX);
        info.put("y", posY);
        SUPORTE_MUDANCA.firePropertyChange("avisoMudancaFruta", null, info);
    }
    
    public static void avisoMudarRodada(int rodadaAtual) {
        SUPORTE_MUDANCA.firePropertyChange("avisoNovaRodada", null, rodadaAtual);
    }

    public static void avisoTrocaJogador(int idAntigo, int idNovo) {
        SUPORTE_MUDANCA.firePropertyChange("avisoTrocaJogador", idAntigo, idNovo);
    }

    public static void avisoPontosAlterados(int novoValor, int idJogador) {
        HashMap<String, Integer> mapa = new HashMap<>();
        mapa.put("id", idJogador);
        mapa.put("pontos", novoValor);

        SUPORTE_MUDANCA.firePropertyChange("avisoPontosAlterados", null, mapa);
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
    
    public static void avisoFimDeJogo(String nomeVencedor) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoFimDeJogo", null, nomeVencedor);
    }
}
