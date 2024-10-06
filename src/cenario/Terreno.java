package cenario;

import java.util.Arrays;
import java.util.Random;
import frutas.*;
import jogoCataFrutas.Elemento;
import jogoCataFrutas.Jogador;
import utilitarios.Extras;
import utilitarios.GerenciadorArquivo;

public class Terreno {
	private int dimensao = 3;
	private Elemento[][] tabuleiro = new Elemento[dimensao][dimensao];
	private int[] quantArvores = new int[6];
    private int[] quantFrutasChao = new int[7];
    private int quantPedras = 0;

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
  
	public Terreno() {
      GerenciadorArquivo arquivo = new GerenciadorArquivo("config.txt");
      
      dimensao = arquivo.pegarDimensao();
      quantPedras = arquivo.pegarQtdPedras();
      tabuleiro = new Elemento[dimensao][dimensao];
      
      quantArvores = Arrays.copyOfRange(Extras.colunaMatriz(arquivo.pegarFrutas(), 0), 1, 7);
      quantFrutasChao = Extras.colunaMatriz(arquivo.pegarFrutas(), 1);
	}

  public int getDimensao() {
      return dimensao;
  }

  public void setDimensao(int dimensao) {
      this.dimensao = dimensao;
      tabuleiro = new Elemento[dimensao][dimensao];
  }

  public Elemento[][] getTabuleiro() {
      return tabuleiro;
  }

  public void setTabuleiro(Elemento[][] tabuleiro) {
      this.tabuleiro = tabuleiro;
      dimensao = tabuleiro.length;
  }

  public boolean mudarPosicao(Elemento elemento, int X, int Y) {
      return false;
  }
  
  public boolean gerarElementosAleatorios(String classe, int quantidade) {
    Random gerador = new Random();

    // verifica se a quantidade de elementos e maior q as posicoes disponiveis
    if (quantidade > dimensao * dimensao) {
      System.out.println("Nao e possivel gerar " + quantidade + " elementos. Apenas " + (dimensao * dimensao)
          + " posicoes disponiveis...");
      return false;
    }

    // array q armazena as posicoes disponiveis
    int[][] posicoesDisponiveis = new int[dimensao * dimensao][2];
    int index = 0;

    // preenche o array de posicoes disponiveis
    for (int i = 0; i < dimensao; i++) {
      for (int j = 0; j < dimensao; j++) {
    	  if(tabuleiro[i][j] == null ) {
    		  posicoesDisponiveis[index++] = new int[] { i, j };
    	  }
      }
    }

    // gera os elementos nas posicoes disponiveis
    for (int i = 0; i < quantidade; i++) {
      // seleciona uma posicao aleatoria
      int randomIndex = gerador.nextInt(index);
      int x = posicoesDisponiveis[randomIndex][0];
      int y = posicoesDisponiveis[randomIndex][1];

      // marca a posicao como ocupada e remove do array
      posicoesDisponiveis[randomIndex] = posicoesDisponiveis[index - 1]; // troca com a ultima posicao
      posicoesDisponiveis[index - 1] = null; // marca a ultima posicao como usada
      index--; // reduz o indice para posicoes validas

      // adiciona um novo elemento na posicao
      if(classe.equals("Jogador")) {
        tabuleiro[x][y] = new Jogador("Jo" + (i + 1), x, y);
      }
      else if(classe.equals("Arvore")) {    		
        tabuleiro[x][y] = new Arvore("Ar" + (i + 1), x, y, null);
      }
      else if(classe.equals("Pedra")) {
        tabuleiro[x][y] = new Pedra("Pe" + (i + 1), x, y);
      }
      else if(classe.equals("Maracuja")) {
    	  tabuleiro[x][y] = new Maracuja("Ma" + (i + 1), x, y);
      }
      else if(classe.equals("Laranja")) {
    	  tabuleiro[x][y] = new Laranja("La" + (i + 1), x, y);
      }
      else if(classe.equals("Abacate")) {
        tabuleiro[x][y] = new Abacate("Ab" + (i + 1), x, y);
      }
      else if(classe.equals("Coco")) {
        tabuleiro[x][y] = new Coco("Co" + (i + 1), x, y);
      }
      else if(classe.equals("Generica")) {
        tabuleiro[x][y] = new Generica("Ge" + (i + 1), x, y);
      }
      else {
        return false;
      }
    }
    return true;
  }

  public void gerarTerreno() {
	  GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
	  
	  this.gerarElementosAleatorios("Jogador", 2);
	  this.imprimirTerreno();
	  this.gerarElementosAleatorios("Arvore", Extras.somarVetor(quantArvores));
	  this.imprimirTerreno();
	  this.gerarElementosAleatorios("Pedra", quantPedras);
	  this.imprimirTerreno();
	  this.gerarElementosAleatorios("Maracuja", quantFrutasChao[0]);
	  this.imprimirTerreno();
	  this.gerarElementosAleatorios("Laranja", quantFrutasChao[1]);
	  this.imprimirTerreno();
	  this.gerarElementosAleatorios("Abacate", quantFrutasChao[2]);
	  this.imprimirTerreno();
	  this.gerarElementosAleatorios("Coco", quantFrutasChao[3]);
	  this.imprimirTerreno();
	  this.gerarElementosAleatorios("Generica", Extras.somarVetor(Arrays.copyOfRange(quantFrutasChao, 4, 7)));
  }
  
  public void imprimirTerreno() {
    if (dimensao < 3) {
      System.out.println("Erro ao gerar o terreno, as dimensoes precisam ser maiores ou iguais a 3...");
    } else {
      System.out.println("Imprimindo tabuleiro...");
      for (int i = 0; i < dimensao; i++) {
        for (int j = 0; j < dimensao; j++) {
          if (tabuleiro[i][j] != null) {
            System.out.print(" " + tabuleiro[i][j].getNome() + " ");
          } else {
            System.out.print("  .  ");
          }
        }
        System.out.println();
      }
    }
  }
}
