package cenario;

import jogoCataFrutas.Elemento;

public class Terreno {
	private int dimensao = 3;
    private Elemento[][] tabuleiro = new Elemento[dimensao][dimensao];
    
    public Terreno() { }

    // Pra quando for gerar um terreno novo
    public Terreno(int dimensao) {
        this.setDimensao(dimensao);
    }

    // Pra quando for pegar um terreno já feito
    public Terreno(Elemento[][] tabuleiro) {
        this.setTabuleiro(tabuleiro);
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

      public void gerarElementosAleatorios(int quantidade) {

    // verifica se a quantidade de elementos e maior q as posicoes disponiveis
    if (quantidade > dimensao * dimensao) {
      System.out.println("Nao e possivel gerar " + quantidade + " elementos. Apenas " + (dimensao * dimensao)
          + " posicoes disponiveis...");
      return;
    }

    // array q armazena as posicoes disponiveis
    int[][] posicoesDisponiveis = new int[dimensao * dimensao][2];
    int index = 0;

    // preenche o array de posicoes disponiveis
    for (int i = 0; i < dimensao; i++) {
      for (int j = 0; j < dimensao; j++) {
        posicoesDisponiveis[index++] = new int[] { i, j };
      }
    }

    // gera os elementos nas posicoes disponiveis
    for (int i = 0; i < quantidade; i++) {
      // seleciona uma posicao aleatoria
      int randomIndex = random.nextInt(index);
      int x = posicoesDisponiveis[randomIndex][0];
      int y = posicoesDisponiveis[randomIndex][1];

      // marca a posicao como ocupada e remove do array
      posicoesDisponiveis[randomIndex] = posicoesDisponiveis[index - 1]; // troca com a ultima posicao
      posicoesDisponiveis[index - 1] = null; // marca a ultima posicao como usada
      index--; // reduz o indice para posicoes validas

      // adiciona um novo elemento na posicao
      tabuleiro[x][y] = new Elemento("E" + (i + 1)); // nome do elemento
    }
  }

  public void gerarTerreno() {
    if (dimensao < 3) {
      System.out.println("Erro ao gerar o terreno, as dimensoes precisam ser maiores ou iguais a 3...");
    } else {
      System.out.println("Imprimindo tabuleiro...");
      for (int i = 0; i < dimensao; i++) {
        for (int j = 0; j < dimensao; j++) {
          if (tabuleiro[i][j] != null) {
            System.out.print(" " + tabuleiro[i][j].toString() + " ");
          } else {
            System.out.print(" . ");
          }
        }
        System.out.println();
      }
    }
  }
}
