package elementos;

import excecoes.*;

import frutas.*;
import utilitarios.GerenciadorArquivo;
import utilitarios.Transmissor;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.lang.NullPointerException;

public class Mochila {
    private final HashMap<Class<? extends Fruta>, Stack<Fruta>> bolso;

    private final int capacidade;
    private int quantFrutas = 0;

    private Jogador dono;

    public Mochila(Jogador dono) {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        capacidade = arquivo.pegarEspacoMochila();

        bolso = new HashMap<>(5);
        bolso.put(Maracuja.class, new Stack<>());
        bolso.put(Abacate.class, new Stack<>());
        bolso.put(Coco.class, new Stack<>());
        bolso.put(Laranja.class, new Stack<>());
        bolso.put(Amora.class, new Stack<>());
        bolso.put(Acerola.class, new Stack<>());
        bolso.put(Goiaba.class, new Stack<>());

        this.dono = dono;
    }

    public Boolean taCheia() {
        return capacidade == quantFrutas;
    }

    protected HashMap<Class<? extends Fruta>, Stack<Fruta>> getBolso() {
        return bolso;
    }

    public int getQuantFrutas() {
        return quantFrutas;
    }

    public int getQuantMaracujas() {
        return bolso.get(Maracuja.class).size();
    }

    public int getQuantLaranjas() {
        return bolso.get(Laranja.class).size();
    }

    public int getQuantCocos() {
        return bolso.get(Coco.class).size();
    }

    public int getQuantGenericas() {
        return bolso.get(Generica.class).size();
    }

    public int getQuantAbacates() {
        return bolso.get(Abacate.class).size();
    }

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
