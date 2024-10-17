package elementos;

import frutas.Fruta;
import utilitarios.FixedStack;
import utilitarios.GerenciadorArquivo;

import java.util.Random;

/**
 * Esta classe representa um jogador no jogo, contendo informações sobre sua
 * posição, pontos e estados.
 */

public class Jogador extends Elemento {
    private FixedStack<Fruta> mochila;
    private Terreno local;

    private int pontosMovimento = 3;
    private int pontosOuro = 0;
    private String estado = "";

    private boolean buffForca = false;
    private boolean buffAgilidade = false;
    private boolean nerfBichada = false;

    /** Construtor padrão da classe Jogador. */

    public Jogador() {

    }

    public Jogador(Terreno local) {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        this.local = local;

        mochila = new FixedStack<Fruta>(arquivo.pegarEspacoMochila());
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
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean catarFruta() {
        if(pontosMovimento <=  0 || mochila.isFull()) {
            return false;
        }

        Grama quadradinho;

        // Testando se é grama ou não
        try {
            quadradinho = (Grama) local.tabuleiro[posicaoX][posicaoY];
        }
        catch(Exception e) {
            return false;
        }

        if(!quadradinho.temFruta()){
            return false;
        }

        pontosMovimento--;
        mochila.push(quadradinho.getEspacoFruta());
        quadradinho.setEspacoFruta(null);

        return true;
    }

    /**
     * Tenta comer uma fruta.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public boolean comerFruta() {
        try {
            Fruta frutaComida = mochila.pop();

            frutaComida.buffar(this);
            frutaComida.nerfar(this);
        }
        catch(Exception e) {
            return false;
        }

        return true;
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
