package visao.componentes;

import elementos.*;
import frutas.*;
import jogoCataFrutas.Configuracoes;
import jogoCataFrutas.Jogo;
import utilitarios.Transmissor;
import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TabuleiroJogo extends JPanel {
    private int TAMANHO;
    private int DIMENSAO;
    public ArrayList< ArrayList <QuadradinhoTabuleiro> > malha;
    public HashMap<String, ArrayList<Image>> imagens;
    int[] posJogador1, posJogador2;

    public TabuleiroJogo(int tamanho) {
        this.TAMANHO = tamanho;
        this.DIMENSAO = Configuracoes.dimensao;
        posJogador1 = new int[2];
        posJogador2 = new int[2];
        carregarImagens();

        Jogo jogoAtual = Transmissor.getJogoDoMomento();
        Terreno floresta = jogoAtual.getFloresta();

        Jogador jogador1 = floresta.getJogador1();
        jogador1.setNome(Configuracoes.nomeJogador1);

        Jogador jogador2 = floresta.getJogador2();
        jogador2.setNome(Configuracoes.nomeJogador2);


        this.malha = new ArrayList<>(this.DIMENSAO);
        int tamanhoQuadradinho = this.TAMANHO/this.DIMENSAO;
        for (int i = 0; i < this.DIMENSAO; i++) {
            ArrayList<QuadradinhoTabuleiro> coluna = new ArrayList<>(this.DIMENSAO);
            for (int j = 0; j < this.DIMENSAO; j++) {
                QuadradinhoTabuleiro quad;


                if (floresta.getTabuleiro()[i][j] instanceof Grama grama) {
                    if (grama.temJogador()){
                        quad = new QuadradinhoTabuleiro(tamanhoQuadradinho,
                                grama.getJogador() == jogador1 ? "jogador1" : "jogador2"
                                , 0,this.imagens);
                    }
                    else if (!grama.temFruta()){
                        quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "grama", 0, this.imagens);
                    }
                    else{
                        if (grama.getEspacoFruta() instanceof Abacate){
                            quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "fruta", 0, this.imagens);
                        }
                        else if (grama.getEspacoFruta() instanceof Coco){
                            quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "fruta", 3, this.imagens);
                        }

                        else if (grama.getEspacoFruta() instanceof Laranja){
                            quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "fruta", 5, this.imagens);
                        }

                        else if (grama.getEspacoFruta() instanceof Maracuja){
                            quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "fruta", 6, this.imagens);
                        }
                        else{
                            //Mudar depois isso aqui pra incluir todas as outras frutas! ----------
                            Generica fGenerica = (Generica) grama.getEspacoFruta();
                            quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "fruta", 2, this.imagens);
                        }
                    }
                }
                else if (floresta.getTabuleiro()[i][j] instanceof Arvore arvore) {
                    if (arvore.getTipo() == Abacate.class){
                        quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "arvore", 0, this.imagens);
                    }
                    else if (arvore.getTipo() == Coco.class){
                        quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "arvore", 3, this.imagens);
                    }
                    else if (arvore.getTipo() == Laranja.class){
                        quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "arvore", 5, this.imagens);
                    }
                    else{
                        quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "arvore", 2, this.imagens);
                    }
                }
                else{
                    quad = new QuadradinhoTabuleiro(tamanhoQuadradinho, "pedra", 0, this.imagens);
                }

                quad.imagens = this.imagens;
                coluna.add(quad);
            }
            this.malha.add(coluna);
        }

        EstiloComponentes.aplicarEstiloTabuleiro(this);
        configurarListeners();
    }

    public int getTamanho() {
        return TAMANHO;
    }

    public int getDimensao() {
        return DIMENSAO;
    }

    private void carregarImagens(){
        this.imagens = new HashMap<>();
        int QTD_ARVORES = 6;
        int QTD_FRUTAS = 7;
        int QTD_PEDRAS = 7;
        int QTD_JOGADORES = 2;
        int QTD_GRAMA = 1;

        ArrayList<Image> itensCarregados = new ArrayList<>(QTD_ARVORES);

        for (int i = 0; i < QTD_ARVORES; i++) {
            itensCarregados.add(new ImageIcon("./assets/imgs/jogo/arvores/arvore"+i+".png").getImage());
        }

        this.imagens.put("arvores", itensCarregados);

        itensCarregados = new ArrayList<>(QTD_FRUTAS);
        for (int i = 0; i < QTD_FRUTAS; i++) {
            itensCarregados.add(new ImageIcon("./assets/imgs/jogo/frutas/fruta"+i+".png").getImage());
        }

        this.imagens.put("frutas", itensCarregados);

        itensCarregados = new ArrayList<>(QTD_PEDRAS);
        for (int i = 0; i < QTD_PEDRAS; i++) {
            itensCarregados.add(new ImageIcon("./assets/imgs/jogo/pedras/pedra"+i+".png").getImage());
        }

        this.imagens.put("pedras", itensCarregados);

        itensCarregados = new ArrayList<>(QTD_GRAMA);
        itensCarregados.add(new ImageIcon("./assets/imgs/jogo/grama.png").getImage());
        this.imagens.put("gramas", itensCarregados);

        itensCarregados = new ArrayList<>(QTD_JOGADORES);
        for (int i = 1; i <= QTD_JOGADORES; i++) {
            itensCarregados.add(new ImageIcon("./assets/imgs/jogo/jogador/jogador"+i+".png").getImage());
        }
        this.imagens.put("jogadores", itensCarregados);
    }

    public void configurarListeners() {
        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("avisoMovimentacaoJogador")) {
                    System.out.println("teste");
                    malha
                            .get(((ArrayList<Integer>) evt.getOldValue()).getFirst())
                            .get(((ArrayList<Integer>) evt.getOldValue()).getLast())
                            .atualizarQuadradinho("tirarJogador", 0);

                    malha
                            .get(((ArrayList<Integer>) evt.getNewValue()).getFirst())
                            .get(((ArrayList<Integer>) evt.getNewValue()).getLast())
                            .atualizarQuadradinho("colocarJogador1", 0);
                }
            }
        });
    }
}
