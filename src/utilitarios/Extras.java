package utilitarios;

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
}
