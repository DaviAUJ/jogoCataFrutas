package cenario;

import java.util.Random;
import frutas.*;
import jogoCataFrutas.Elemento;
import jogoCataFrutas.Jogador;
import utilitarios.GerenciadorArquivo;

public class Terreno {
	private int dimensao = 3;
	private Elemento[][] tabuleiro = new Elemento[dimensao][dimensao];
	
	// Aqui era para ser em outra classe de configuração, mas
    // classes estaticas não funcionam do jeito
    // que a gente tava pensando
    public int[] quantArvores = new int[7];
    public int[] quantFrutasChao = new int[7];
    public int quantPedras = 0;

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

      for (int c = 0; c < quantFrutasChao.length; c++){
          quantFrutasChao[c] = arquivo.pegarFrutas()[c][1];
          quantArvores[c] = arquivo.pegarFrutas()[c][0];
      }

      quantArvores[0] = 0; //Maracujás não podem ter árvores específicas (ainda).
      tabuleiro = new Elemento[dimensao][dimensao];
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
  
  private int[][] PegarPosDiposniveis() {
	// array q armazena as posicoes disponiveis
	    int[][] posicoesDisponiveis = new int[dimensao * dimensao][2];
	    int index = 0;

	    // preenche o array de posicoes disponiveis
	    for (int i = 0; i < dimensao; i++) {
	      for (int j = 0; j < dimensao; j++) {
	        posicoesDisponiveis[index++] = new int[] { i, j };
	      }
	    }
	    
	    return posicoesDisponiveis;
  }

  public boolean gerarElementosAleatorios(int quantidade, String classe, String FrutaArvore) {
    Random gerador = new Random();

    // verifica se a quantidade de elementos e maior q as posicoes disponiveis
    if (quantidade > dimensao * dimensao) {
      System.out.println("Nao e possivel gerar " + quantidade + " elementos. Apenas " + (dimensao * dimensao)
          + " posicoes disponiveis...");
      return false;
    }

    int[][] posicoesDisponiveis = this.PegarPosDiposniveis();
    int index = posicoesDisponiveis.length - 1;

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
      else if(classe.equals("Abacate")) {
        tabuleiro[x][y] = new Abacate("Ab" + (i + 1), x, y);
      }
      else if(classe.equals("Coco")) {
        tabuleiro[x][y] = new Coco("Co" + (i + 1), x, y);
      }
      else if(classe.equals("Generica")) {
        tabuleiro[x][y] = new Generica("Ge" + (i + 1), x, y);
      }
      else if(classe.equals("Laranja")) {
        tabuleiro[x][y] = new Laranja("La" + (i + 1), x, y);
      }
      else if(classe.equals("Maracauja")) {
        tabuleiro[x][y] = new Maracuja("Ma" + (i + 1), x, y);
      }
      else {
        return false;
      }
    }
    return true;
  }

  public void gerarTerreno() {
	  GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);
	  
	  this.gerarElementosAleatorios(quant, null)
	  
	  for
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
