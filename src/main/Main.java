package main;

import com.formdev.flatlaf.FlatDarkLaf;
import utilitarios.Transmissor;
import visao.*;
import jogoCataFrutas.*;
import visao.estilos.Estilos;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


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

        FlatDarkLaf.setup();
        UIManager.put("ScrollBar.thumb", new Color(64, 64, 159)); // Cor da "polegada" (thumb)
        UIManager.put("ScrollBar.thumbHover", new Color(120, 120, 255)); // Cor quando o mouse passa sobre
        UIManager.put("ScrollBar.track", new Color(240, 240, 240)); // Cor do fundo da trilha
        UIManager.put("ScrollBar.width", 5); // Largura da ScrollBar
        UIManager.put("ScrollBar.thumbArc", 8); // Bordas arredondadas da "polegada"
        UIManager.put("ScrollBar.trackArc", 8); // Bordas arredondadas da trilha
        UIManager.put("ScrollBar.minThumbSize", new Dimension(30, 30));
        UIManager.put("Component.arc", 0); // Remove arredondamento
        UIManager.put("Component.focusWidth", 0); // Remove o efeito de foco
        UIManager.put("Component.innerFocusWidth", 0); // Remove animações de foco interno
        UIManager.put("FormattedTextField.caretBlinkRate", 0);
        UIManager.put("Component.focusedBackground", null);  // Remove a cor de fundo quando o botão tem foco
        UIManager.put("Component.hoverBackground", null);    // Remove o fundo quando o botão está em hover
        UIManager.put("Component.pressedBackground", null);  // Remove a cor de fundo quando o botão é pressionado
        UIManager.put("Component.arc", 0);

        VisaoPrincipal principal = new VisaoPrincipal();
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Transmissor transmissor = new Transmissor();
        GerenciadorDeTelas gerenciadorDeTelas = new GerenciadorDeTelas(principal, transmissor);
        principal.setGerenciador(gerenciadorDeTelas);

        VisaoInicio telaInicio = new VisaoInicio(gerenciadorDeTelas);
        telaInicio.setDoubleBuffered(true);
        VisaoInicialOpcoes telaOpcoes = new VisaoInicialOpcoes(gerenciadorDeTelas);
        telaOpcoes.setDoubleBuffered(true);
        VisaoNovoJogo telaNovoJogo = new VisaoNovoJogo(gerenciadorDeTelas);
        telaNovoJogo.setDoubleBuffered(true);
        VisaoNomesJogadores telaNomes = new VisaoNomesJogadores(gerenciadorDeTelas);


        gerenciadorDeTelas.adicionarNovaTela("INICIO", telaInicio);
        gerenciadorDeTelas.adicionarNovaTela("OPCOES INICIAIS", telaOpcoes);
        gerenciadorDeTelas.adicionarNovaTela("NOVO JOGO", telaNovoJogo);
        gerenciadorDeTelas.adicionarNovaTela("NOMES DOS JOGADORES", telaNomes);

        gerenciadorDeTelas.irParaTela("INICIO");

        Estilos.visaoPrincipal(principal);
        principal.setVisible(true);

        transmissor.setGerenciador(gerenciadorDeTelas);
        transmissor.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("solicitacaoNovoJogo")){
                    Jogo novoJogo = new Jogo();

                    transmissor.setJogoDoMomento(novoJogo);


                }
            }
        });









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
