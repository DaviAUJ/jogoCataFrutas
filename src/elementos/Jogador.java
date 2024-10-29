package elementos;

import frutas.Fruta;
import frutas.Maracuja;
import sons.EventoSonoroHandler;
import utilitarios.Extras;
import utilitarios.GerenciadorArquivo;
import utilitarios.Transmissor;
import excecoes.*;

import java.util.*;

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
    public Jogador(Terreno local) {
        this.local = local;

        mochila = new Mochila(this);
    }

    /**
     * Retorna o ID do jogador baseado na sua posição no terreno.
     *
     * @return O ID do jogador (1 ou 2), ou 0 se não for encontrado.
     */
    public int getID() {
        if(local.getJogador1() == this) {
            return 1;
        }

        if(local.getJogador2() == this) {
            return 2;
        }

        return 0;
    }

    /**
     * Abre a mochila do jogador.
     *
     * @return A mochila do jogador.
     */
    public Mochila abrirMochila() {
        return mochila;
    }

    /**
     * Define se o jogador já foi empurrado.
     *
     * @param jaFoiEmpurrado O novo estado do empurrão.
     */
    public void setJaFoiEmpurrado(boolean jaFoiEmpurrado) {
        this.jaFoiEmpurrado = jaFoiEmpurrado;
    }

    /**
     * Retorna a força do jogador, considerando o buff de força.
     *
     * @return A força do jogador.
     */
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
        Transmissor.avisoPontosAlterados(pontosMovimento, getID());
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
     * Retorna a quantidade de ouro do jogador.
     *
     * @return A quantidade de maracujás (representando ouro).
     */
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
     * Verifica se o jogador não tem pontos de movimento.
     *
     * @return True se o jogador não tem pontos de movimento, false caso contrário.
     */

    public boolean semPontosMovimento() {
        return pontosMovimento == 0;
    }

    /**
     * Tenta catar uma fruta.
     *
     * @throws JogadorSemPontosDeMovimentacaoException Se o jogador não tem pontos de movimento.
     * @throws GramaSemFrutaException Se a grama onde o jogador está não tem fruta.
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

            discontarPontos(1);
            mochila.guardar(frutaColetada);
            quadradinho.setEspacoFruta(null);

            Transmissor.avisoMudancaFruta(null, quadradinho.posicaoX, quadradinho.posicaoY);

            if(frutaColetada.isBichada()) {
                Transmissor.avisoBichada(this);
                EventoSonoroHandler.somNerf();
            }
            else if(frutaColetada.getClass() == Maracuja.class) {
                EventoSonoroHandler.somPontuacao();
            }
            else {
                EventoSonoroHandler.somCatar();
            }

            // Tenta aplicar nerf no jogador
            frutaColetada.nerfar(this);
        }
        catch(Exception e) {
            System.out.println(e + "");
        }
    }

    /**
     * Tenta comer uma fruta.
     *
     * @param fruta A classe da fruta a ser comida.
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
    
    /**
     * Tenta pegar uma fruta de uma árvore.
     *
     * @throws JogadorJaSeMovimentouException Se o jogador já se moveu.
     * @throws JogadorForaDeArvoreException Se o jogador não está em uma árvore.
     * @throws ClasseNaoInstanciadaException Se a classe da fruta não pôde ser instanciada.
     * @throws ArvoreEmCooldownException Se a árvore está em cooldown.
     */
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
        Transmissor.avisoPegouFrutaArvore(this.posicaoX, this.posicaoY);
        EventoSonoroHandler.somCatar();
    }

    /**
     * Disconta os pontos de movimento do jogador.
     *
     * @param pontos Os pontos a serem descontados.
     */
    private void discontarPontos(int tirar) {
        pontosMovimento -= tirar;
        Transmissor.avisoPontosAlterados(pontosMovimento, getID());
    }

    /**
     * Empurra outro jogador, caso a força do jogador que está empurrando seja 
     * superior à do jogador alvo. Se o alvo já foi empurrado, a ação não é permitida.
     *
     * @param alvo O jogador que será empurrado.
     * @throws ForcaInsuficienteException Se a força do jogador que está empurrando 
     *         for menor ou igual à força do jogador alvo.
     * @throws jaEmpurraramNoJogadorException Se o jogador alvo já foi empurrado 
     *         anteriormente, impedindo novas tentativas de empurrão.
     */
    public void empurrar(Jogador alvo) throws ForcaInsuficienteException, jaEmpurraramNoJogadorException {
        if(getForca() <= alvo.getForca()) {
            throw new ForcaInsuficienteException("Forca insuficiente");
        }

        if(alvo.jaFoiEmpurrado) {
            throw new jaEmpurraramNoJogadorException("");
        }

        discontarPontos(1);
        EventoSonoroHandler.somEmpurrao();

        int numFrutas = (int) Math.round(Extras.logb(2, getForca() + 1));
        numFrutas -= (int) Math.round(Extras.logb(2, alvo.getForca() + 1));
        numFrutas = Math.max(0, numFrutas);

        alvo.derrubarFrutas(numFrutas);
        alvo.setJaFoiEmpurrado(true);
    }

    
    /**
     * Move o jogador para uma nova posição no tabuleiro, caso essa posição seja 
     * válida e não contenha obstáculos. Atualiza a posição do jogador e notifica 
     * outros componentes do jogo sobre a movimentação.
     *
     * @param posX A nova coordenada X onde o jogador deseja se mover.
     * @param posY A nova coordenada Y onde o jogador deseja se mover.
     * @throws MovimentoParaEspacoComPlayerException Se a nova posição já estiver 
     *         ocupada por outro jogador.
     * @throws MovimentoParaEspacoComPedraException Se a nova posição contiver um 
     *         obstáculo (pedra).
     * @throws JogadorForaDoCampoException Se a nova posição estiver fora dos limites 
     *         do tabuleiro.
     */
    
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

        Transmissor.avisoMovimentacaoJogador(
                new ArrayList<>(Arrays.asList(posicaoX, posicaoY)),
                new ArrayList<>(Arrays.asList(posX, posY))
        );
        
        // Trocando a posição do jogador
        ((ElementoEstaticoPisavel) local.tabuleiro[posX][posY]).setJogador(this);
        ((ElementoEstaticoPisavel) local.tabuleiro[posicaoX][posicaoY]).setJogador(null);
        this.posicaoX = posX;
        this.posicaoY = posY;

        setJaSeMoveu(true);
    }

    /**
     * Move o jogador uma posição para cima no tabuleiro. Se a posição superior 
     * estiver ocupada por um obstáculo (pedra) e o jogador tiver pontos de 
     * movimento suficientes, pode pular para a posição acima. Se a posição 
     * estiver ocupada por outro jogador, tenta empurrá-lo, caso o jogador tenha 
     * força suficiente.
     *
     * @throws JogadorSemPontosDeMovimentacaoException Se o jogador não tiver 
     *         pontos de movimento suficientes para realizar a ação.
     */

    public void moverCima() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX, posicaoY - 1);
            discontarPontos(1);
            EventoSonoroHandler.somAndarCima();
        }
        catch(JogadorForaDoCampoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX, posicaoY - 2);
                discontarPontos(3);
                EventoSonoroHandler.somPular();
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
     * Move o jogador uma posição para baixo no tabuleiro. Se a posição inferior 
     * estiver ocupada por um obstáculo (pedra) e o jogador tiver pontos de 
     * movimento suficientes, pode pular para a posição abaixo. Se a posição 
     * estiver ocupada por outro jogador, tenta empurrá-lo, caso o jogador tenha 
     * força suficiente.
     *
     * @throws JogadorSemPontosDeMovimentacaoException Se o jogador não tiver 
     *         pontos de movimento suficientes para realizar a ação.
     */

    public void moverBaixo() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX, posicaoY + 1);
            discontarPontos(1);
            EventoSonoroHandler.somAndarBaixo();
        }
        catch(JogadorForaDoCampoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX, posicaoY + 2);
                discontarPontos(3);
                EventoSonoroHandler.somPular();
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

    /**
     * Move o jogador uma posição para a esquerda no tabuleiro. Se a posição 
     * esquerda estiver ocupada por um obstáculo (pedra) e o jogador tiver 
     * pontos de movimento suficientes, pode pular para a posição à esquerda. 
     * Se a posição estiver ocupada por outro jogador, tenta empurrá-lo, caso o 
     * jogador tenha força suficiente.
     *
     * @throws JogadorSemPontosDeMovimentacaoException Se o jogador não tiver 
     *         pontos de movimento suficientes para realizar a ação.
     */

    public void moverEsquerda() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX - 1, posicaoY);
            discontarPontos(1);
            EventoSonoroHandler.somAndarEsquerda();
        }
        catch(JogadorForaDoCampoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX - 2, posicaoY);
                discontarPontos(3);
                EventoSonoroHandler.somPular();
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
     * Move o jogador uma posição para a direita no tabuleiro. Se a posição 
     * direita estiver ocupada por um obstáculo (pedra) e o jogador tiver 
     * pontos de movimento suficientes, pode pular para a posição à direita. 
     * Se a posição estiver ocupada por outro jogador, tenta empurrá-lo, caso o 
     * jogador tenha força suficiente.
     *
     * @throws JogadorSemPontosDeMovimentacaoException Se o jogador não tiver 
     *         pontos de movimento suficientes para realizar a ação.
     */

    public void moverDireita() throws JogadorSemPontosDeMovimentacaoException {
        if(semPontosMovimento()) {
            throw new JogadorSemPontosDeMovimentacaoException("");
        }

        try {
            moverLivre(posicaoX + 1, posicaoY);
            discontarPontos(1);
            EventoSonoroHandler.somAndarDireita();
        }
        catch(JogadorForaDoCampoException _) {  }
        catch(MovimentoParaEspacoComPedraException e) {
            if(pontosMovimento < 3) {
                throw new JogadorSemPontosDeMovimentacaoException("");
            }

            try {
                moverLivre(posicaoX + 2, posicaoY);
                discontarPontos(3);
                EventoSonoroHandler.somPular();
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
     * Gera uma quantidade aleatória de pontos de movimento para o jogador. 
     * O número de pontos é calculado como a soma de dois lançamentos de 
     * dados de seis lados (resultando em um valor entre 2 e 12). Se a 
     * opção de trapaça for ativada, a quantidade de pontos é multiplicada 
     * por 20.
     *
     * @param trapaca Indica se o jogador está utilizando uma trapaça para 
     *                aumentar a quantidade de pontos gerados.
     */

    public void gerarPontos(boolean trapaca) {
        Random gerador = new Random();

        pontosMovimento = gerador.nextInt(6) + gerador.nextInt(6) + 2;
        if(trapaca) {
            pontosMovimento *= 20;
        }

        Transmissor.avisoPontosAlterados(pontosMovimento, getID());
    }

    /**
     * Atualiza o cooldown de todas as árvores armazenadas no mapa 
     * `arvoresEmCooldown`. Para cada árvore, reduz o tempo de cooldown 
     * em 1. Se o cooldown de uma árvore chega a 0, a árvore é removida 
     * do mapa.
     */
    public void atualizarCooldowns() {
        for(Arvore arvore : arvoresEmCooldown.keySet()) {
            arvoresEmCooldown.put(arvore, arvoresEmCooldown.get(arvore) - 1);

            if(arvoresEmCooldown.get(arvore) == 0) {
                arvoresEmCooldown.remove(arvore);
            }
        }
    }

    /**
     * Derruba uma quantidade especificada de frutas em espaços vazios ao redor 
     * da posição atual do jogador. O método verifica um quadrado de 5x5 em 
     * torno da posição (posicaoX, posicaoY) e adiciona espaços vazios à lista 
     * `espacosVazios`. Se o espaço já contém uma fruta, ele não é adicionado 
     * à lista.
     * 
     * @param quantidade A quantidade de frutas a serem derrubadas.
     */
    
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
            int indexEspaco = gerador.nextInt(espacosVazios.size());
            Class<? extends Fruta> escolhida = frutasDisponiveis.get(num);

            try {
                espacosVazios.get(indexEspaco).setEspacoFruta(mochila.tirar(escolhida));
                Grama espacoRemovido = espacosVazios.remove(indexEspaco);
                quantidade--;
                Transmissor.avisoMudancaFruta(
                        espacoRemovido.getEspacoFruta().getClass(), espacoRemovido.posicaoX, espacoRemovido.posicaoY
                );
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
