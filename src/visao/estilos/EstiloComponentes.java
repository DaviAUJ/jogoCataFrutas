package visao.estilos;

import visao.componentes.BarrinhaConfiguracoes;
import visao.componentes.EspacoSalvamento;
import visao.componentes.QuadradinhoTabuleiro;
import visao.componentes.TabuleiroJogo;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;


public abstract class EstiloComponentes {
    private static int BARRINHA_LARGURA = 506;
    private static int BARRINHA_ALTURA = 33;

    private static ImageIcon imagemConfig1 = new ImageIcon("./assets/imgs/carregarJogo/config1.png");
    private static ImageIcon imagemConfig2 = new ImageIcon("./assets/imgs/carregarJogo/config2.png");
    private static ImageIcon imagemConfig3 = new ImageIcon("./assets/imgs/carregarJogo/config3.png");
    private static ImageIcon imagemConfig4 = new ImageIcon("./assets/imgs/carregarJogo/config4.png");
    private static ImageIcon imagemBordaSave = new ImageIcon("./assets/imgs/carregarJogo/bordaSave.png");
    private static ImageIcon imagemGradiente = new ImageIcon("./assets/imgs/carregarJogo/gradiente.png");

    private static ImageIcon imagemLabelDimensao = new ImageIcon("./assets/imgs/carregarJogo/labelDimensao.png");
    private static ImageIcon imagemLabelFrutasOuro = new ImageIcon("./assets/imgs/carregarJogo/frutasOuro.png");
    private static ImageIcon imagemLabelFrutasBichadas = new ImageIcon("./assets/imgs/carregarJogo/frutasBichadas.png");
    private static ImageIcon imagemCoverEspacoSalvamento = new ImageIcon("./assets/imgs/carregarJogo/cover.png");

