package jogoCataFrutas;

import cenario.Terreno;
import utilitarios.GerenciadorArquivo;

public class Jogo {
    private Terreno floresta = new Terreno();
    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private int contadorRodada = 0;
    private String estado = "";

    // Aqui era para ser em outra classe de configuração, mas
    // classes estaticas não funcionam do jeito
    // que a gente tava pensando
    public int[] quantArvores = new int[7];
    public int[] quantFrutasChao = new int[7];
    public int quantPedras = 0;
    public float chanceBichada = 0.25f;

    /*
    * quantArvores {
    *   0 - Quantidade de árvores de maracujá
    *   1 - Quantidade de árvores de laranja
    *   2 - Quantidade de árvores de abacate
    *   3 - Quantidade de árvores de coco
    *   4 - Quantidade de árvores de acerola
    *   5 - Quantidade de árvores de amora
    *   6 - Quantidade de árvores de goiaba
    *}
    *
    *
    *
    *
    *
    * */
    
    public Jogo() {

        GerenciadorArquivo arquivo = new GerenciadorArquivo("config.txt");
        int dimensao;
        int qtdPedras;
        int chanceBichadas;
        int espacoMochila;
        int[][] configFrutas;

        if (!arquivo.estaConfigurado()){
            //Código para construir baseado no novo arquivo.
            //Essas informações devem vir do front-end futuramente.
            dimensao = 6;
            qtdPedras = 7;
            int[] maracuja = {3, 1};
            int[] laranja = {2, 1};
            int[] abacate = {2, 3};
            int[] coco = {2, 1};
            int[] acerola = {1, 2};
            int[] amora = {1, 1};
            int[] goiaba = {1, 1};

            configFrutas = new int[7][];
            configFrutas[0] = maracuja;
            configFrutas[1] = laranja;
            configFrutas[2] = abacate;
            configFrutas[3] = coco;
            configFrutas[4] = acerola;
            configFrutas[5] = amora;
            configFrutas[6] = goiaba;

            chanceBichadas = 25;
            espacoMochila = 10;


            //É interessante que essa função só seja chamada caso o botão de salvar for pressionado.
            arquivo.salvarNovasConfiguracoes(dimensao, qtdPedras, configFrutas, chanceBichadas, espacoMochila);





        }
        else{
            dimensao = arquivo.pegarDimensao();
            qtdPedras = arquivo.pegarQtdPedras();
            configFrutas = arquivo.pegarFrutas();
            chanceBichadas = arquivo.pegarChanceBichadas();
            espacoMochila = arquivo.pegarEspacoMochila();
        }

        this.quantPedras = qtdPedras;
        this.chanceBichada = (float) chanceBichadas / 100;

        for (int c = 0; c < this.quantFrutasChao.length; c++){
            quantFrutasChao[c] = configFrutas[c][1];
            quantArvores[c] = configFrutas[c][0];
        }

        quantArvores[0] = 0; //Maracujás não podem ter árvores específicas (ainda).






    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean iniciarJogo() {
        return false;
    }

    public boolean passarVez() {
        return false;
    }
}
