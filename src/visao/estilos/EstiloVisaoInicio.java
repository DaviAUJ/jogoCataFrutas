package visao.estilos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import visao.*;

public abstract class EstiloVisaoInicio {
    public static final int LARGURA = 800;
    public static final int ALTURA = 450;


    public static void aplicarEstilo(VisaoInicio visaoInicio){

        visaoInicio.setBounds(0, 0, LARGURA, ALTURA);
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

    public static JLabel construirFundo(VisaoInicio inicio){
        ImageIcon imagemDeFundoFloresta = new ImageIcon("./assets/imgs/inicio/fundoFloresta.png");
        ImageIcon bordaFundo = new ImageIcon("./assets/imgs/inicio/borda.png");

        JLabel fundo = new JLabel(imagemDeFundoFloresta);
        fundo.setBounds(0, 0, LARGURA, ALTURA);

        JLabel borda = new JLabel(bordaFundo);
        borda.setBounds(0, 0, LARGURA, ALTURA);

        fundo.add(borda);
        inicio.add(fundo);
        return borda;
    }

    public static void estiloTitulo(JLabel titulo){
        ImageIcon spriteTitulo = new ImageIcon("./assets/imgs/inicio/cata-frutas.png");
        titulo.setIcon(spriteTitulo);

        titulo.setBounds(0, 0, LARGURA, ALTURA);

        ActionListener acaoDoTimer = new ActionListener() {
            private int estadoAnimacaoAtual = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                Timer contadorAtual = (Timer) e.getSource();

                switch (estadoAnimacaoAtual){
                    case 0:{
                        estadoAnimacaoAtual = 1;
                        titulo.setBounds(0, -5, LARGURA, ALTURA);
                        contadorAtual.setDelay(2000);

                        break;
                    }
                    case 1:{
                        estadoAnimacaoAtual = 0;
                        titulo.setBounds(0, 0, LARGURA, ALTURA);
                        contadorAtual.setDelay(3000);

                        break;
                    }
                }
            }
        };

        Timer contador = new Timer(3000, acaoDoTimer);
        contador.start();




    }

    public static void estiloBotao(JButton botao){
        ImageIcon spriteTexto = new ImageIcon("./assets/imgs/inicio/btnIniciarJogo.png");
        botao.setIcon(spriteTexto);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorder(null);
        botao.setBounds(400-114, 325-33, 228, 65);

        botao.addMouseListener(new MouseAdapter() {
            private ImageIcon icon = (ImageIcon) botao.getIcon();

            @Override
            public void mouseEntered(MouseEvent e) {
                Image imagem = icon.getImage();
                imagem = imagem.getScaledInstance(230, 70, Image.SCALE_SMOOTH);

                botao.setBounds(400-115, 325-35, 230, 70);
                botao.setIcon(new ImageIcon(imagem));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Image imagem = icon.getImage();
                imagem = imagem.getScaledInstance(228, 65, Image.SCALE_SMOOTH);

                botao.setBounds(400-114, 325-33, 228, 65);
                botao.setIcon(new ImageIcon(imagem));
            }


        });
    }


}
