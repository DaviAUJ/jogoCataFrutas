package elementos;

import SemUtilidade.JogadorNerfadoException;
import frutas.Fruta;
import utilitarios.Extras;
import utilitarios.GerenciadorArquivo;
import utilitarios.Transmissor;
import excecoes.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Esta classe representa um jogador no jogo, contendo informações sobre sua
 * posição, pontos e estados.
 */

public class Jogador extends Elemento {
    private Mochila mochila;
    private Terreno local;

    private int pontosMovimento = 0;

    private boolean buffForca = false;
    private boolean nerfBichada = false;
    private boolean jaSeMoveu = false;
    private boolean jaFoiEmpurrado;

    private final HashMap<Arvore, Integer> arvoresEmCooldown = new HashMap<>();

    /** Construtor padrão da classe Jogador. */

    public Jogador() {

    }

    public Jogador(Terreno local) {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        this.local = local;

        mochila = new Mochila();
    }

    public Mochila abrirMochila() {
        return mochila;
    }

    public void setJaFoiEmpurrado(boolean jaFoiEmpurrado) {
        this.jaFoiEmpurrado = jaFoiEmpurrado;
    }

    public int getForca() {
        if(buffForca) {
            return mochila.getQuantFrutas() * 2;
        }

        return mochila.getQuantFrutas();
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

    public int getPontosOuro() {
        return mochila.getQuantMaracujas();
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

    public boolean semPontosMovimento() {
        return pontosMovimento == 0;
    }

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
    
    public void pegarFrutaArvore() throws
            JogadorJaSeMovimentouException, JogadorForaDeArvoreException,
            ClasseNaoInstanciadaException, ArvoreEmCooldownException {
        // Verifica se o jogador já se moveu
        if (jaSeMoveu) {
            throw new JogadorJaSeMovimentouException("");
        }

        // Verifica se o jogador está em uma posição de árvore
        ElementoEstatico elementoAtual = local.tabuleiro[posicaoX][posicaoY];
        if (!(elementoAtual instanceof Arvore)) {
            throw new JogadorForaDeArvoreException("");
        }

        Arvore arvore = (Arvore) elementoAtual;
        Class<? extends Fruta> tipoFruta = arvore.getTipo();

        if(arvoresEmCooldown.containsKey(arvore)) {
            throw new ArvoreEmCooldownException("");
        }

        // Cria uma nova instância da fruta do tipo da árvore
        Fruta novaFruta;
        try {
            novaFruta = tipoFruta.getDeclaredConstructor(String.class).newInstance(tipoFruta.getSimpleName()
                    + " " +
                    (mochila.getQuantFrutas() + 1)
            );
        } catch (Exception e) {
            throw new ClasseNaoInstanciadaException("Não foi possível instanciar a fruta");
        }

        // Adiciona a nova fruta à mochila
        mochila.guardar(novaFruta);

        arvoresEmCooldown.put(arvore, 5);
    }

    /**
     * Tenta empurrar outro jogador.
     *
     * @param alvo O jogador a ser empurrado.
     * @return True se a ação for bem-sucedida, false caso contrário.
     */
    public void empurrar(Jogador alvo) throws ForcaInsuficienteException, jaEmpurraramNoJogadorException {
        if(getForca() <= alvo.getForca()) {
            throw new ForcaInsuficienteException("Forca insuficiente");
        }

        if(alvo.jaFoiEmpurrado) {
            throw new jaEmpurraramNoJogadorException("");
        }

        pontosMovimento--;

        int numFrutas = (int) Math.round(Extras.logb(2, getForca() + 1));
        numFrutas -= (int) Math.round(Extras.logb(2, alvo.getForca() + 1));
        numFrutas = Math.max(0, numFrutas);

        alvo.derrubarFrutas(numFrutas);
        System.out.println(numFrutas);
    }

    public void moverLivre(int posX, int posY)
            throws MovimentoParaEspacoComPlayerException,
            MovimentoParaEspacoComPedraException,
            JogadorForaDoCampoException {

        if(posX < 0 || posX >= local.getDimensao() || posY < 0 || posY >= local.getDimensao()) {
            throw new JogadorForaDoCampoException("");
        }

        if(!(local.tabuleiro[posX][posY] instanceof ElementoEstaticoPisavel)) {
            throw new MovimentoParaEspacoComPedraException("");
        }

        if(((ElementoEstaticoPisavel)local.tabuleiro[posX][posY]).temJogador()) {
            throw new MovimentoParaEspacoComPlayerException("");
        }

        Transmissor.avisoMovimentacaoJogador(new int[]{posicaoX,posicaoY},new int[] {posX,posY});
        
        // Trocando a posição do jogador
        ((ElementoEstaticoPisavel) local.tabuleiro[posX][posY]).setJogador(this);
        ((ElementoEstaticoPisavel) local.tabuleiro[this.posicaoX][this.posicaoY]).setJogador(null);
        this.posicaoX = posX;
        this.posicaoY = posY;

        setJaSeMoveu(true);
    }

    /**
     * Move o jogador para cima.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public void moverCima() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX - 1, posicaoY);
            pontosMovimento--;
        }
        catch(JogadorForaDoCampoException | JogadorNerfadoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX - 2, posicaoY);
                pontosMovimento -= 3;
            }
            catch(MovimentoParaEspacoComPlayerException _) {  }
        }
        catch(MovimentoParaEspacoComPlayerException e) {
            try {
                empurrar(((ElementoEstaticoPisavel) local.getTabuleiro()[posicaoX - 1][posicaoY]).espacoJogador);
            }
            catch(ForcaInsuficienteException _) {  }
        }
    }

    /**
     * Move o jogador para baixo.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public void moverBaixo() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX + 1, posicaoY);
            pontosMovimento--;
        }
        catch(JogadorForaDoCampoException | JogadorNerfadoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX + 2, posicaoY);
                pontosMovimento -= 3;
            }
            catch(MovimentoParaEspacoComPlayerException _) {  }
        }
        catch(MovimentoParaEspacoComPlayerException e) {
            try {
                empurrar(((ElementoEstaticoPisavel) local.getTabuleiro()[posicaoX + 1][posicaoY]).espacoJogador);
            }
            catch(ForcaInsuficienteException _) {  }
        }
    }

    /**
     * Move o jogador para a esquerda.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public void moverEsquerda() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX, posicaoY - 1);
            pontosMovimento--;
        }
        catch(JogadorForaDoCampoException | JogadorNerfadoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX, posicaoY - 2);
                pontosMovimento -= 3;
            }
            catch(MovimentoParaEspacoComPlayerException _) {  }
        }
        catch(MovimentoParaEspacoComPlayerException e) {
            try {
                empurrar(((ElementoEstaticoPisavel) local.getTabuleiro()[posicaoX][posicaoY - 1]).espacoJogador);
            }
            catch(ForcaInsuficienteException _) {  }
        }
    }

    /**
     * Move o jogador para a direita.
     *
     * @return True se a ação for bem-sucedida, false caso contrário.
     */

    public void moverDireita() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX, posicaoY + 1);
            pontosMovimento--;
        }
        catch(JogadorForaDoCampoException | JogadorNerfadoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX, posicaoY + 2);
                pontosMovimento -= 3;
            }
            catch(MovimentoParaEspacoComPlayerException _) {  }
        }
        catch(MovimentoParaEspacoComPlayerException e) {
            try {
                empurrar(((ElementoEstaticoPisavel) local.getTabuleiro()[posicaoX][posicaoY + 1]).espacoJogador);
            }
            catch(ForcaInsuficienteException _) {  }
        }
    }

    /** Gera pontos para o jogador (a implementação ainda não existe). */

    public void gerarPontos() {
        Random gerador = new Random();

        pontosMovimento = gerador.nextInt(6) + gerador.nextInt(6) + 2;
        pontosMovimento *= 10;
    }

    public void atualizarCooldowns() {
        for(Arvore arvore : arvoresEmCooldown.keySet()) {
            arvoresEmCooldown.put(arvore, arvoresEmCooldown.get(arvore) - 1);

            if(arvoresEmCooldown.get(arvore) == 0) {
                arvoresEmCooldown.remove(arvore);
            }
        }
    }

    public void derrubarFrutas(int quantidade) {
        Random gerador = new Random();
        LinkedList<Grama> espacosVazios = new LinkedList<>();
        Grama temp;

        for(int x = -2; x <= 2; x++) {
            for(int y = -2; y <= 2; y++) {
                try {
                    temp = (Grama) local.getTabuleiro()[posicaoX + x][posicaoY + y];

                    if(!temp.temFruta()) {
                        espacosVazios.add(temp);
                    }
                }
                catch(Exception _) {  }
            }
        }

        LinkedList<Class<? extends Fruta>> frutasDisponiveis = new LinkedList<>();

        for(Class<? extends Fruta> classe : mochila.getBolso().keySet()) {
            if(!mochila.getBolso().get(classe).isEmpty()) {
                frutasDisponiveis.add(classe);
            }
        }

        while(quantidade > 0 && mochila.getQuantFrutas() > 0) {
            int num = gerador.nextInt(frutasDisponiveis.size());
            Class<? extends Fruta> escolhida = frutasDisponiveis.get(num);

            try {
                espacosVazios.get(gerador.nextInt(espacosVazios.size())).setEspacoFruta(mochila.tirar(escolhida));
                quantidade--;
            }
            catch(BolsoFrutaVazioException e) {
                frutasDisponiveis.remove(escolhida);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
