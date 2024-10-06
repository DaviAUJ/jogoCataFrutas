package utilitarios;

import java.util.Random;

public class Extras {
	public static int[] colunaMatriz(int[][] matriz, int coluna) {
		int[] saida = new int[matriz.length];
		
		try {
			for(int i = 0; i < matriz.length; i++) {
				saida[i] = matriz[i][coluna];
			}
		}
		catch(Exception e) {
			System.out.println("NÃO FOI POSSÍVEL PEGAR O ARRAY - ERRO: " + e);
		}
		
		return saida;
	}
	
	public static int somarVetor(int[] vetor) {
		int saida = 0;
		
		for(int i = 0; i < vetor.length; i++) {
			saida += vetor[i];
		}
		
		return saida;
	}
	
	public static <T> T[] embaralharVetor(T[] vetor) {
		Random rand = new Random();
		
		T[] saida = vetor.clone();
		
		for(int i = saida.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			
			T temp = saida[index];
			saida[index] = saida[i];
			saida[i] = temp;
		}
		
		return saida;
	}
}
