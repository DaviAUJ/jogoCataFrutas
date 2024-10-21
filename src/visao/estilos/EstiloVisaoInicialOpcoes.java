package visao.estilos;

import visao.VisaoInicialOpcoes;

import javax.swing.*;
import java.awt.*;

public abstract class EstiloVisaoInicialOpcoes {
    private static int ALTURA = 720;
    private static int LARGURA = 1280;


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
        Image imagemFundo = new ImageIcon("./assets/imgs/opcoesIniciais/fundoMontanha.png").getImage();

        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/geral/borda.png");

        FundoAnimado fundo = new FundoAnimado(imagemFundo, -800, 0);
        fundo.setBounds(0, 0, LARGURA, ALTURA);

        JLabel borda = new JLabel(imagemBorda);
        borda.setBounds(0, 0, LARGURA, ALTURA);
        borda.setLayout(null);



        fundo.add(borda);
        tela.add(fundo);
        fundo.iniciarAnimacao(50);
        return borda;

    }

    private static void estiloBotaoCarregar(JButton botao){
        int botaoAltura = (int)(167*1.6);
        int botaoLargura = (int)(242*1.6);
        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/btnCarregar.png");
        spriteBotao.setImage(spriteBotao.getImage().getScaledInstance(botaoLargura, botaoAltura, Image.SCALE_SMOOTH));


        botao.setBounds(LARGURA/2 - botaoLargura/2, ALTURA/2 - botaoAltura/2-20, botaoLargura, botaoAltura);
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        Estilos.animacaoClicavel(botao);

    }

    private static void estiloBotoesSelecao(JButton botaoVoltar, JButton botaoOk){
        int botaoVoltarAltura = 34+10;
        int botaoVoltarLargura = 138+10;

        int botaoOkAltura = 34+10;
        int botaoOkLargura = 70+10;

        estiloBtnVoltar(botaoVoltar);
        botaoVoltar.setBounds(LARGURA - botaoVoltarLargura - 120, ALTURA - botaoVoltarAltura - 50, botaoVoltarLargura, botaoVoltarAltura);
        Estilos.animacaoClicavel(botaoVoltar);

        /*
        estiloBtnOk(botaoOk);
        botaoOk.setBounds(LARGURA - botaoVoltarLargura - botaoOkLargura - 140, ALTURA - botaoOkAltura - 51, botaoOkLargura, botaoOkAltura);
        Estilos.animacaoClicavel(botaoOk);


         */



    }

    private static void estiloBotaoNovoJogo(JButton botao){
        int botaoAltura = (int)(45*1.5);
        int botaoLargura = (int)(244*1.5);

        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/jogarDoInicio.png");
        spriteBotao.setImage(spriteBotao.getImage().getScaledInstance(botaoLargura, botaoAltura, Image.SCALE_SMOOTH));


        botao.setBounds(LARGURA/2 - botaoLargura/2, ALTURA - botaoAltura*3, botaoLargura, botaoAltura);
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        Estilos.animacaoClicavel(botao);

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
