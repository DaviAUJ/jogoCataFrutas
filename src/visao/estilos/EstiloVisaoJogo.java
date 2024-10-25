package visao.estilos;

import visao.VisaoJogo;
import visao.VisaoNomesJogadores;
import visao.componentes.TabuleiroJogo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class EstiloVisaoJogo {
    private static int TELA_LARGURA = 1280;
    private static int TELA_ALTURA = 720;

    public static void aplicarEstilo(VisaoJogo tela){
        tela.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        tela.setLayout(null);
        aplicarEstiloTabuleiro(tela.tabuleiro);

        JLabel fundo = configurarFundo(tela);
        JLabel inventarioJ1 = new JLabel();
        JLabel inventarioJ2 = new JLabel();

        inventariosJogadores(inventarioJ1, inventarioJ2);
        labelsValoresInventarios(tela.valoresInventario1, tela.valoresInventario2, fundo);


        fundo.add(tela.tabuleiro);
        fundo.add(bordaTabuleiro());
        fundo.add(indicadorRodadas(tela.rodada));
        fundo.add(tela.rodada);
        fundo.add(inventarioJ1);
        fundo.add(inventarioJ2);



    }


    public static void aplicarEstiloTabuleiro(TabuleiroJogo tabuleiro){
        tabuleiro.setLocation((TELA_LARGURA - tabuleiro.getTamanho()) / 2, (TELA_ALTURA - tabuleiro.getTamanho()) / 2);
    }

    private static JLabel configurarFundo(VisaoJogo tela){
        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/geral/borda.png");
        Image imagemFundoAtras = new ImageIcon("./assets/imgs/jogo/fundo/fundo_atras.png").getImage();
        Image imagemFundoFrente = new ImageIcon("./assets/imgs/jogo/fundo/fundo_frente.png").getImage();

        JLabel borda = new JLabel(imagemBorda);
        borda.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        borda.setOpaque(false);

        FundoAnimado fundoAtras = new FundoAnimado(imagemFundoAtras, -800, 0);
        FundoAnimado fundoFrente = new FundoAnimado(imagemFundoFrente, -800, 0);

        fundoAtras.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        fundoFrente.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);
        fundoAtras.setOpaque(false);
        fundoFrente.setOpaque(false);

        fundoFrente.add(borda);
        fundoAtras.add(fundoFrente);
        tela.add(fundoAtras);
        fundoAtras.iniciarAnimacao(70);
        fundoFrente.iniciarAnimacao(50);
        return borda;
    }

    private static JLabel bordaTabuleiro(){
        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/jogo/fundo/borda_quadrado.png");
        JLabel borda = new JLabel(imagemBorda);

        borda.setBounds((TELA_LARGURA - imagemBorda.getIconWidth()) / 2 -3, 0, imagemBorda.getIconWidth(), imagemBorda.getIconHeight());

        return borda;
    }

    private static JLabel indicadorRodadas(JLabel val){
        JLabel textoRodada = new JLabel("Rodada: ");
        textoRodada.setBounds(TELA_LARGURA/4, 22, 200, 30);
        val.setBounds(TELA_LARGURA/4 + 110, 22, 100, 30);

        textoRodada.setFont(new Font("Arial", Font.PLAIN, 26));
        val.setFont(new Font("Arial", Font.PLAIN, 26));

        textoRodada.setForeground(new Color(255, 255, 255));
        val.setForeground(new Color(255, 255, 255));
        return textoRodada;
    }

    private static void inventariosJogadores(JLabel inventario1, JLabel inventario2){
        ImageIcon imagemInventario1 = new ImageIcon("./assets/imgs/jogo/fundo/inventario1.png");
        ImageIcon imagemInventario2 = new ImageIcon("./assets/imgs/jogo/fundo/inventario2.png");

        inventario1.setIcon(imagemInventario1);
        inventario2.setIcon(imagemInventario2);

        inventario1.setBounds(40, (TELA_ALTURA - imagemInventario1.getIconHeight()) / 2, imagemInventario1.getIconWidth(), imagemInventario1.getIconHeight());

        inventario2.setBounds(TELA_LARGURA - imagemInventario2.getIconWidth()-40, (TELA_ALTURA - imagemInventario2.getIconHeight()) / 2, imagemInventario2.getIconWidth(), imagemInventario2.getIconHeight());
    }

    private static void labelsValoresInventarios (ArrayList<JLabel> inventario1, ArrayList<JLabel> inventario2, JLabel fundo){
        for (int i = 0; i < inventario1.size(); i++){
            inventario1.get(i).setBounds(150, 130 + i*62, 50, 24);
            inventario1.get(i).setFont(new Font("Arial", Font.PLAIN, 24));
            inventario1.get(i).setForeground(new Color(255, 255, 255));
            inventario2.get(i).setBounds(TELA_LARGURA-190, 130 + i*62, 50, 24);
            inventario2.get(i).setFont(new Font("Arial", Font.PLAIN, 24));
            inventario2.get(i).setForeground(new Color(117, 117, 117, 195));
            inventario2.get(i).setHorizontalAlignment(JLabel.RIGHT);
            fundo.add(inventario1.get(i));
            fundo.add(inventario2.get(i));
        }
    }
}
