package elementos;

import excecoes.*;

import frutas.*;
import utilitarios.GerenciadorArquivo;
import utilitarios.Transmissor;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.lang.NullPointerException;

/**
 * Representa uma mochila que armazena frutas do jogador.
 */

public class Mochila {
    private final HashMap<Class<? extends Fruta>, Stack<Fruta>> bolso;

    private final int capacidade;
    private int quantFrutas = 0;

    private Jogador dono;

    /**
     * Constrói uma mochila para o jogador especificado.
     * 
     * @param dono O jogador que possui a mochila.
     */
    public Mochila(Jogador dono) {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        capacidade = arquivo.pegarEspacoMochila();

        bolso = new HashMap<>(5);
        bolso.put(Maracuja.class, new Stack<>());
        bolso.put(Abacate.class, new Stack<>());
        bolso.put(Coco.class, new Stack<>());
        bolso.put(Generica.class, new Stack<>());
        bolso.put(Laranja.class, new Stack<>());

        this.dono = dono;
    }

    /**
     * Verifica se a mochila está cheia.
     * 
     * @return True se a mochila estiver cheia, caso contrário, false.
     */
    
    public Boolean taCheia() {
        return capacidade == quantFrutas;
    }

    protected HashMap<Class<? extends Fruta>, Stack<Fruta>> getBolso() {
        return bolso;
    }

    /**
     * Retorna a quantidade total de frutas na mochila.
     * 
     * @return A quantidade de frutas.
     */
    
    public int getQuantFrutas() {
        return quantFrutas;
    }

    /**
     * Retorna a quantidade de maracujás na mochila.
     * 
     * @return A quantidade de maracujás.
     */
    public int getQuantMaracujas() {
        return bolso.get(Maracuja.class).size();
    }

    /**
     * Retorna a quantidade de laranjas na mochila.
     * 
     * @return A quantidade de laranjas.
     */
    public int getQuantLaranjas() {
        return bolso.get(Laranja.class).size();
    }

    /**
     * Retorna a quantidade de cocos na mochila.
     * 
     * @return A quantidade de cocos.
     */
    public int getQuantCocos() {
        return bolso.get(Coco.class).size();
    }

    /**
     * Retorna a quantidade de frutas genéricas na mochila.
     * 
     * @return A quantidade de frutas genéricas.
     */
    public int getQuantGenericas() {
        return bolso.get(Generica.class).size();
    }

    /**
     * Retorna a quantidade de abacates na mochila.
     * 
     * @return A quantidade de abacates.
     */
    public int getQuantAbacates() {
        return bolso.get(Abacate.class).size();
    }

    /**
     * Armazena uma fruta na mochila.
     * 
     * @param fruta A fruta a ser armazenada.
     * @throws MochilaCheiaException Se a mochila já estiver cheia.
     */
    public void guardar(Fruta fruta) throws MochilaCheiaException {
        if(taCheia()) {
            throw new MochilaCheiaException("Mochila em sua capacidade máxima");
        }

        quantFrutas++;
        bolso.get(fruta.getClass()).push(fruta);
        Transmissor.avisoMudouMochila(
                fruta.getClass(),
                bolso.get(fruta.getClass()).size(),
                dono.getID()
        );
    }

    /**
     * Remove e retorna uma fruta do tipo especificado da mochila.
     * 
     * @param classe A classe da fruta a ser removida.
     * @return A fruta removida.
     * @throws MochilaVaziaException Se a mochila estiver vazia.
     * @throws BolsoFrutaVazioException Se não houver frutas do tipo especificado.
     * @throws NullPointerException Se a classe for nula.
     */
    public Fruta tirar(Class<? extends Fruta> classe)
            throws MochilaVaziaException, BolsoFrutaVazioException, NullPointerException {
        if(quantFrutas == 0) {
            throw new MochilaVaziaException("Mochila vazia");
            
        }

        if(bolso.get(classe).isEmpty()) {
            throw new BolsoFrutaVazioException("Bolso fruta vazio");
        }

        if(classe == null) {
            throw new NullPointerException();
        }

        quantFrutas--;
        Transmissor.avisoMudouMochila(
                classe,
                bolso.get(classe).size() - 1,
                dono.getID()
        );
        
        return bolso.get(classe).pop();
    }
}
