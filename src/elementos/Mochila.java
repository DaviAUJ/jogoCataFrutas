package elementos;

import frutas.Fruta;
import utilitarios.GerenciadorArquivo;

/**
 * Esta classe representa uma mochila que pode armazenar frutas.
 */

public class Mochila {
    private int capacidade = 1;
    private Fruta[] listaFrutas = new Fruta[capacidade];
    private int quantFrutas = 0;

    /**
     * Construtor padrão da classe Mochila.
     * Inicializa a capacidade da mochila com base nas configurações do arquivo.
     */

    public Mochila() {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        capacidade = arquivo.pegarEspacoMochila();
    }

    /**
     * Retorna a capacidade máxima da mochila.
     *
     * @return A capacidade da mochila.
     */

    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Define a capacidade máxima da mochila.
     *
     * @param capacidade A nova capacidade da mochila.
     */

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * Retorna a quantidade atual de frutas na mochila.
     *
     * @return A quantidade de frutas.
     */

    public int getQuantFrutas() {
        return quantFrutas;
    }

    /**
     * Define a quantidade de frutas na mochila, respeitando a capacidade máxima.
     *
     * @param quantFrutas A nova quantidade de frutas.
     */

    public void setQuantFrutas(int quantFrutas) {
        if (quantFrutas > capacidade) {
            quantFrutas = capacidade;
        } else {
            this.quantFrutas = quantFrutas;
        }
    }

    /**
     * Remove uma fruta da mochila pelo nome.
     *
     * @param nomeFruta O nome da fruta a ser removida.
     * @return True se a fruta foi removida com sucesso, false caso contrário.
     */

    public boolean removerFruta(String nomeFruta) {
        return false;
    }

    /**
     * Adiciona uma fruta à mochila pelo nome.
     *
     * @param nomeFruta O nome da fruta a ser adicionada.
     * @return True se a fruta foi adicionada com sucesso, false caso contrário.
     */

    public boolean adicionarFruta(String nomeFruta) {
        return false;
    }
}
