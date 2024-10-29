package utilitarios;

import java.util.Random;

/**
 * Classe de utilitários contendo métodos para manipulação de matrizes e
 * vetores.
 */

public class Extras {

	/**
	 * Extrai uma coluna de uma matriz e a retorna como um vetor.
	 *
	 * @param matriz A matriz de onde a coluna será extraída.
	 * @param coluna O índice da coluna a ser extraída.
	 * @return Um vetor contendo os elementos da coluna especificada.
	 */

	public static int[] colunaMatriz(int[][] matriz, int coluna) {
		int[] saida = new int[matriz.length];

		try {
			for (int i = 0; i < matriz.length; i++) {
				saida[i] = matriz[i][coluna];
			}
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL PEGAR O ARRAY - ERRO: " + e);
		}

		return saida;
	}

	/**
	 * Soma todos os elementos de um vetor.
	 *
	 * @param vetor O vetor cujos elementos serão somados.
	 * @return O resultado da soma dos elementos do vetor.
	 */

	public static int somarVetor(int[] vetor) {
		int saida = 0;

		for (int i = 0; i < vetor.length; i++) {
			saida += vetor[i];
		}

		return saida;
	}

	/**
	 * Embaralha um vetor e retorna uma nova versão embaralhada.
	 *
	 * @param vetor O vetor a ser embaralhado.
	 * @param <T>   O tipo dos elementos do vetor.
	 * @return Um novo vetor com os elementos embaralhados.
	 */

	public static <T> T[] embaralharVetor(T[] vetor) {
		Random rand = new Random();

		T[] saida = vetor.clone();

		for (int i = saida.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);

			T temp = saida[index];
			saida[index] = saida[i];
			saida[i] = temp;
		}

		return saida;
	}

	/**
     * Calcula o logaritmo de um número em uma base específica.
     *
     * @param base A base do logaritmo.
     * @param x    O número do qual o logaritmo será calculado.
     * @return O logaritmo de x na base especificada.
     */
	public static double logb(double base, double x) {
		return Math.log(x) / Math.log(base);
	}
}
