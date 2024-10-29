package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe que representa um quadradinho no tabuleiro do jogo.
 * Esta classe estende JButton e é responsável por gerenciar a aparência e as interações de um quadrado no tabuleiro.
 */
public class QuadradinhoTabuleiro extends JButton {
    public HashMap<String, ArrayList<Image>> imagens;
    private int TAMANHO;
    public String tipo;
    public int indicador = 0;
    private int indiceAleatorio = 0;
    private Image imagemFundo;
    private Image imagemJogador;

    /**
     * Construtor da classe QuadradinhoTabuleiro.
     *
     * @param tamanho O tamanho do quadradinho.
     * @param tipo O tipo do quadradinho (ex: pedra, jogador1, jogador2).
     * @param indicador Indicador para selecionar a imagem.
     * @param imagens Mapa de imagens para diferentes tipos de quadrados.
     */
    public QuadradinhoTabuleiro(int tamanho, String tipo, int indicador, HashMap<String, ArrayList<Image>> imagens) {
        this.TAMANHO = tamanho;
        this.tipo = tipo;
        this.indicador = indicador;
        this.indiceAleatorio = gerarIndiceAleatorio(tipo);
        this.imagens = imagens;


        EstiloComponentes.aplicarEstiloQuadradinhoTabuleiro(this);

        imagemFundo = null;
        imagemJogador = null;

        configurarFundo();
    }

    /**
     * Obtém o tamanho do quadradinho.
     *
     * @return O tamanho do quadradinho.
     */
    public int getTamanho() {
        return TAMANHO;
    }

    /**
     * Configura a imagem de fundo do quadradinho com base no seu tipo.
     */
    public void configurarFundo(){
        if (this.tipo.equals("pedra")){
            imagemFundo = this.imagens.get("pedras").get(this.indiceAleatorio);
        }
        else if (!this.tipo.equals("jogador1") && !this.tipo.equals("jogador2")){
            System.out.println(this.tipo+"s");
            imagemFundo = this.imagens.get(this.tipo + "s").get(this.indicador);
        }

        else{
            imagemFundo = this.imagens.get("gramas").get(this.indicador);
            if (this.tipo.equals("jogador1")){
                this.imagemJogador = this.imagens.get("jogadores").getFirst();
            }
            else{
                this.imagemJogador = this.imagens.get("jogadores").get(1);
            }
        }
    }

    /**
     * Gera um índice aleatório para a seleção de imagens de pedra.
     *
     * @param tipo O tipo de quadradinho.
     * @return Um índice aleatório para a imagem de pedra.
     */
    private int gerarIndiceAleatorio(String tipo) {
        Random random = new Random();
        int max = 0;
        int min = 0;

        if (tipo.equals("pedra")) {
            max = 6;
        }
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagemFundo != null){
            Image imagemRedimencionada = imagemFundo.getScaledInstance(this.TAMANHO, this.TAMANHO, Image.SCALE_DEFAULT);
            ImageIcon imagemIcon = new ImageIcon(imagemRedimencionada);
            g.drawImage(imagemIcon.getImage(), 0, 0, null);
        }

        if (imagemJogador != null){
            Image imagemJogadorRedimencionada = imagemJogador.getScaledInstance(this.TAMANHO, this.TAMANHO, Image.SCALE_DEFAULT);
            ImageIcon imagemIcon = new ImageIcon(imagemJogadorRedimencionada);
            g.drawImage(imagemIcon.getImage(), 0, 0, null);
        }

    }

    /**
     * Atualiza o quadradinho com base no tipo de ação e no indicador fornecido.
     *
     * @param tipo O tipo de atualização (ex: colocarJogador1, colocarFruta, etc.).
     * @param indicador O indicador para selecionar a imagem, se aplicável.
     */
    public void atualizarQuadradinho(String tipo, int indicador) {
        if(tipo.equals("colocarJogador1")){
            this.imagemJogador = this.imagens.get("jogadores").getFirst();
        }
        else if (tipo.equals("colocarJogador2")){
            this.imagemJogador = this.imagens.get("jogadores").get(1);
        }
        else if (tipo.equals("colocarFruta")){
            this.imagemFundo = this.imagens.get("frutas").get(indicador);
        }else if (tipo.equals("tirarJogador")){
            this.imagemJogador = null;
        }
        else if (tipo.equals("tirarFruta")){
            this.imagemFundo = this.imagens.get("gramas").getFirst();
        }

        repaint();
    }



}
