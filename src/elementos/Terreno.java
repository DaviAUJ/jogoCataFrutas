package elementos;

import java.util.*;

import excecoes.*;
import frutas.*;
import jogoCataFrutas.Configuracoes;
import utilitarios.*;

/**
 * Esta classe representa um terreno/tabuleiro que contém elementos como
 * árvores, pedras, frutas e gramas
 */

public class Terreno {
	private final Jogador jogador1 = new Jogador(this);
	private final Jogador jogador2 = new Jogador(this);

	private int dimensao;
	private int[] quantTipoArvores = new int[6];
	private int[] quantFrutasChao = new int[7];
	private int quantPedras;
	private int totalMaracujas;
	private int maracujasSpawnados = 0;

	protected ElementoEstatico[][] tabuleiro = new ElementoEstatico[dimensao][dimensao];

	/**
	 * Construtor da classe Terreno. Inicializa o terreno com as dimensões e
	 * quantidades de elementos
	 * a partir de um arquivo de configuração recebido.
	 */

	public Terreno() {
		dimensao = Configuracoes.dimensao;
		quantPedras = Configuracoes.qtdPedras;
		totalMaracujas = Configuracoes.qtdMaracujasTotal;

		quantTipoArvores[0] = Configuracoes.qtdLaranjaArvore;
		quantTipoArvores[1] = Configuracoes.qtdAbacatesArvore;
		quantTipoArvores[2] = Configuracoes.qtdCocosArvore;
		quantTipoArvores[3] = Configuracoes.qtdAcerolasArvore;
		quantTipoArvores[4] = Configuracoes.qtdAmorasArvore;
		quantTipoArvores[5] = Configuracoes.qtdGoiabasArvore;

		quantFrutasChao[0] = Configuracoes.qtdMaracujasNoChao;
		quantFrutasChao[1] = Configuracoes.qtdLaranjaChao;
		quantFrutasChao[2] = Configuracoes.qtdAbacatesChao;
		quantFrutasChao[3] = Configuracoes.qtdCocosChao;
		quantFrutasChao[4] = Configuracoes.qtdAcerolasChao;
		quantFrutasChao[5] = Configuracoes.qtdAmorasChao;
		quantFrutasChao[6] = Configuracoes.qtdGoiabasChao;

		tabuleiro = new ElementoEstatico[dimensao][dimensao];
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
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
	 * Retorna o tabuleiro do jogo.
	 *
	 * @return O tabuleiro de elementos.
	 */

	public Elemento[][] getTabuleiro() {
		return tabuleiro;
	}
	
	public int getTotalMaracujas() {
		return totalMaracujas;
	}

	private void posicionarJogadores() {
		Random gerador = new Random();

		jogador1.setNome("Jo1");
		jogador2.setNome("Jo2");

		// Enquanto a posição deles forem iguais
		while(jogador1.posicaoX == jogador2.posicaoX && jogador1.posicaoY == jogador2.posicaoY) {
			// Não é a melhor solução do mundo mas serve
			jogador1.posicaoX = gerador.nextInt(dimensao);
			jogador1.posicaoY = gerador.nextInt(dimensao);
			jogador2.posicaoX = gerador.nextInt(dimensao);
			jogador2.posicaoY = gerador.nextInt(dimensao);
		}

		tabuleiro[jogador1.posicaoX][jogador1.posicaoY] =
				new Grama("Gr0", jogador1.posicaoX, jogador1.posicaoY, jogador1);
		tabuleiro[jogador2.posicaoX][jogador2.posicaoY] =
				new Grama("Gr1", jogador2.posicaoX, jogador2.posicaoY, jogador2);
	}

	/**
	 * Gera elementos aleatórios no tabuleiro, respeitando a quantidade máxima de
	 * posições disponíveis.
	 *
	 * @param classe     A classe dos elementos a serem gerados.
	 * @param quantidade A quantidade de elementos a serem gerados.
	 */

	private <T extends ElementoEstatico> void gerarElementosEstaticos(Class<T> classe, int quantidade)
			throws ElementosDemaisException {
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
			throw new ElementosDemaisException("ERRO - tentando colocar elementos estáticos de mais no tabuleiro");
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
			}
		}
	}

	private void distribuirFrutasNaGrama() throws FrutasDemaisException {
		ArrayList<Grama> gramas = new ArrayList<Grama>();

		for(int x = 0; x < dimensao; x++) {
			for(int y = 0; y < dimensao; y++) {
				if(tabuleiro[x][y] instanceof Grama) {
					gramas.add((Grama) tabuleiro[x][y]);
				}
			}
		}

		if(gramas.size() < Extras.somarVetor(quantFrutasChao)) {
			throw new FrutasDemaisException("Muitas frutas estão tentando ser colocadas na grama");
		}

		Collections.shuffle(gramas);

		HashMap<Class<? extends Fruta>, Integer> tipoFaltando = new HashMap<Class<? extends Fruta>, Integer>();
		tipoFaltando.put(Maracuja.class, quantFrutasChao[0]);
		tipoFaltando.put(Laranja.class, quantFrutasChao[1]);
		tipoFaltando.put(Abacate.class, quantFrutasChao[2]);
		tipoFaltando.put(Coco.class, quantFrutasChao[3]);
		tipoFaltando.put(Generica.class, Extras.somarVetor(Arrays.copyOfRange(quantFrutasChao, 3, 6)));

		if(!gramas.isEmpty()) {
			for(Class<? extends Fruta> classe : tipoFaltando.keySet()) {
				for(int i = 0; i < tipoFaltando.get(classe); i++) {
					// Isso aqui tudo é pra colocar a fruta na grama
					// Deus salve nossas criança do java
					try {
						gramas
						.getFirst()
						.setEspacoFruta(
							classe
							.getDeclaredConstructor(String.class)
							.newInstance(classe.getSimpleName().substring(0, 2) + i)
						);
					}
					catch (Exception e) {
						System.out.println("Não foi possível instanciar a classe: " + e);
					}

					gramas.removeFirst();
				}
			}
		}
	}

	private void distribuirFrutasNasArvores() throws FrutasDemaisException {
		ArrayList<Arvore> arvores = new ArrayList<Arvore>();

		for(int x = 0; x < dimensao; x++) {
			for(int y = 0; y < dimensao; y++) {
				if(tabuleiro[x][y] instanceof Arvore) {
					arvores.add((Arvore) tabuleiro[x][y]);
				}
			}
		}

		if(arvores.size() < Extras.somarVetor(quantTipoArvores)) {
			throw new FrutasDemaisException("Muitas frutas estão tentando ser colocadas na grama");
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
	}

	/**
	 * Gera o terreno, colocando árvores, pedras, frutas e gramas nas posições
	 * disponíveis.
	 * @return true se o terreno foi gerado com sucesso ou false caso contrário.
	 *
	 */

	public boolean gerarTerreno() {
		int totalArvores = Extras.somarVetor(quantTipoArvores);

		this.posicionarJogadores();

		try {
			this.gerarElementosEstaticos(Arvore.class, totalArvores);
			this.gerarElementosEstaticos(Pedra.class, quantPedras);

			int contadorGrama = 2;

			// preenchendo com grama
			// Se for jogador colocar ele dentro da grama
			for (int i = 0; i < dimensao; i++) {
				for (int j = 0; j < dimensao; j++) {
					if (tabuleiro[i][j] == null) {
						tabuleiro[i][j] = new Grama("Gr" + contadorGrama, i, j);
						contadorGrama++;
					}
				}
			}

			this.distribuirFrutasNaGrama();
			this.distribuirFrutasNasArvores();
		}
		catch(Exception e) {
			System.err.println("não foi possivel criar o terreno - Erro:" + e);
			return false;
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

	public void spawnarMaracuja() throws LimiteMaracujasAtingido {
		if(maracujasSpawnados == totalMaracujas - quantFrutasChao[0]) {
			throw new LimiteMaracujasAtingido("");
		}

		LinkedList<Arvore> possiveisArvores = new LinkedList<>();
		Random gerador = new Random();

		for(int x = 0; x < dimensao; x++) {
			for(int y = 0; y < dimensao; y++) {
				if(tabuleiro[x][y] instanceof Arvore) {
					possiveisArvores.add((Arvore) tabuleiro[x][y]);
				}
			}
		}

		Arvore tentativa;
		Grama grama;
		while(true) {
			tentativa = possiveisArvores.get(gerador.nextInt(possiveisArvores.size()));

			for(int x = -1; x <= 1; x++) {
				for(int y = -1; y <= 1; y++) {
					try {
						if(tabuleiro[x + tentativa.getPosicaoX()][y + tentativa.getPosicaoY()] instanceof Grama) {
							grama = (Grama) tabuleiro[x + tentativa.getPosicaoX()][y + tentativa.getPosicaoY()];

							if(!grama.temFruta()) {
								grama.setEspacoFruta(new Maracuja("MaSp"));
								maracujasSpawnados++;
								Transmissor.avisoMudancaFruta(Maracuja.class, grama.posicaoX, grama.posicaoY);

								return;
							}
						}
					}
					catch(Exception _) {}
				}
			}

			possiveisArvores.remove(tentativa);
		}
	}
}
