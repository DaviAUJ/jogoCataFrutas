package elementos;

import frutas.Fruta;

/**
 * Esta classe representa um jogador no jogo, contendo informações sobre sua
 * posição, pontos e estados.
 */

public class Jogador extends Elemento {
    private int pontosMovimento = 0;
    private Mochila mochila = new Mochila();
    private int pontosOuro = 0;
    private String estado = "";
    private boolean buffForca = false;
    private boolean buffAgilidade = false;
    private boolean nerfBichada = false;

    /** Construtor padrão da classe Jogador. */

    public Jogador() {

    }

    /**
     * Construtor da classe Jogador que define o nome e a posição do jogador.
     *
     * @param nome O nome do jogador.
     * @param posX A coordenada x da posição do jogador.
     * @param posY A coordenada y da posição do jogador.
     */

    public Jogador(String nome, int posX, int posY) {
    	this.nome = nome;
    	this.posicaoX = posX;
    	this.posicaoY = posY;
    }

    /**
     * Retorna os pontos de movimento do jogador.
     *
     * @return Os pontos de movimento.
     */

    public int getPontosMovimento() {
        return pontosMovimento;
    }

    /**
     * Define os pontos de movimento do jogador.
     *
     * @param pontosMovimento Os novos pontos de movimento.
     */

    public void setPontosMovimento(int pontosMovimento) {
        this.pontosMovimento = pontosMovimento;
    }

    /**
     * Retorna os pontos de ouro do jogador.
     *
     * @return Os pontos de ouro.
     */

    public int getPontosOuro() {
        return pontosOuro;
    }

    /**
     * Define os pontos de ouro do jogador.
     *
     * @param pontosOuro Os novos pontos de ouro.
     */

    public void setPontosOuro(int pontosOuro) {
        this.pontosOuro = pontosOuro;
    }

    /**
     * Retorna o estado atual do jogador.
     *
     * @return O estado do jogador.
     */

    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado do jogador.
     *
     * @param estado O novo estado do jogador.
     */

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Verifica se o jogador possui o buff de força.
     *
     * @return True se o buff de força estiver ativo, false caso contrário.
     */

    public boolean getBuffForca() {
        return buffForca;
    }

    /**
     * Define se o buff de força está ativo.
     *
     * @param buffForca O novo estado do buff de força.
     */

    public void setBuffForca(boolean buffForca) {
        this.buffForca = buffForca;
    }

    /**
     * Verifica se o jogador possui o buff de agilidade.
     *
     * @return True se o buff de agilidade estiver ativo, false caso contrário.
     */

    public boolean getBuffAgilidade() {
        return buffAgilidade;
    }

    /**
     * Define se o buff de agilidade está ativo.
     *
     * @param buffAgilidade O novo estado do buff de agilidade.
     */

    public void setBuffAgilidade(boolean buffAgilidade) {
        this.buffAgilidade = buffAgilidade;
    }

    /**
     * Verifica se o jogador está sob o nerf de bichada.
     *
     * @return True se o nerf de bichada estiver ativo, false caso contrário.
     */

    public boolean getNerfBichada() {
        return nerfBichada;
    }

    /**
     * Define se o nerf de bichada está ativo.
     *
     * @param nerfBichada O novo estado do nerf de bichada.
     */

    public void setNerfBichada(boolean nerfBichada) {
        this.nerfBichada = nerfBichada;
    }

    /**
     * Tenta catar uma fruta.
     *
     * @param fruta A fruta a ser catada.
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean catarFruta(Fruta fruta) {
        return false;
    }

    /**
     * Tenta comer uma fruta.
     *
     * @param fruta A fruta a ser comida.
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean comerFruta(Fruta fruta) {
        return false;
    }

    /**
     * Tenta empurrar outro jogador.
     *
     * @param alvo O jogador a ser empurrado.
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean empurrar(Jogador alvo) {
        return false;
    }

    /**
     * Move o jogador para cima.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverCima() {
        return false;
    }

    /**
     * Move o jogador para baixo.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverBaixo() {
        return false;
    }

    /**
     * Move o jogador para a esquerda.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverEsquerda() {
        return false;
    }

    /**
     * Move o jogador para a direita.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverDireita() {
        return false;
    }

    /** Gera pontos para o jogador (a implementação ainda não existe). */

    public void gerarPontos() {
    }
}
