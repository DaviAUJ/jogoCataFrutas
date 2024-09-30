package utilitarios;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.concurrent.ExecutionException;

public class GerenciadorArquivo {
    public File arquivoAtual;

    private int QTD_TIPOS_DE_FRUTAS;
    private int INFO_POR_FRUTA;

    public GerenciadorArquivo(String caminho) {
        this.QTD_TIPOS_DE_FRUTAS = 7;
        this.INFO_POR_FRUTA = 2;



        this.arquivoAtual = new File(caminho);

        if (!this.arquivoAtual.exists()){
            System.out.printf("Não existe um arquivo de configuração. Criando novo %s\n", this.arquivoAtual.getName());
            try{
                this.arquivoAtual.createNewFile();
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

    public int pegarQtdBichadas(){
        return this.pegarValAtributo(10);
    }

    public int pegarEspacoMochila(){
        return this.pegarValAtributo(11);
    }


}
