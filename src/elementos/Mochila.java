package elementos;

import excecoes.*;
import frutas.*;
import utilitarios.GerenciadorArquivo;

import java.util.HashMap;
import java.util.Stack;

public class Mochila {
    private final HashMap<Class<? extends Fruta>, Stack<Fruta>> bolso;

    private final int capacidade;
    private int quantFrutas = 0;

    public Mochila() {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        capacidade = arquivo.pegarEspacoMochila();

        bolso = new HashMap<>(5);
        bolso.put(Abacate.class, new Stack<>());
        bolso.put(Coco.class, new Stack<>());
        bolso.put(Generica.class, new Stack<>());
        bolso.put(Laranja.class, new Stack<>());
        bolso.put(Maracuja.class, new Stack<>());
    }

    public Boolean taCheia() {
        return capacidade == quantFrutas;
    }

    public int getQuantFrutas() {
        return quantFrutas;
    }

    public void guardar(Fruta fruta) throws MochilaCheiaException {
        if(taCheia()) {
            throw new MochilaCheiaException("Mochila em sua capacidade máxima");
        }

        quantFrutas++;
        bolso.get(fruta.getClass()).push(fruta);
    }

    public Fruta tirar(Class<? extends Fruta> classe) throws MochilaVaziaException, BolsoFrutaVazioException {
        if(quantFrutas == 0) {
            throw new MochilaVaziaException("Mochila vazia");
        }

        if(bolso.get(classe).isEmpty()) {
            throw new BolsoFrutaVazioException("Bolso fruta vazio");
        }

        quantFrutas--;
        return bolso.get(classe).pop();
    }


}
