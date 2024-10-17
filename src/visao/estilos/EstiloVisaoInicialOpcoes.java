package visao.estilos;

import visao.VisaoInicialOpcoes;

import javax.swing.*;
import java.awt.*;

public abstract class EstiloVisaoInicialOpcoes {
    private static int ALTURA = 450;
    private static int LARGURA = 800;


    public static void aplicarEstilo(VisaoInicialOpcoes tela){
        tela.setBounds(0, 0, LARGURA, ALTURA);
        tela.setLayout(null);

        tela.btnCarregarJogo = new JButton();
        tela.btnJogarDoInicio = new JButton();
        tela.btnVoltar = new JButton();
        tela.btnOk = new JButton();

        JLabel fundo = configurarFundo(tela);
        estiloBotaoCarregar(tela.btnCarregarJogo);
        estiloBotaoNovoJogo(tela.btnJogarDoInicio);

        estiloBotoesSelecao(tela.btnVoltar, tela.btnOk);

        fundo.add(tela.btnCarregarJogo);
        fundo.add(tela.btnJogarDoInicio);
        fundo.add(tela.btnVoltar);
        fundo.add(tela.btnOk);




    }

    private static JLabel configurarFundo(VisaoInicialOpcoes tela){
        ImageIcon imagemFundo = new ImageIcon("./assets/imgs/opcoesIniciais/fundoMontanha.png");
        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/opcoesIniciais/borda.png");

        JLabel fundo = new JLabel(imagemFundo);
        fundo.setBounds(0, 0, LARGURA, ALTURA);

        JLabel borda = new JLabel(imagemBorda);
        borda.setBounds(0, 0, LARGURA, ALTURA);
        borda.setLayout(null);



        fundo.add(borda);
        tela.add(fundo);
        return borda;

    }

    private static void estiloBotaoCarregar(JButton botao){
        int botaoAltura = 167;
        int botaoLargura = 242;
        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/btnCarregar.png");


        botao.setBounds(LARGURA/2 - botaoLargura/2, ALTURA/2 - botaoAltura/2-20, botaoLargura, botaoAltura);
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);

    }

    private static void estiloBotoesSelecao(JButton botaoVoltar, JButton botaoOk){
        int botaoVoltarAltura = 34;
        int botaoVoltarLargura = 138;

        int botaoOkAltura = 34;
        int botaoOkLargura = 70;

        estiloBtnVoltar(botaoVoltar);
        botaoVoltar.setBounds(LARGURA - botaoVoltarLargura - 100, ALTURA - botaoVoltarAltura - 20, botaoVoltarLargura, botaoVoltarAltura);


        estiloBtnOk(botaoOk);
        botaoOk.setBounds(LARGURA - botaoVoltarLargura - botaoOkLargura - 120, ALTURA - botaoOkAltura - 21, botaoOkLargura, botaoOkAltura);



    }

    private static void estiloBotaoNovoJogo(JButton botao){
        int botaoAltura = 45;
        int botaoLargura = 244;

        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/jogarDoInicio.png");


        botao.setBounds(LARGURA/2 - botaoLargura/2, ALTURA - botaoAltura*3, botaoLargura, botaoAltura);
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);

    }

    private static void estiloBtnVoltar(JButton botao){

        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/voltar.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
    }

    private static void estiloBtnOk(JButton botao){
        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/ok.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
    }


}
