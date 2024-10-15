package visao.estilos;
import visao.VisaoNovoJogo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class EstiloVisaoNovoJogo {

    private static ImageIcon IMAGEM = new ImageIcon("./assets/imgs/inicio-background.jpg");

    public static void aplicarEstilo(VisaoNovoJogo tela) {
        tela.setLayout(new BorderLayout());
        tela.setBounds(0, 0, 1280, 720);
        tela.setBackground(Color.YELLOW);

        tela.painelEsquerda = new JPanel();
        tela.painelDireita = new JPanel();
        tela.imgGrandeEsquerda = new JLabel(IMAGEM);
        tela.labelsTexto = new HashMap<>();

        painelEsquerda(tela.painelEsquerda);
        painelDireita(tela.painelDireita);
        imagemFundoPainelEsquerda(tela.imgGrandeEsquerda);

        labelsFrutas(tela.labelsTexto);


        for (String chave : tela.labelsTexto.keySet()){
            tela.painelDireita.add(tela.labelsTexto.get(chave));
        }

        tela.painelEsquerda.add(tela.imgGrandeEsquerda);
        tela.add(tela.painelEsquerda, BorderLayout.WEST);
        tela.add(tela.painelDireita, BorderLayout.EAST);




    }

    private static void painelEsquerda (JPanel painel) {
        painel.setPreferredSize(new Dimension(450, 720));
        painel.setBackground(Color.WHITE);
        painel.setLayout(null);
    }

    private static void painelDireita(JPanel painel) {
        painel.setPreferredSize(new Dimension(830, 720));
        painel.setBackground(new Color(37, 37, 37));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(new EmptyBorder(10, 40, 0, 0));
    }

    private static void imagemFundoPainelEsquerda(JLabel fundo) {
        fundo.setBounds(0, 0, 450, 720);
        fundo.setIcon(new ImageIcon(IMAGEM.getImage().getScaledInstance(450, 720, Image.SCALE_AREA_AVERAGING)));
    }

    private static void labelsFrutas(HashMap <String, JLabel> labels) {
        labels.put("nomeJogador1", new JLabel("Nome do Jogador 1:"));
        labels.put("nomeJogador2", new JLabel("Nome do Jogador 2:"));
        labels.put("dimensoes", new JLabel("Dimensão:"));
        labels.put("qtdPedras", new JLabel("Quantidade de pedras:"));
        labels.put("qtdMaracujas", new JLabel("Quantidade de maracujás:"));
        labels.put("qtdLaranjas", new JLabel("Quantidade de laranjas:"));
        labels.put("qtdAbacates", new JLabel("Quantidade de abacates:"));
        labels.put("qtdCocos", new JLabel("Quantidade de cocos:"));
        labels.put("qtdAcerolas", new JLabel("Quantidade de acerolas:"));
        labels.put("qtdAmoras", new JLabel("Quantidade de amoras:"));
        labels.put("qtdGoiabas", new JLabel("Quantidade de goiabas:"));
        labels.put("chanceBichadas", new JLabel("Chance de frutas bichadas:"));
        labels.put("espacoMochilas", new JLabel("Espaço das mochilas:"));

        for (String chave : labels.keySet()){
            labels.get(chave).setFont(new Font("Arial", Font.PLAIN, 22));
        }





    }
}
