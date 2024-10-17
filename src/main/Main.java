package main;

import com.formdev.flatlaf.FlatDarkLaf;
import visao.*;
import jogoCataFrutas.*;
import visao.estilos.Estilos;

import javax.swing.*;



/**
 * Classe principal do jogo Cata Frutas.
 * Esta classe inicializa o jogo e a interface do usuário.
 */

public class Main {

    /**
     * Método principal que inicia a execução do programa.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */

    public static void main(String[] args) {
        Jogo novoJogo = new Jogo();
        final int SIZE = 800;

        FlatDarkLaf.setup();

        VisaoPrincipal principal = new VisaoPrincipal();
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GerenciadorDeTelas gerenciadorDeTelas = new GerenciadorDeTelas(principal);
        principal.setGerenciador(gerenciadorDeTelas);

        VisaoInicio telaInicio = new VisaoInicio(gerenciadorDeTelas);
        VisaoInicialOpcoes telaOpcoes = new VisaoInicialOpcoes(gerenciadorDeTelas);


        gerenciadorDeTelas.adicionarNovaTela("INICIO", telaInicio);
        gerenciadorDeTelas.adicionarNovaTela("OPCOES INICIAIS", telaOpcoes);

        gerenciadorDeTelas.irParaTela("INICIO");

        Estilos.visaoPrincipal(principal);
        principal.setVisible(true);









        /*

        boolean conseguiuGerar = novoJogo.getFloresta().gerarTerreno();
        if (!conseguiuGerar) {
            JOptionPane.showMessageDialog(null, "As especificações informadas no arquivo de configuração não permitem uma criação consistente do mapa.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;

        }



        VisaoPrincipal visaoPrincipal = new VisaoPrincipal(SIZE);
        VisaoTerreno visaoTerreno = new VisaoTerreno(novoJogo.getFloresta(), SIZE);

        visaoPrincipal.add(visaoTerreno);
        visaoPrincipal.setVisible(true);
        visaoPrincipal.setLocationRelativeTo(null);


        */


    }

}
