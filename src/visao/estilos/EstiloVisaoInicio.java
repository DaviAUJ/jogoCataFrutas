package visao.estilos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import visao.*;

/**
 * Classe responsável por aplicar estilos e configurações visuais à tela inicial do jogo.
 */
public abstract class EstiloVisaoInicio {
    private static final int LARGURA_TELA = 1280;
    private static final int ALTURA_TELA = 720;


    /**
     * Aplica o estilo à tela inicial do jogo.
     *
     * @param visaoInicio A instância de VisaoInicio a ser estilizada.
     */
    public static void aplicarEstilo(VisaoInicio visaoInicio){

        visaoInicio.setBounds(0, 0, LARGURA_TELA, ALTURA_TELA);
        visaoInicio.setLayout(null);

        visaoInicio.tituloJogo = new JLabel();
        visaoInicio.iniciarJogo = new JButton();
        visaoInicio.tituloJogoAnimacao = 0;


        JLabel conteudo = construirFundo(visaoInicio);

        estiloTitulo(visaoInicio.tituloJogo);
        estiloBotao(visaoInicio.iniciarJogo);

        conteudo.add(visaoInicio.tituloJogo);
        conteudo.add(visaoInicio.iniciarJogo);

    }

    /**
     * Constrói o fundo da tela inicial do jogo.
     *
     * @param inicio A instância de VisaoInicio para a qual o fundo será configurado.
     * @return O JLabel que representa a borda do fundo.
     */
    private static JLabel construirFundo(VisaoInicio inicio){
        ImageIcon imagemDeFundoFloresta = new ImageIcon("./assets/imgs/inicio/fundoFloresta.png");
        Image imagemFundo = imagemDeFundoFloresta.getImage();

        ImageIcon bordaFundo = new ImageIcon("./assets/imgs/geral/borda.png");

        final int[] FUNDO_POSX = {-800};
        int FUNDO_POSY = 0;

        int BORDA_POSX = 0;
        int BORDA_POSY = 0;



        FundoAnimado fundo = new FundoAnimado(imagemFundo, -800, 0);

        fundo.setBounds(0, FUNDO_POSY, 2506, ALTURA_TELA);


        JLabel borda = new JLabel(bordaFundo);
        borda.setBounds(BORDA_POSX, BORDA_POSY, LARGURA_TELA, ALTURA_TELA);

        fundo.add(borda);
        inicio.add(fundo);
        fundo.iniciarAnimacao(50);
        return borda;
    }

    /**
     * Estiliza o título do jogo.
     *
     * @param titulo O JLabel que representa o título a ser estilizado.
     */
    private static void estiloTitulo(JLabel titulo){
        ImageIcon spriteTitulo = new ImageIcon("./assets/imgs/inicio/cata-frutas.png");
        titulo.setIcon(spriteTitulo);
        int TITULO_LARGURA = 800;
        int TITULO_ALTURA = 450;
        int TITULO_POSX = (LARGURA_TELA - TITULO_LARGURA)/2;
        int TITULO_POSY = (ALTURA_TELA - TITULO_ALTURA)/2;

        titulo.setBounds(TITULO_POSX, TITULO_POSY, TITULO_LARGURA, TITULO_ALTURA);

        ActionListener acaoDoTimer = new ActionListener() {
            private int estadoAnimacaoAtual = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                Timer contadorAtual = (Timer) e.getSource();

                switch (estadoAnimacaoAtual){
                    case 0:{
                        estadoAnimacaoAtual = 1;
                        titulo.setBounds(TITULO_POSX, TITULO_POSY-5, TITULO_LARGURA, TITULO_ALTURA);
                        contadorAtual.setDelay(700);

                        break;
                    }
                    case 1:{
                        estadoAnimacaoAtual = 0;
                        titulo.setBounds(TITULO_POSX, TITULO_POSY, TITULO_LARGURA, TITULO_ALTURA);
                        contadorAtual.setDelay(700);

                        break;
                    }
                }
            }
        };

        Timer contador = new Timer(700, acaoDoTimer);
        contador.start();




    }

    /**
     * Estiliza o botão de iniciar jogo.
     *
     * @param botao O JButton a ser estilizado.
     */
    private static void estiloBotao(JButton botao){
        ImageIcon spriteTexto = new ImageIcon("./assets/imgs/inicio/btnIniciarJogo.png");
        int BOTAO_LARGURA = 228;
        int BOTAO_ALTURA = 65;
        int BOTAO_POSX = (LARGURA_TELA - BOTAO_LARGURA)/2;
        int BOTAO_POSY = (ALTURA_TELA - BOTAO_ALTURA)/2 + 100;

        botao.setIcon(spriteTexto);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorder(null);
        botao.setBounds(BOTAO_POSX, BOTAO_POSY, BOTAO_LARGURA, BOTAO_ALTURA);

        Estilos.animacaoClicavel(botao);
    }


}