    public static void aplicarEstiloBarrinhaConfiguracoes(BarrinhaConfiguracoes barrinha, ImageIcon textoBarrinha){
        barrinha.setLayout(null);
        barrinha.setPreferredSize(new Dimension(BARRINHA_LARGURA, BARRINHA_ALTURA));

        NumberFormat formato = NumberFormat.getInstance();
        NumberFormatter formatador = new NumberFormatter(formato);
        formatador.setValueClass(Integer.class);
        formatador.setMinimum(0);
        formatador.setAllowsInvalid(false);
        formatador.setCommitsOnValidEdit(true);

        barrinha.texto = new JLabel();
        barrinha.valor = new JFormattedTextField(formato);

        barrinha.texto.setBounds(15, 0, BARRINHA_LARGURA-56, BARRINHA_ALTURA);
        barrinha.texto.setIcon(textoBarrinha);

        barrinha.valor.setBounds(BARRINHA_LARGURA-56, 0, 56, BARRINHA_ALTURA);
        barrinha.texto.setOpaque(false);
        barrinha.valor.setOpaque(false);
        barrinha.valor.setFont(new Font("Arial", Font.PLAIN, 20));


        barrinha.valor.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 110)));
        barrinha.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 255)));


        barrinha.valor.setHorizontalAlignment(JTextField.CENTER);

        barrinha.setBackground(new Color(25, 0, 0, 100));

        barrinha.setMaximumSize(new Dimension(Integer.MAX_VALUE, barrinha.getPreferredSize().height));

        barrinha.add(barrinha.texto);
        barrinha.add(barrinha.valor);


    }

    public static void aplicarEstiloBarrinhaConfiguracoes2(BarrinhaConfiguracoes barrinha, ImageIcon textoBarrinha){
        barrinha.setLayout(null);




        barrinha.setPreferredSize(new Dimension(BARRINHA_LARGURA, BARRINHA_ALTURA));


        NumberFormat formato = NumberFormat.getInstance();
        NumberFormatter formatador = new NumberFormatter(formato);
        formatador.setValueClass(Integer.class);
        formatador.setMinimum(0);
        formatador.setAllowsInvalid(false);

        barrinha.texto = new JLabel();
        barrinha.valor = new JFormattedTextField(formato);
        barrinha.valor2 = new JFormattedTextField(formato);

        barrinha.texto.setBounds(15, 0, BARRINHA_LARGURA-56*2, BARRINHA_ALTURA);
        barrinha.valor.setBounds(BARRINHA_LARGURA-56*2, 0, 56, BARRINHA_ALTURA);
        barrinha.valor2.setBounds(BARRINHA_LARGURA-56, 0, 56, BARRINHA_ALTURA);
        barrinha.valor.setOpaque(false);
        barrinha.valor2.setOpaque(false);
        barrinha.valor.setFont(new Font("Arial", Font.PLAIN, 20));
        barrinha.valor2.setFont(new Font("Arial", Font.PLAIN, 20));


        barrinha.texto.setIcon(textoBarrinha);
        barrinha.valor.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 126)));
        barrinha.valor2.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 110)));
        barrinha.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 255)));

        barrinha.valor.setHorizontalAlignment(JTextField.CENTER);
        barrinha.valor2.setHorizontalAlignment(JTextField.CENTER);

        barrinha.setBackground(new Color(25, 0, 0, 100));
        barrinha.setMaximumSize(new Dimension(Integer.MAX_VALUE, barrinha.getPreferredSize().height));

        barrinha.add(barrinha.texto);
        barrinha.add(barrinha.valor);
        barrinha.add(barrinha.valor2);


    }


    public static void aplicarEstiloTabuleiro(TabuleiroJogo tabuleiro){
        tabuleiro.setLayout(null);
        tabuleiro.setBounds(0, 0, tabuleiro.TAMANHO, tabuleiro.TAMANHO);
        tabuleiro.setBackground(Color.GREEN);

    }

    public static void aplicarEstiloQuadradinhoTabuleiro(QuadradinhoTabuleiro quadradinho){

    }

    public static void aplicarEstiloEspacoSalvamento(EspacoSalvamento espacoSalvamento){
        int SAVE_LARGURA = imagemBordaSave.getIconWidth();
        int SAVE_ALTURA = imagemBordaSave.getIconHeight();


        espacoSalvamento.setSize(SAVE_LARGURA, SAVE_ALTURA);
        espacoSalvamento.setLayout(null);
        espacoSalvamento.imagemFundo = imagemBordaSave;
        espacoSalvamento.imagemGradiente = imagemGradiente;
        espacoSalvamento.setOpaque(false);

        if (!espacoSalvamento.getEstaHabilitado()){
            JLabel cover = new JLabel(imagemCoverEspacoSalvamento);
            cover.setBounds(1, -2, SAVE_LARGURA, SAVE_ALTURA);
            espacoSalvamento.add(cover);
        }


        ImageIcon imagemTextoTitulo;
        JLabel labelTextoTitulo = new JLabel();


        switch (espacoSalvamento.getTipoTitulo()){
            case 2:{
                labelTextoTitulo.setIcon(imagemConfig2);
                imagemTextoTitulo = imagemConfig1;
                break;
            }

            case 3:{
                labelTextoTitulo.setIcon(imagemConfig3);
                imagemTextoTitulo = imagemConfig4;
                break;
            }

            case 4:{
                labelTextoTitulo.setIcon(imagemConfig4);
                imagemTextoTitulo = imagemConfig4;
                break;
            }

            default:{
                labelTextoTitulo.setIcon(imagemConfig1);
                imagemTextoTitulo = imagemConfig1;
                break;
            }
        }

        labelTextoTitulo.setBounds(40, 30, imagemTextoTitulo.getIconWidth(), imagemTextoTitulo.getIconHeight());

        ArrayList<JLabel> labelsDeEnfeite = new ArrayList<>();
        ArrayList<JLabel> labelsImportantes = new ArrayList<>();

        //x = 70, y = 90, margem = 20
        labelsDeEnfeite.add(new JLabel(imagemLabelDimensao));
        labelsImportantes.add(new JLabel(Integer.toString(espacoSalvamento.getEstaHabilitado() ? espacoSalvamento.getInfo("dimensao") : 0)));

        labelsDeEnfeite.add(new JLabel(imagemLabelFrutasOuro));
        labelsImportantes.add(new JLabel(Integer.toString(espacoSalvamento.getEstaHabilitado() ? espacoSalvamento.getInfo("frutasOuro") : 0)));

        labelsDeEnfeite.add(new JLabel(imagemLabelFrutasBichadas));
        labelsImportantes.add(new JLabel(Integer.toString(espacoSalvamento.getEstaHabilitado() ? espacoSalvamento.getInfo("frutasBichadas") : 0)));

        int empurraPraBaixo = 0;
        int margem = 0;

        for (int c = 0; c < labelsDeEnfeite.size(); c++){
            JLabel labelDeEnfeiteAtual = labelsDeEnfeite.get(c);
            JLabel labelImportanteAtual = labelsImportantes.get(c);
            ImageIcon imagemEnfeite = (ImageIcon) labelDeEnfeiteAtual.getIcon();


            labelDeEnfeiteAtual.setBounds(70, 90 + empurraPraBaixo + margem, imagemEnfeite.getIconWidth(), imagemEnfeite.getIconHeight());

            labelImportanteAtual.setBounds(70 + 355, 90 + empurraPraBaixo + margem, 50, imagemEnfeite.getIconHeight());
            labelImportanteAtual.setFont(new Font("Arial", Font.PLAIN, 30));
            labelImportanteAtual.setForeground(new Color(255, 255, 255));

            espacoSalvamento.add(labelDeEnfeiteAtual);
            espacoSalvamento.add(labelImportanteAtual);

            empurraPraBaixo += imagemEnfeite.getIconHeight();
            margem += 15;
        }

        espacoSalvamento.labels = labelsImportantes;













        espacoSalvamento.add(labelTextoTitulo);



    }
}
