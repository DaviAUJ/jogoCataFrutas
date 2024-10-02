import java.util.Random;
import java.util.Scanner;

class Elemento {
  private String nome;

  public Elemento(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return nome;
  }
}

public class Terreno3 {
  private int dimensao;
  private Elemento[][] tabuleiro;
  private Random random;

  public Terreno3(int dimensao) {
    if (dimensao < 3) {
      System.out.println("Erro ao criar o terreno, as dimensoes precisam ser maiores ou iguais a 3...");
    }
    this.setDimensao(dimensao);
    this.tabuleiro = new Elemento[dimensao][dimensao]; // inicializa o tabuleiro
    this.random = new Random(); // inicializa o gerador de numeros aleatorios
  }

  public int getDimensao() {
    return dimensao;
  }

  public void setDimensao(int dimensao) {
    this.dimensao = dimensao;
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

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o valor das dimensões do terreno: ");
    int dimensao = scanner.nextInt();

    Terreno3 teste = new Terreno3(dimensao);
    System.out.println("Digite a quantidade de elementos a serem gerados: ");
    int quantidade = scanner.nextInt();
    teste.gerarElementosAleatorios(quantidade);
    teste.gerarTerreno(); // gera e exibe o terreno
    scanner.close();
  }
}