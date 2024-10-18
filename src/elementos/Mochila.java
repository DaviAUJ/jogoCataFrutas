package elementos;

import excecoes.*;
import frutas.Fruta;
import frutas.Maracuja;
import utilitarios.GerenciadorArquivo;
import java.util.ArrayList;

// Essa classe funciona como se fosse uma stack estranha de frutas
public class Mochila {
    private final ArrayList<Fruta> bolso;

    private final int capacidade;
    private int numeroMaracujas;

    Mochila() {
        GerenciadorArquivo arquivo = new GerenciadorArquivo(GerenciadorArquivo.caminhoPadrao);

        capacidade = arquivo.pegarEspacoMochila();
        numeroMaracujas = 0;
        bolso = new ArrayList<Fruta>();
    }

    public int quantidadeFrutas() {
        return bolso.size();
    }

    public int getNumeroMaracujas() {
        return numeroMaracujas;
    }

    // Retorno topo da stack
    public Fruta bizoiar() {
        return bolso.getLast();
    }

    public boolean taCheia() {
        return capacidade == bolso.size();
    }

    // Serve como push da stack
    public void colocar(Fruta fruta) throws MochilaCheiaException {
        if(taCheia()) {
            throw new MochilaCheiaException("Mochila cheia");
        }

        if(fruta instanceof Maracuja) {
            numeroMaracujas++;
        }

        bolso.add(fruta);
    }

    // Essa função funciona como um pop da stack porem apenas para todas as frutas exceto maracujá
    public Fruta tirarFrutaNormal() throws MochilaSemFrutaNormalException {
        Fruta saida;

        if(bolso.size() == numeroMaracujas) {
            throw new MochilaSemFrutaNormalException("Mochila sem fruta normal");
        }

        for(int i = bolso.size() - 1; i >= 0; i--) {
            saida = bolso.get(i);

            if(!(saida instanceof Maracuja)) {
                bolso.remove(i);

                return saida;
            }
        }

        return null;
    }

    // Essa função será usada na hora que o jogador empurrar outro
    // Funciona como a função de cima mas para todas os os tipos de fruta e também pode ser repetida ali
    public Fruta[] derrubar(int quantidade) throws MochilaVaziaException {
        Fruta[] saida = new Fruta[quantidade];

        if(quantidade > bolso.size()) {
            throw new MochilaVaziaException("Não é possível derrubar o número de frutas especificado");
        }

        for(int i = bolso.size() - 1; i >= bolso.size() - quantidade; i--) {
            saida[i] = bolso.get(i);

            if(bolso.get(i) instanceof Maracuja) {
                numeroMaracujas--;
            }

            bolso.remove(i);
        }

        return saida;
    }
}
