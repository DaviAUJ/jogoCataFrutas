package utilitarios;

public class Extras {
	public static int[] colunaMatriz(int[][] matriz, int coluna) {
		int[] saida = new int[matriz.length - 1];
		
		for(int i = 0; i < matriz.length; i++) {
			saida[i] = matriz[i][coluna];
		}
		
		return saida;
	}
}
