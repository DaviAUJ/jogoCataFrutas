package visao.estilos;

import visao.componentes.BarrinhaConfiguracoes;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public abstract class EstiloComponentes {
    private static int BARRINHA_LARGURA = 506;
    private static int BARRINHA_ALTURA = 33;

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
        System.out.println(barrinha.texto.getIcon().getIconWidth());
        barrinha.valor.setBounds(BARRINHA_LARGURA-56, 0, 56, BARRINHA_ALTURA);
        barrinha.texto.setOpaque(false);
        barrinha.valor.setOpaque(false);


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
}
