package jogoCataFrutas;

import java.util.Scanner;

import elementos.Jogador;
import elementos.Terreno;
import visao.VisaoPrincipal;
import visao.VisaoTerreno;
import frutas.*;

/**
 * Representa o jogo Cata Frutas.
 * Esta classe gerencia os jogadores, o terreno e o estado do jogo.
 */

public class Jogo {
    private int contadorRodada = 1;
    private String estado;

    protected Terreno floresta = new Terreno();
    
    //ESSES ATRIBUTOS AQUI SE TORNARAM INUTEIS
    //APAGAR DEPOIS
    VisaoPrincipal visaoPrincipal;
    VisaoTerreno visaoTerreno;

    /**
     * Construtor da classe Jogo.
     * Inicializa um novo jogo com um terreno e dois jogadores.
     */

    public Jogo() {
    	estado = "NoMenuInicial";
    }
    
    public int getContadorRodada() {
		return contadorRodada;
	}

    public void contarRodada() {
        contadorRodada++;
    }

    /**
     * Obtém o estado atual do jogo.
     *
     * @return uma String representando o estado do jogo.
     */

    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado do jogo.
     *
     * @param estado uma String representando o novo estado do jogo.
     */

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtém o terreno do jogo.
     *
     * @return o objeto Terreno que representa a floresta do jogo.
     */

    public Terreno getFloresta() {
        return this.floresta;
    }
    
    // ESSAS QUATRO FUNÇÕES ABAIXO SERÃO INUTEIS NO FUTURO
    // LEMBRETE PARA NÃO FAZER JAVADOC A TOA. DEPOIS APAGAR
    private void criarJanela() {
    	visaoPrincipal = new VisaoPrincipal(800);
        visaoTerreno = new VisaoTerreno(floresta, 800);
        
        visaoPrincipal.add(visaoTerreno);
        visaoPrincipal.setVisible(true);
        visaoPrincipal.setLocationRelativeTo(null);
    }
    
    private void atualizarJanela() {
    	visaoTerreno = new VisaoTerreno(getFloresta(), 800);

    	visaoPrincipal.add(visaoTerreno);
    	visaoPrincipal.setVisible(true);
    }
    
    private char pegarInput() {
    	char input;
    	
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Comando: ");
        input = scanner.next().charAt(0);
        
        return input;
    }

    private Class<? extends Fruta>  pegarInputFruta() {
        char input;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Fruta: ");
        input = scanner.next().charAt(0);

        return switch (input) {
            case 'a' -> Abacate.class;
            case 'l' -> Laranja.class;
            case 'g' -> Generica.class;
            case 'c' -> Coco.class;
            default -> null;
        };
    }

    /**
     * Inicia o jogo.
     */

    public void iniciarPartida() {   	        
    	Jogador jogadorDaVez = floresta.getJogador1();
    	
    	estado = "EmPartida";
    	
    	criarJanela();
        
        while(estado.equals("EmPartida")) {
        	jogadorDaVez.gerarPontos();
            jogadorDaVez.resetarMovimento();
            jogadorDaVez.atualizarCooldowns();
            jogadorDaVez.setJaFoiEmpurrado(false);
        	
        	System.out.println(
                    "Rodada: "
                            + contadorRodada + " - "
                            + jogadorDaVez.getNome() + ": "
                            + jogadorDaVez.getPontosMovimento()
            );
        	
           	while(jogadorDaVez.getPontosMovimento() != 0) {
               try {
                   switch (pegarInput()) {
                       case 'd':
                           jogadorDaVez.moverDireita();
                           break;
                       case 'a':
                           jogadorDaVez.moverEsquerda();
                           break;
                       case 'w':
                           jogadorDaVez.moverCima();
                           break;
                       case 's':
                           jogadorDaVez.moverBaixo();
                           break;
                       case 'p':
                           jogadorDaVez.setPontosMovimento(0);
                           jogadorDaVez.pegarFrutaArvore();
                           break;
                       case 'f':
                           jogadorDaVez.catarFruta();
                           break;
                       case 'c':
                           System.out.println("Abacates: " + jogadorDaVez.abrirMochila().getQuantAbacates());
                           System.out.println("Laranjas: " + jogadorDaVez.abrirMochila().getQuantLaranjas());
                           System.out.println("Genericas: " + jogadorDaVez.abrirMochila().getQuantGenericas());
                           System.out.println("Cocos: " + jogadorDaVez.abrirMochila().getQuantCocos());
                           jogadorDaVez.comerFruta(pegarInputFruta());
                           break;
                   }

                   atualizarJanela();
               } catch (Exception e) {
                   System.out.println(e.getMessage());
               }
            }

            // Acabou a rodada reseta os status effects
            jogadorDaVez.setNerfBichada(false);
            jogadorDaVez.setBuffForca(false);

            // Trocando os jogadores
           	if(jogadorDaVez == floresta.getJogador1()) {
           		jogadorDaVez = floresta.getJogador2();
           	}
           	else {
           		jogadorDaVez = getFloresta().getJogador1();
                contarRodada();
           	}
           	
           	// Vendo se o jogador ganhou
           	if(jogadorDaVez.getPontosOuro() > floresta.getTotalMaracujas() / 2) {
           		estado = "Vitoria" + jogadorDaVez.getNome();
           	}
        }
    }
}
