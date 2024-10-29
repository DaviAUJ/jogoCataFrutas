package utilitarios;

import jogoCataFrutas.Jogo;
import frutas.Fruta;
import java.util.ArrayList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import elementos.Jogador;


/**
 * Classe que serve como transmissor de eventos dentro do jogo,
 * permitindo a comunicação entre diferentes componentes através
 * do padrão de observador.
 */
public abstract class Transmissor {
    private static Jogo JOGO;
    private static PropertyChangeSupport SUPORTE_MUDANCA = new PropertyChangeSupport(Jogo.class);

    /**
     * Define o jogo atual.
     *
     * @param jogo O objeto Jogo que representa a partida em andamento.
     */
    public static void setJogoDoMomento(Jogo jogo) {
        JOGO = jogo;
    }

    /**
     * Obtém o jogo atual.
     *
     * @return O objeto Jogo que representa a partida em andamento.
     */
    public static Jogo getJogoDoMomento() {
        return JOGO;
    }

    /**
     * Adiciona um listener para escutar eventos de mudança.
     *
     * @param listener O listener que será notificado sobre mudanças.
     */
    public static void adicionarEvento(PropertyChangeListener listener) {
        SUPORTE_MUDANCA.addPropertyChangeListener(listener);
    }

    /**
     * Solicita a criação de um novo jogo.
     */
    public static void solicitacaoNovoJogo() {
        SUPORTE_MUDANCA.firePropertyChange("solicitacaoNovoJogo", null, null);
    }

    /**
     * Inicia a partida atual.
     */
    public static void iniciarPartida() {
        SUPORTE_MUDANCA.firePropertyChange("iniciarPartida", null, null);
    }

    /**
     * Solicita a busca de salvamentos.
     */
    public static void buscarSalvamentos(){
        SUPORTE_MUDANCA.firePropertyChange("solicitacaoListaSalvamentos", null, null);
    }

    /**
     * Pede o movimento do jogador.
     *
     * @param entrada A entrada que representa o movimento do jogador.
     */
    public static void pedirMovJogador(String entrada) {
        SUPORTE_MUDANCA.firePropertyChange("pedirMovJogador", null, entrada);
    }

    /**
     * Pede para passar a rodada atual.
     */
    public static void pedirPassarRodada() {
        SUPORTE_MUDANCA.firePropertyChange("pedirPassarRodada", null, null);
    }

    /**
     * Notifica sobre a movimentação do jogador.
     *
     * @param antigos A lista de posições antigas do jogador.
     * @param novos A lista de novas posições do jogador.
     */
    public static void avisoMovimentacaoJogador(ArrayList<Integer> antigos, ArrayList<Integer> novos) {
        SUPORTE_MUDANCA.firePropertyChange("avisoMovimentacaoJogador", antigos, novos);
    }
    
    /**
     * Notifica sobre mudanças na mochila do jogador.
     *
     * @param fruta A classe da fruta que foi alterada.
     * @param quantidade A quantidade da fruta.
     * @param idJogador O ID do jogador que teve a mochila alterada.
     */
    public static void avisoMudouMochila(Class<?extends Fruta> fruta, int quantidade, int idJogador) {
    	HashMap <String, Object> info = new HashMap <>();
    	info.put("fruta", fruta);
    	info.put("quantidade", quantidade);
        info.put("jogador", idJogador);
    	SUPORTE_MUDANCA.firePropertyChange("avisoMudouMochila",null, info);
    }

    /**
     * Notifica sobre mudanças na fruta em uma posição específica.
     *
     * @param fruta A classe da fruta que foi alterada.
     * @param posX A posição X da fruta.
     * @param posY A posição Y da fruta.
     */
    public static void avisoMudancaFruta(Class<?extends Fruta> fruta, int posX, int posY) {
        HashMap <String, Object> info = new HashMap <>();
        info.put("classe", fruta);
        info.put("x", posX);
        info.put("y", posY);
        SUPORTE_MUDANCA.firePropertyChange("avisoMudancaFruta", null, info);
    }
    
    /**
     * Notifica sobre o início de uma nova rodada.
     *
     * @param rodadaAtual O número da rodada atual.
     */
    public static void avisoMudarRodada(int rodadaAtual) {
        SUPORTE_MUDANCA.firePropertyChange("avisoNovaRodada", null, rodadaAtual);
    }

    /**
     * Notifica sobre a troca de jogador.
     *
     * @param idAntigo O ID do jogador antigo.
     * @param idNovo O ID do novo jogador.
     */
    public static void avisoTrocaJogador(int idAntigo, int idNovo) {
        SUPORTE_MUDANCA.firePropertyChange("avisoTrocaJogador", idAntigo, idNovo);
    }

    /**
     * Notifica sobre a alteração de pontos de um jogador.
     *
     * @param novoValor O novo valor de pontos.
     * @param idJogador O ID do jogador cujos pontos foram alterados.
     */
    public static void avisoPontosAlterados(int novoValor, int idJogador) {
        HashMap<String, Integer> mapa = new HashMap<>();
        mapa.put("id", idJogador);
        mapa.put("pontos", novoValor);

        SUPORTE_MUDANCA.firePropertyChange("avisoPontosAlterados", null, mapa);
    }

    /**
     * Notifica que um jogador pegou uma fruta de uma árvore.
     *
     * @param ArvoreX A posição X da árvore.
     * @param ArvoreY A posição Y da árvore.
     */
    public static void avisoPegouFrutaArvore(int ArvoreX, int ArvoreY) {
    	HashMap <String, Integer> info = new HashMap <>();
    	info.put("posicao da arvore x", ArvoreX);
    	info.put("posicao ArvoreY", ArvoreY);
    	SUPORTE_MUDANCA.firePropertyChange("avisoPegouFrutaArvore", null, info );
    }

    /**
     * Notifica sobre uma situação de "bichada" para um jogador.
     *
     * @param jogador O jogador afetado pela bichada.
     */
    public static void avisoBichada(Jogador jogador) {
    	HashMap <String, Jogador> info = new HashMap <>();
    	info.put("jogador", jogador);
    	SUPORTE_MUDANCA.firePropertyChange("avisoBichada", null, info);
    }
    
    /**
     * Notifica sobre um efeito que afetou um jogador.
     *
     * @param jogador O jogador que recebeu o efeito.
     * @param tipoEfeito O tipo de efeito recebido (0: nerf, 1: mais pontos de ação, 2: mais força, 3: remover nerf).
     */
    public static void avisoEfeito(Jogador jogador, int tipoEfeito) { // 0 é o nerf, 1 é mais pontos de ação, 2 é o de mais força e 3 é o de tirar nerf
    	 HashMap <String, Object> info = new HashMap <>();
    	 info.put("jogador", jogador);
    	 info.put("tipoEfeito", tipoEfeito);
    	 // Jogador jogador2 =(Jogador) info.get("jogador");
    	SUPORTE_MUDANCA.firePropertyChange("avisoEfeito", null, info);
    }
    
    /**
     * Notifica que o jogo terminou e indica o vencedor.
     *
     * @param nomeVencedor O nome do jogador vencedor.
     */
    public static void avisoFimDeJogo(String nomeVencedor) {
    	SUPORTE_MUDANCA.firePropertyChange("avisoFimDeJogo", null, nomeVencedor);
    }
}
