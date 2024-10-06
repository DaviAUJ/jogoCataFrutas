package utilitarios;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class GerenciadorArquivo {
	public static final String caminhoPadrao = "config.txt";
    public File arquivoAtual;

    private int QTD_TIPOS_DE_FRUTAS;
    private int INFO_POR_FRUTA;

    public GerenciadorArquivo(String caminho) {
        this.QTD_TIPOS_DE_FRUTAS = 7;
        this.INFO_POR_FRUTA = 2;
        
        int dimensao;
        int qtdPedras;
        int chanceBichadas;
        int espacoMochila;
        int[][] configFrutas;

        this.arquivoAtual = new File(caminho);

        if (!this.arquivoAtual.exists()){
            System.out.printf("Não existe um arquivo de configuração. Criando novo %s\n", this.arquivoAtual.getName());
            try{
                this.arquivoAtual.createNewFile();
                
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
                this.salvarNovasConfiguracoes(dimensao, qtdPedras, configFrutas, chanceBichadas, espacoMochila);
            }
            catch (Exception e){
                System.err.println("NÃO FOI POSSÍVEL CRIAR O ARQUIVO: ERRO: " + e);
            }
        }
    }

    private String pegarLinha(int nLinha) {
        FileReader leitorDeArquivo;
        BufferedReader paginacao;

        try{
            leitorDeArquivo = new FileReader(this.arquivoAtual);
            paginacao = new BufferedReader(leitorDeArquivo);

            String linha;
            for (int c = 0; c < nLinha; c++) {
                linha = paginacao.readLine();
                if (c + 1 == nLinha){
                    leitorDeArquivo.close();
                    paginacao.close();
                    return linha;
                }
                if (linha == null) {
                    System.err.println("FORA DO LIMITE DE LINHAS DO ARQUIVO!");
                    break;
                }

            }

        }
        catch (Exception e){
            System.err.println(e);
        }


        return null;
    }

    private int pegarValAtributo(int nLinha){
        try{
            String linha = this.pegarLinha(nLinha);
            int posInicio = linha.indexOf(" ");

            return Integer.parseInt(linha.substring(posInicio+1));
        }
        catch (Exception e){
            System.err.println("O ARQUIVO DE CONFIGURAÇÃO NÃO SEGUE O PADRÃO CORRETO!!!");
        }
        return 0;
    }

    public int pegarDimensao(){
        return this.pegarValAtributo(1);
    }

    public int pegarQtdPedras(){
        return this.pegarValAtributo(2);
    }

    public int[][] pegarFrutas(){
        try{
            int [][] qtdCadaFruta = new int[QTD_TIPOS_DE_FRUTAS][INFO_POR_FRUTA];

            for (int i = 0; i < QTD_TIPOS_DE_FRUTAS; i++){
                String[] linhaDeAtributos = pegarLinha(i+3).split("\\s+");
                for (int j = 0; j < INFO_POR_FRUTA; j++){
                    qtdCadaFruta[i][j] = Integer.parseInt(linhaDeAtributos[j+1]);
                }
            }

            return qtdCadaFruta;
        }
        catch (Exception e){
            System.err.println("O ARQUIVO DE CONFIGURAÇÃO NÃO SEGUE O PADRÃO CORRETO!!");
        }

        return null;
    }

    public int pegarChanceBichadas(){
        return this.pegarValAtributo(10);
    }

    public int pegarEspacoMochila(){
        return this.pegarValAtributo(11);
    }

    public boolean salvarNovasConfiguracoes(int dimensao, int qtdPedras, int[][] configFrutas, int chanceBichadas, int tamMochila){
        try{
            FileWriter arquivo = new FileWriter(this.arquivoAtual.getPath());
            BufferedWriter arquivoEditado = new BufferedWriter(arquivo);

            String textoAtual = String.format("dimensao %d", dimensao);
            arquivoEditado.write(textoAtual);
            arquivoEditado.newLine();

            textoAtual = String.format("pedras %d", qtdPedras);
            arquivoEditado.write(textoAtual);
            arquivoEditado.newLine();

            //Talvez seja interessante implementar essa parte utilizando dicionários no futuro
            String[] frutas = {"maracuja", "laranja", "abacate", "coco", "acerola", "amora", "goiaba"};

            for (int c = 0; c < configFrutas.length; c++){
                textoAtual = String.format("%s %d %d", frutas[c], configFrutas[c][0], configFrutas[c][1]);
                arquivoEditado.write(textoAtual);
                arquivoEditado.newLine();
            }

            textoAtual = String.format("bichadas %d", chanceBichadas);
            arquivoEditado.write(textoAtual);
            arquivoEditado.newLine();

            textoAtual = String.format("mochila %d", tamMochila);
            arquivoEditado.write(textoAtual);

            arquivoEditado.flush();
            arquivoEditado.close();
            arquivo.close();
            return true;

        }
        catch (Exception e){
            System.err.println(e);
            return false;
        }
    }
}
