package jogoCataFrutas;

import java.util.Scanner;

import elementos.Jogador;
import elementos.Terreno;
import visao.VisaoPrincipal;
import visao.VisaoTerreno;

/**
 * Representa o jogo Cata Frutas.
 * Esta classe gerencia os jogadores, o terreno e o estado do jogo.
 */

public class Jogo {
    private int contadorRodada = 1;
    private String estado = "";

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
    
    // ESSAS TRÊS FUNÇÕES ABAIXO SERÃO INUTEIS NO FUTURO
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

    /**
     * Inicia o jogo.
     *
     * @return true se o jogo foi iniciado com sucesso, false caso contrário.
     */

    public void iniciarPartida() {   	        
    	Jogador jogadorDaVez = floresta.getJogador1();
    	
    	estado = "EmPartida";
    	
    	criarJanela();
        
        while(estado.equals("EmPartida")) {
        	jogadorDaVez.gerarPontos();
        	
        	System.out.println(
                    "Rodada: "
                            + contadorRodada + " - "
                            + jogadorDaVez.getNome() + ": "
                            + jogadorDaVez.getPontosMovimento()
            );
        	
           	while(jogadorDaVez.getPontosMovimento() != 0) {
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
                        break;
                    case 'f':
                        jogadorDaVez.catarFruta();
                        break;
                }

                atualizarJanela();
            }

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
