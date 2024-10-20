package visao.estilos;

import visao.VisaoCarregarJogo;
import visao.VisaoNomesJogadores;
import visao.componentes.EspacoSalvamento;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class EstiloVisaoCarregarJogo {

    private static int TELA_LARGURA = 1280;
    private static int TELA_ALTURA = 720;



    public static void aplicarEstilo(VisaoCarregarJogo tela){
        tela.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        tela.setLayout(null);

        JLabel fundo = configurarFundo(tela);
        JLabel titulo = estiloTitulo();
        tela.btnVoltar = new JButton();


        estiloBotoesSelecao(tela.btnVoltar);

        tela.saves.getFirst().setLocation(150, 140);
        tela.saves.get(1).setLocation(650, 140);
        tela.saves.get(2).setLocation(150, 380);
        tela.saves.get(3).setLocation(650, 380);

        for (JPanel save : tela.saves){
            Estilos.animacaoClicavel(save);
            fundo.add(save);
        }

        fundo.add(titulo);
        fundo.add(tela.btnVoltar);




    }

    private static JLabel configurarFundo(VisaoCarregarJogo tela){
        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/geral/borda.png");
        Image imagemFundo = new ImageIcon("./assets/imgs/carregarJogo/fundoKunst.png").getImage();

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

    private static JLabel estiloTitulo(){
        ImageIcon imagem = new ImageIcon("./assets/imgs/carregarJogo/titulo.png");
        int TITULO_LARGURA = imagem.getIconWidth();
        int TITULO_ALTURA = imagem.getIconHeight();

        JLabel titulo = new JLabel(imagem);

        titulo.setIcon(imagem);
        titulo.setBounds((TELA_LARGURA-TITULO_LARGURA)/2, 60, TITULO_LARGURA, TITULO_ALTURA);
        return titulo;
    }

    private static void estiloBotoesSelecao(JButton botaoVoltar) {
        int botaoVoltarAltura = 34 + 10;
        int botaoVoltarLargura = 138 + 10;


        estiloBtnVoltar(botaoVoltar);
        botaoVoltar.setBounds(TELA_LARGURA - botaoVoltarLargura - 120, TELA_ALTURA - botaoVoltarAltura - 50, botaoVoltarLargura, botaoVoltarAltura);
        Estilos.animacaoClicavel(botaoVoltar);
    }

    private static void estiloBtnVoltar(JButton botao){

        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/voltar.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);

    }
}
