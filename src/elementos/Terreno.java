package elementos;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import frutas.*;
import utilitarios.*;

public class Terreno {
	private int dimensao = 3;
	private Elemento[][] tabuleiro = new Elemento[dimensao][dimensao];
	private int[] quantTipoArvores = new int[6];
	private int[] quantFrutasChao = new int[7];
	private int quantPedras = 0;
	
	public Terreno() {
		GerenciadorArquivo arquivo = new GerenciadorArquivo("config.txt");

		dimensao = arquivo.pegarDimensao();
		quantPedras = arquivo.pegarQtdPedras();
		tabuleiro = new Elemento[dimensao][dimensao];

		quantTipoArvores = Arrays.copyOfRange(Extras.colunaMatriz(arquivo.pegarFrutas(), 0), 1, 7);
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

	public <T extends Elemento> boolean gerarElementosAleatorios(Class<T> classe, int quantidade) {
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
				if (tabuleiro[i][j] == null) {
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

			try {
				T novo = classe
						.getDeclaredConstructor(String.class, int.class, int.class)
						.newInstance(new Object[] {classe.getSimpleName().substring(0, 2) + i, x, y});
				
				tabuleiro[x][y] = novo;
			} catch (Exception e) {
				System.out.println("Não foi possível instanciar a classe: " + e);

				return false;
			}
		}
		
		return true;
	}

	public void gerarTerreno() {
		int totalArvores = Extras.somarVetor(quantTipoArvores);
		
		this.gerarElementosAleatorios(Jogador.class, 2);
		this.gerarElementosAleatorios(Arvore.class, totalArvores);
		this.gerarElementosAleatorios(Pedra.class, quantPedras);
		this.gerarElementosAleatorios(Maracuja.class, quantFrutasChao[0]);
		this.gerarElementosAleatorios(Laranja.class, quantFrutasChao[1]);
		this.gerarElementosAleatorios(Abacate.class, quantFrutasChao[2]);
		this.gerarElementosAleatorios(Coco.class, quantFrutasChao[3]);
		this.gerarElementosAleatorios(Generica.class, Extras.somarVetor(Arrays.copyOfRange(quantFrutasChao, 4, 7)));
		
		ArrayList<Arvore> arvores = new ArrayList<Arvore>();
		
		// Pegando todas as árvores
		for (int i = 0; i < dimensao; i++) {
			for (int j = 0; j < dimensao; j++) {
				// tirar esse trycatch depois de implementar a funcionalidade de preencher espaços vazios com grama
				// .getclass da erro quando é null
				try {
					if (tabuleiro[i][j].getClass() == Arvore.class) {
						arvores.add((Arvore) tabuleiro[i][j]);
					}
				}
				catch(Exception e) {
					System.out.println("null");
				}
			}
		}
		
		Collections.shuffle(arvores);
		
		HashMap<Class<? extends Fruta>, Integer> tipoFaltando = new HashMap<Class<? extends Fruta>, Integer>();
		tipoFaltando.put(Laranja.class, quantTipoArvores[0]);
		tipoFaltando.put(Abacate.class, quantTipoArvores[1]);
		tipoFaltando.put(Coco.class, quantTipoArvores[2]);
		tipoFaltando.put(Generica.class, Extras.somarVetor(Arrays.copyOfRange(quantTipoArvores, 3, 6)));
		
		for(Class<? extends Fruta> classe : tipoFaltando.keySet()) {
			for(int i = 0; i < tipoFaltando.get(classe); i++) {
				arvores.getFirst().setTipo(classe);
				arvores.removeFirst();
			}
		}
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
