package jogoCataFrutas;
import cenario.*;

public class Main {

    public static void main (String[] args){

        Terreno teste = new Terreno(7);

        teste.gerarElementosAleatorios(5, "Abacate");
        teste.gerarElementosAleatorios(10, "Arvore");
        teste.gerarTerreno();
    }

}
