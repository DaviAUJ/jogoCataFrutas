package elementos;

import frutas.Fruta;
import utilitarios.GerenciadorArquivo;
import excecoes.*;

import java.util.Random;

/**
 * Esta classe representa um jogador no jogo, contendo informações sobre sua
 * posição, pontos e estados.
 */

public class Jogador extends Elemento {
    private Mochila mochila;
    private Terreno local;

    private int pontosMovimento = 0;
    private String estado = "";

    private boolean buffForca = false;
    private boolean nerfBichada = false;
    private boolean jaSeMoveu = false;

    /** Construtor padrão da classe Jogador. */

    public Jogador() {

    }

    public Jogador(Terreno local) {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        this.local = local;

        mochila = new Mochila();
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

    public Mochila getMochila() {
        return mochila;
    }

    /**
     * Verifica se o jogador já se moveu.
     *
     * @return True se o jogador já se moveu, false caso contrário.
     */
    
    public boolean getJaSeMoveu() {
        return jaSeMoveu;
    }
    
    /**
     * Define se o jogador já se moveu.
     *
     * @param jaSeMoveu O novo estado de movimento do jogador.
     */
    
    public void setJaSeMoveu(boolean jaSeMoveu) {
        this.jaSeMoveu = jaSeMoveu;
    }
    
    /**
     * Reseta o estado de movimento do jogador para o início de uma nova rodada.
     */
    
    public void resetarMovimento() {
        setJaSeMoveu(false);
    }
    /**
     * Tenta catar uma fruta.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public void catarFruta() throws JogadorSemPontosDeMovimentacaoException, GramaSemFrutaException{
        if(pontosMovimento <=  0) {
            throw new JogadorSemPontosDeMovimentacaoException("O jogador não tem pontos de movimento");
        }

        Grama quadradinho;

        // Testando se é grama ou não
        try {
            quadradinho = (Grama) local.tabuleiro[posicaoX][posicaoY];

            if(!quadradinho.temFruta()){
                throw new GramaSemFrutaException("Grama sem fruta");
            }

            Fruta frutaColetada = quadradinho.getEspacoFruta();

            pontosMovimento--;
            mochila.guardar(frutaColetada);
            quadradinho.setEspacoFruta(null);

            // Tenta aplicar nerf no jogador
            frutaColetada.nerfar(this);
        }
        catch(Exception e) {
            System.out.println(e + "");
            return;
        }
    }

    /**
     * Tenta comer uma fruta.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public void comerFruta(Class<? extends Fruta> fruta) {
        try {
            Fruta frutaComida = mochila.tirar(fruta);

            frutaComida.buffar(this);
        }
        catch(Exception e) {
            System.out.println(e + "");
        }
    }
    
    public boolean pegarFrutaArvore() {
        // Verifica se o jogador já se moveu
        if (jaSeMoveu) {
            System.out.println("Você já se moveu e não pode pegar a fruta da árvore.");
            return false;
        }

        // Verifica se o jogador está em uma posição de árvore
        ElementoEstatico elementoAtual = local.tabuleiro[posicaoX][posicaoY];
        if (!(elementoAtual instanceof Arvore)) {
            System.out.println("Você não está em uma árvore.");
            return false;
        }

        Arvore arvore = (Arvore) elementoAtual;

        // Pega o tipo da fruta da árvore
        Class<? extends Fruta> tipoFruta = arvore.getTipo();
        if (tipoFruta == null) { // Essa verficação me aparenta ser meio inutil agora que vi, mas eu acho q ta massa e ta compilando...
            System.out.println("A árvore não possui frutas.");
            return false;
        }

        // Cria uma nova instância da fruta do tipo da árvore
        Fruta novaFruta;
        try {
            novaFruta = tipoFruta.getDeclaredConstructor(String.class).newInstance(tipoFruta.getSimpleName() + " " + (mochila.quantidadeFrutas() + 1));
        } catch (Exception e) {
            System.out.println("Não foi possível instanciar a fruta: " + e.getMessage());
            return false;
        }

        // Adiciona a nova fruta à mochila
        try {
            mochila.colocar(novaFruta);
            System.out.println("Você pegou uma " + novaFruta.getNome() + "!");
        } catch (MochilaCheiaException e) {
            System.out.println("A mochila está cheia: " + e.getMessage());
            return false;
        }

        return true; // Retorna true se a fruta foi pegada com sucesso
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

    public char moverLivre(int posX, int posY) {
    	try {
	        if(!(local.tabuleiro[posX][posY] instanceof ElementoEstaticoPisavel)) {
	            return 'p';
	        }
	        
	        if(((ElementoEstaticoPisavel)local.tabuleiro[posX][posY]).temJogador()) {
	        	return 'j';
	        }
	        
        	// Trocando a posição do jogador
        	((ElementoEstaticoPisavel) local.tabuleiro[posX][posY]).setJogador(this);
        	((ElementoEstaticoPisavel) local.tabuleiro[this.posicaoX][this.posicaoY]).setJogador(null);
        	this.posicaoX = posX;
        	this.posicaoY = posY;

            setJaSeMoveu(true);
        	return 's';	
        }
        catch(Exception e) {
        	System.out.println("Tentando mover o jogador fora do campo: " + e);
        	
        	return 'n';
        }
    }

    /**
     * Move o jogador para cima.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverCima() {
    	char tentativa = 'n';

        if(pontosMovimento == 0) {
            return false;
        }

        tentativa = moverLivre(posicaoX - 1, posicaoY);

    	switch (tentativa) {
            case 's':
                pontosMovimento--;
                return true;
            case 'j':
                //empurrar
                return false;
            case 'p':
                if(pontosMovimento < 3) {
                    return false;
                }
                tentativa = moverLivre(posicaoX - 2, posicaoY);
        }

        if(tentativa == 's') {
            pontosMovimento -= 3;
        }

        return tentativa == 's';
    }

    /**
     * Move o jogador para baixo.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverBaixo() {
        char tentativa = 'n';

        if(pontosMovimento == 0) {
            return false;
        }

        tentativa = moverLivre(posicaoX + 1, posicaoY);

        switch (tentativa) {
            case 's':
                pontosMovimento--;
                return true;
            case 'j':
                //empurrar
                return false;
            case 'p':
                if(pontosMovimento < 3) {
                    return false;
                }
                tentativa = moverLivre(posicaoX + 2, posicaoY);
        }

        if(tentativa == 's') {
            pontosMovimento -= 3;
        }

        return tentativa == 's';
    }

    /**
     * Move o jogador para a esquerda.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverEsquerda() {
        char tentativa = 'n';

        if(pontosMovimento == 0) {
            return false;
        }

        tentativa = moverLivre(posicaoX, posicaoY - 1);

        switch (tentativa) {
            case 's':
                pontosMovimento--;
                return true;
            case 'j':
                //empurrar
                return false;
            case 'p':
                if(pontosMovimento < 3) {
                    return false;
                }
                tentativa = moverLivre(posicaoX, posicaoY - 2);
        }

        if(tentativa == 's') {
            pontosMovimento -= 3;
        }

        return tentativa == 's';
    }

    /**
     * Move o jogador para a direita.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean moverDireita() {
        char tentativa = 'n';

        if(pontosMovimento == 0) {
            return false;
        }

        tentativa = moverLivre(posicaoX, posicaoY + 1);

        switch (tentativa) {
            case 's':
                pontosMovimento--;
                return true;
            case 'j':
                //empurrar
                return false;
            case 'p':
                if(pontosMovimento < 3) {
                    return false;
                }
                tentativa = moverLivre(posicaoX, posicaoY + 2);
        }

        if(tentativa == 's') {
            pontosMovimento -= 3;
        }

        return tentativa == 's';
    }

    /** Gera pontos para o jogador (a implementação ainda não existe). */

    public void gerarPontos() {
        Random gerador = new Random();

        pontosMovimento = gerador.nextInt(6) + gerador.nextInt(6) + 2;
    }
}
