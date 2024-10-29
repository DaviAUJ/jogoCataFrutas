package visao.estilos;

import visao.VisaoNomesJogadores;
import visao.VisaoNovoJogo;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável por aplicar estilos à visão de nomes dos jogadores.
 */
public abstract class EstiloVisaoNomesJogadores {

    private static int TELA_LARGURA = 1280;
    private static int TELA_ALTURA = 720;

    /**
     * Aplica o estilo à tela de nomes dos jogadores.
     *
     * @param tela A instância de VisaoNomesJogadores que será estilizada.
     */
    public static void aplicarEstilo(VisaoNomesJogadores tela){
        tela.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        tela.setLayout(null);

        JLabel fundo = configurarFundo(tela);
        tela.btnOk = new JButton();
        tela.btnVoltar = new JButton();
        tela.nomeJogador1 = new JTextField();
        tela.nomeJogador2 = new JTextField();

        estiloBotoesSelecao(tela.btnVoltar, tela.btnOk);
        JPanel painelNomesJogadores = estiloNomesJogadores(tela.nomeJogador1, tela.nomeJogador2);

        fundo.add(tela.btnVoltar);
        fundo.add(tela.btnOk);
        fundo.add(painelNomesJogadores);





    }

    /**
     * Configura o fundo da tela de nomes dos jogadores.
     *
     * @param tela A instância de VisaoNomesJogadores.
     * @return Um JLabel que representa a borda do fundo.
     */
    private static JLabel configurarFundo(VisaoNomesJogadores tela){
        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/geral/borda.png");
        Image imagemFundo = new ImageIcon("./assets/imgs/novoJogo/nomesJogadores/fundoBosque.png").getImage();

        JLabel borda = new JLabel(imagemBorda);
        borda.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        borda.setOpaque(false);

        FundoAnimado fundo = new FundoAnimado(imagemFundo, -800, 0);
        fundo.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        fundo.setOpaque(true);

        fundo.add(borda);
        tela.add(fundo);
        fundo.iniciarAnimacao(50);
        return borda;
    }

    /**
     * Estiliza o botão "Voltar".
     *
     * @param botao O JButton que será estilizado.
     */
    private static void estiloBtnVoltar(JButton botao){

        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/voltar.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);

    }

    /**
     * Estiliza o botão "OK".
     *
     * @param botao O JButton que será estilizado.
     */
    private static void estiloBtnOk(JButton botao){
        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/ok.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
    }

    /**
     * Estiliza os botões de seleção.
     *
     * @param botaoVoltar O botão "Voltar".
     * @param botaoOk     O botão "OK".
     */
    private static void estiloBotoesSelecao(JButton botaoVoltar, JButton botaoOk){
        int botaoVoltarAltura = 34+10;
        int botaoVoltarLargura = 138+10;

        int botaoOkAltura = 34+10;
        int botaoOkLargura = 70+10;

        estiloBtnVoltar(botaoVoltar);
        botaoVoltar.setBounds(TELA_LARGURA - botaoVoltarLargura - 120, TELA_ALTURA - botaoVoltarAltura - 50, botaoVoltarLargura, botaoVoltarAltura);
        Estilos.animacaoClicavel(botaoVoltar);


        estiloBtnOk(botaoOk);
        botaoOk.setBounds(TELA_LARGURA - botaoVoltarLargura - botaoOkLargura - 140, TELA_ALTURA - botaoOkAltura - 51, botaoOkLargura, botaoOkAltura);
        Estilos.animacaoClicavel(botaoOk);

    }

    /**
     * Estiliza os campos de entrada de nomes dos jogadores.
     *
     * @param nomeJogador1 O campo de texto para o nome do jogador 1.
     * @param nomeJogador2 O campo de texto para o nome do jogador 2.
     * @return Um JPanel contendo os campos de entrada.
     */
    public static JPanel estiloNomesJogadores(JTextField nomeJogador1, JTextField nomeJogador2){
        JPanel painelNomes = new JPanel();
        painelNomes.setLayout(null);

        ImageIcon imagemTextoJ1 = new ImageIcon("./assets/imgs/novoJogo/nomesJogadores/nome1.png");
        ImageIcon imagemTextoJ2 = new ImageIcon("./assets/imgs/novoJogo/nomesJogadores/nome2.png");

        JPanel caixaJogador1 = estiloNomeInput(imagemTextoJ1, nomeJogador1);
        JPanel caixaJogador2 = estiloNomeInput(imagemTextoJ2, nomeJogador2);

        caixaJogador1.setLocation(5, 10);
        caixaJogador2.setLocation(10, caixaJogador1.getHeight()+10);

        int PAINEL_LARGURA = caixaJogador1.getWidth() + 20;
        int PAINEL_ALTURA = caixaJogador2.getHeight()*2 + 20;

        painelNomes.setBounds((TELA_LARGURA-PAINEL_LARGURA) / 2, (TELA_ALTURA-PAINEL_ALTURA) / 2, PAINEL_LARGURA, PAINEL_ALTURA );
        painelNomes.setOpaque(false);
        painelNomes.add(caixaJogador1);
        painelNomes.add(caixaJogador2);

        return painelNomes;

    }

    /**
     * Estiliza o campo de entrada para o nome de um jogador.
     *
     * @param imagemTexto  A imagem que será exibida como rótulo.
     * @param nomeJogador  O campo de texto para o nome do jogador.
     * @return Um JPanel contendo o rótulo e a borda do campo de entrada.
     */
    public static JPanel estiloNomeInput(ImageIcon imagemTexto, JTextField nomeJogador){
        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/novoJogo/nomesJogadores/bordaNome.png");
        JLabel labelNomeJogador = new JLabel(imagemTexto);
        JLabel bordaNome = new JLabel(imagemBorda);
        JPanel caixa = new JPanel();
        caixa.setLayout(null);

        nomeJogador.setBounds(15, 15, imagemBorda.getIconWidth()-30, imagemBorda.getIconHeight()-30);
        labelNomeJogador.setBounds(0, (imagemBorda.getIconHeight() - imagemTexto.getIconHeight())/2, imagemTexto.getIconWidth(), imagemTexto.getIconHeight());
        bordaNome.setBounds(imagemTexto.getIconWidth(), 0, imagemBorda.getIconWidth(), imagemBorda.getIconHeight());
        bordaNome.add(nomeJogador);

        caixa.setSize(imagemTexto.getIconWidth()+imagemBorda.getIconWidth(), imagemBorda.getIconHeight());
        caixa.setOpaque(false);
        nomeJogador.setBackground(new Color(0, 0, 0, 0));
        nomeJogador.setFont(new Font("Arial", Font.PLAIN, 24));
        nomeJogador.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        nomeJogador.setForeground(new Color(255, 255, 255));
        caixa.add(labelNomeJogador);
        caixa.add(bordaNome);
        return caixa;

    }
}
