package visao;

import visao.estilos.EstiloVisaoNovoJogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class VisaoNovoJogo extends JPanel{
    private GerenciadorDeTelas gerenciador;
    public JPanel painelEsquerda;
    public JPanel painelDireita;
    public JLabel imgGrandeEsquerda;


    public HashMap <String, JLabel> labelsTexto;
    public HashMap <String, JTextField> qtdArvores;
    public JTextField totalMaracujas;
    public JTextField maracujasNoChao;
    public JTextField chanceBichadas;
    public JTextField espacoMochila;
    public JButton salvarEComecar;
    public JButton voltar;

    public VisaoNovoJogo(GerenciadorDeTelas gerenciadorDeTelas) {
        this.gerenciador = gerenciadorDeTelas;
        EstiloVisaoNovoJogo.aplicarEstilo(this);

    }

    public GerenciadorDeTelas getGerenciadorDeTelas() {
        return this.gerenciador;
    }


}

/*

    Nome do primeiro jogador: [            ]
    Nome do segundo jogador:  [            ]
    Dimensões do jogo: [  ]
    Quantidade de pedras: [  ]
    Quantidade de maracujas (total / no chão): 3 1
    Quantidade de laranjas (árvores / no chão): 2 1
    Quantidade de abacates (árvores / no chão):  2 3
    Quantidade de cocos (árvores / no chão): 2 1
    Quantidade de acerolas (árvores / no chão): 1 2
    Quantidade de amoras (árvores / no chão): 1 1
    Quantidade de goiabas (árvores / no chão): 1 1
    Chance de frutas bichadas: 25[%]
    Espaço da mochila: 10









 */
