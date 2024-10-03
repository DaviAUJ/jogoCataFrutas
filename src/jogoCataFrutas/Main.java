package jogoCataFrutas;
import cenario.*;
import utilitarios.GerenciadorArquivo;

public class Main {

    public static void main (String[] args){
    	Jogo jogo = new Jogo();
    
        jogo.floresta.imprimirTerreno();
        System.out.println(jogo.floresta.getDimensao());
    }

}
