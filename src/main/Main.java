package main;

import com.formdev.flatlaf.FlatDarkLaf;
import sons.EventoSonoroHandler;
import sons.Tocador;
import utilitarios.GerenciadorArquivo;
import utilitarios.Transmissor;
import visao.*;
import jogoCataFrutas.*;
import visao.estilos.Estilos;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;


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
        GerenciadorDeTelas gerenciadorDeTelas = new GerenciadorDeTelas(principal);
        principal.setGerenciador(gerenciadorDeTelas);

        Estilos.visaoPrincipal(principal);
        Tocador.configurarListener();
        EventoSonoroHandler.musicaAbstracao();

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("solicitacaoNovoJogo")){
                    HashMap <String, Object> info = (HashMap<String, Object>) gerenciadorDeTelas.pegarInformacaoCache("infoJogo");

                    System.out.println(info);

                    Transmissor.setJogoDoMomento(null);

                    Configuracoes.nomeJogador1 =  (String) info.get("nomeJogador1");
                    Configuracoes.nomeJogador2 =  (String) info.get("nomeJogador2");
                    Configuracoes.dimensao = (int) info.get("dimensao");
                    Configuracoes.qtdMaracujasTotal = (int) info.get("totalMaracujas");
                    ArrayList <Integer> qtdTipoArvores =  (ArrayList<Integer>) info.get("qtdTipoArvores");
                    ArrayList <Integer> qtdFrutasChao =  (ArrayList<Integer>) info.get("qtdFrutasChao");

                    Configuracoes.qtdMaracujasNoChao = qtdFrutasChao.getFirst();

                    Configuracoes.espacoMochila = (int) info.get("espacoMochila");
                    Configuracoes.chanceFrutaBichada = (int) info.get("chanceBichadas");
                    Configuracoes.qtdPedras = (int) info.get("quantPedras");


                    Configuracoes.qtdAbacatesArvore = qtdTipoArvores.getFirst();
                    Configuracoes.qtdAbacatesChao = qtdFrutasChao.get(1);
                    Configuracoes.qtdAcerolasArvore = qtdTipoArvores.get(1);
                    Configuracoes.qtdAcerolasChao = qtdFrutasChao.get(2);
                    Configuracoes.qtdAmorasArvore = qtdTipoArvores.get(2);
                    Configuracoes.qtdAmorasChao = qtdFrutasChao.get(3);
                    Configuracoes.qtdCocosArvore = qtdTipoArvores.get(3);
                    Configuracoes.qtdCocosChao = qtdFrutasChao.get(4);
                    Configuracoes.qtdGoiabasArvore = qtdTipoArvores.get(4);
                    Configuracoes.qtdGoiabasChao = qtdFrutasChao.get(5);
                    Configuracoes.qtdLaranjaArvore = qtdTipoArvores.get(5);
                    Configuracoes.qtdLaranjaChao = qtdFrutasChao.get(6);


                    Jogo novoJogo = new Jogo();
                    Transmissor.setJogoDoMomento(novoJogo);


                }
            }
        });

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("solicitacaoListaSalvamentos")){
                    ArrayList<GerenciadorArquivo> arquivos = GerenciadorArquivo.resgatarSaves();

                    Transmissor.adicionarDados("salvamentos", arquivos);
                }
            }
        });


        VisaoInicio telaInicio = new VisaoInicio(gerenciadorDeTelas);
        telaInicio.setDoubleBuffered(true);
        VisaoInicialOpcoes telaOpcoes = new VisaoInicialOpcoes(gerenciadorDeTelas);
        telaOpcoes.setDoubleBuffered(true);
        VisaoNovoJogo telaNovoJogo = new VisaoNovoJogo(gerenciadorDeTelas);
        telaNovoJogo.setDoubleBuffered(true);
        VisaoNomesJogadores telaNomes = new VisaoNomesJogadores(gerenciadorDeTelas);
        telaNomes.setDoubleBuffered(true);


        gerenciadorDeTelas.adicionarNovaTela("INICIO", telaInicio);
        gerenciadorDeTelas.adicionarNovaTela("OPCOES INICIAIS", telaOpcoes);
        gerenciadorDeTelas.adicionarNovaTela("NOVO JOGO", telaNovoJogo);
        gerenciadorDeTelas.adicionarNovaTela("NOMES DOS JOGADORES", telaNomes);


        gerenciadorDeTelas.irParaTela("INICIO");

        principal.setVisible(true);


        /*
        JFrame modScreen = new JFrame();
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modScreen.setBounds(0, 0, 200, 600);
        modScreen.setVisible(true);

        */







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
