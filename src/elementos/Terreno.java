package elementos;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import excecoes.elementosDemaisException;
import frutas.*;
import utilitarios.*;

/**
 * Esta classe representa um terreno/tabuleiro que contém elementos como
 * árvores, pedras, frutas e gramas
 */

public class Terreno {
	private int dimensao = 3;
	private Elemento[][] tabuleiro = new Elemento[dimensao][dimensao];
	private int[] quantTipoArvores = new int[6];
	private int[] quantFrutasChao = new int[7];
	private int quantPedras = 0;

	/**
	 * Construtor da classe Terreno. Inicializa o terreno com as dimensões e
	 * quantidades de elementos
	 * a partir de um arquivo de configuração recebido.
	 */

	public Terreno() {
		GerenciadorArquivo arquivo = new GerenciadorArquivo("config.txt");

		dimensao = arquivo.pegarDimensao();
		
		if(dimensao < 3) {
			dimensao = 3;
		}
		
		quantPedras = arquivo.pegarQtdPedras();
		tabuleiro = new Elemento[dimensao][dimensao];

		quantTipoArvores = Arrays.copyOfRange(Extras.colunaMatriz(arquivo.pegarFrutas(), 0), 1, 7);
		quantFrutasChao = Extras.colunaMatriz(arquivo.pegarFrutas(), 1);
	}
	
	

	/**
	 * Retorna a dimensão do terreno.
	 *
	 * @return A dimensão do terreno.
	 */

	public int getDimensao() {
		return dimensao;
	}

	/**
	 * Define a dimensão do terreno do jogo.
	 *
	 * @param dimensao A nova dimensão do tabuleiro.
	 */

	public void setDimensao(int dimensao) {
		this.dimensao = dimensao;
		tabuleiro = new Elemento[dimensao][dimensao];
	}

	/**
	 * Retorna o tabuleiro do jogo.
	 *
	 * @return O tabuleiro de elementos.
	 */

	public Elemento[][] getTabuleiro() {
		return tabuleiro;
	}

	/**
	 * Define um novo tabuleiro para o jogo.
	 *
	 * @param tabuleiro O novo tabuleiro de elementos.
	 */

	public void setTabuleiro(Elemento[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
		dimensao = tabuleiro.length;
	}

	/**
	 * Muda a posição de um elemento no tabuleiro. O método ainda não está
	 * implementado.
	 *
	 * @param elemento O elemento a ser movido.
	 * @param X        A nova coordenada da posição x.
	 * @param Y        A nova coordenada da posição y.
	 * @return false, já que a funcionalidade ainda não está implementada.
	 */

	public boolean mudarPosicao(Elemento elemento, int X, int Y) {
		return false;
	}

	/**
	 * Gera elementos aleatórios no tabuleiro, respeitando a quantidade máxima de
	 * posições disponíveis.
	 *
	 * @param classe     A classe dos elementos a serem gerados.
	 * @param quantidade A quantidade de elementos a serem gerados.
	 * @return true se os elementos foram gerados com sucesso, false caso contrário.
	 */

	public <T extends Elemento> boolean gerarElementosAleatorios(Class<T> classe, int quantidade) throws elementosDemaisException {
		Random gerador = new Random();

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
		
		// Verificando se a quantidade de elementos passada como argumento vai estourar a matriz do tabuleiro
		if(index + 1 < quantidade) {
			throw new elementosDemaisException("ERRO - tentando colocar elementos de mais no tabuleiro");
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
						.newInstance(new Object[] { classe.getSimpleName().substring(0, 2) + i, x, y });

				tabuleiro[x][y] = novo;
			} catch (Exception e) {
				System.out.println("Não foi possível instanciar a classe: " + e);

				return false;
			}
		}

		return true;
	}

	/**
	 * Gera o terreno, colocando árvores, pedras, frutas e gramas nas posições
	 * disponíveis.
	 */

	public boolean gerarTerreno() {
		int totalArvores = Extras.somarVetor(quantTipoArvores);
		
		try {
			this.gerarElementosAleatorios(Jogador.class, 2);
			this.gerarElementosAleatorios(Arvore.class, totalArvores);
			this.gerarElementosAleatorios(Pedra.class, quantPedras);
			this.gerarElementosAleatorios(Maracuja.class, quantFrutasChao[0]);
			this.gerarElementosAleatorios(Laranja.class, quantFrutasChao[1]);
			this.gerarElementosAleatorios(Abacate.class, quantFrutasChao[2]);
			this.gerarElementosAleatorios(Coco.class, quantFrutasChao[3]);
			this.gerarElementosAleatorios(Generica.class, Extras.somarVetor(Arrays.copyOfRange(quantFrutasChao, 4, 7)));
		}
		catch(Exception e) {
			System.err.println("não foi possivel criar o terreno - Erro:" + e);
			return false;
		}
		
		
		ArrayList<Arvore> arvores = new ArrayList<Arvore>();
		int contadorGrama = 0;

		// Pegando todas as árvores
		for (int i = 0; i < dimensao; i++) {
			for (int j = 0; j < dimensao; j++) {
				if (tabuleiro[i][j] == null) {
					tabuleiro[i][j] = new Grama("Gr" + contadorGrama, i, j);
					contadorGrama++;
				}
				else if(tabuleiro[i][j] instanceof Arvore) {
					arvores.add((Arvore) tabuleiro[i][j]);
				}
			}
		}

		Collections.shuffle(arvores);

		HashMap<Class<? extends Fruta>, Integer> tipoFaltando = new HashMap<Class<? extends Fruta>, Integer>();
		tipoFaltando.put(Laranja.class, quantTipoArvores[0]);
		tipoFaltando.put(Abacate.class, quantTipoArvores[1]);
		tipoFaltando.put(Coco.class, quantTipoArvores[2]);
		tipoFaltando.put(Generica.class, Extras.somarVetor(Arrays.copyOfRange(quantTipoArvores, 3, 6)));
		
		if(!arvores.isEmpty()) {
			for(Class<? extends Fruta> classe : tipoFaltando.keySet()) {
				for(int i = 0; i < tipoFaltando.get(classe); i++) {
					arvores.getFirst().setTipo(classe);
					arvores.removeFirst();
				}
			}
		}

		return true;
	}

	/**
	 * Imprime o estado atual do terreno, mostrando os elementos no tabuleiro.
	 */

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
