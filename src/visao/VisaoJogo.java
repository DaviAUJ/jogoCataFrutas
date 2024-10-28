package visao;

import frutas.*;
import jogoCataFrutas.Configuracoes;
import sons.EventoSonoroHandler;
import utilitarios.Transmissor;
import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloVisaoJogo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VisaoJogo extends JPanel {

    public TabuleiroJogo tabuleiro;
    public JLabel rodada;

    public ArrayList <JLabel> valoresInventario1;
    public ArrayList <JLabel> valoresInventario2;

    public JLabel inventario1;
    public JLabel inventario2;

    public JLabel textoFimDeJogo;


    //Quando lançar o evento de fim de jogo, o jogo lá deve desabilitar os eventos, para que nada novo aconteça. Além disso
    //O escutador daqui deve fazer this.setComponentZOrder(this.textoFimDeJogo, 0);

    public JButton btnPassarRodada;

    public VisaoJogo(GerenciadorDeTelas gerenciador){

        int dimensao = Configuracoes.dimensao;
        Configuracoes.tipo = "real";
        this.tabuleiro = new TabuleiroJogo(600);
        this.rodada = new JLabel("1");
        this.valoresInventario1 = new ArrayList<>(8);
        this.valoresInventario2 = new ArrayList<>(8);
        this.inventario1 = new JLabel();
        this.inventario2 = new JLabel();

        for (int i = 0; i < 8; i++){
            valoresInventario1.add(new JLabel("0"));
            valoresInventario2.add(new JLabel("0"));
        }
        EstiloVisaoJogo.aplicarEstilo(this);

        configurarListerners();
        Transmissor.iniciarPartida();
        EventoSonoroHandler.musicaExcecao();

        btnPassarRodada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transmissor.pedirPassarRodada();
            }
        });
    }

    public void trocarInventarios(int idJogador){
        if (idJogador == 2){
            EstiloVisaoJogo.inverterJogadores(inventario1, valoresInventario1, inventario2, valoresInventario2);
        }
        else{
            EstiloVisaoJogo.inverterJogadores(inventario2, valoresInventario2, inventario1, valoresInventario1);
        }
    }

    private void configurarListerners() {
        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("avisoMudouMochila")) {
                    Class<? extends Fruta> fruta = (Class<? extends Fruta>) ((HashMap<String, Object>)evt.getNewValue()).get("fruta");
                    int num = (int) ((HashMap<String, Object>)evt.getNewValue()).get("quantidade");
                    int idJogador = (int) ((HashMap<String, Object>)evt.getNewValue()).get("jogador");
                    List<JLabel> inventario = valoresInventario2;

                    System.out.println("teste");

                    if(idJogador == 1) {
                        inventario = valoresInventario1;
                    }

                    if(fruta == Maracuja.class) {
                        inventario.get(1).setText("" + num);
                    }
                    else if(fruta == Laranja.class) {
                        inventario.get(2).setText("" + num);
                    }
                    else if(fruta == Generica.class) {
                        inventario.get(3).setText("" + num);
                    }
                    else if(fruta == Coco.class) {
                        inventario.get(4).setText("" + num);
                    }
                    else if(fruta == Abacate.class) {
                        inventario.get(5).setText("" + num);
                    }
                }
            }
        });

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("avisoNovaRodada")) {
                    rodada.setText("" + (int) evt.getNewValue());

                }
            }
        });

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("avisoPontosAlterados")) {
                    JLabel label = valoresInventario1.getFirst();

                    if(((HashMap<String, Integer>) evt.getNewValue()).get("id") == 2) {
                        label = valoresInventario2.getFirst();
                    }

                    label.setText("" + (int) ((HashMap<String, Integer>) evt.getNewValue()).get("pontos"));
                }
            }
        });

        Transmissor.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("avisoTrocaJogador")) {
                    trocarInventarios((Integer) evt.getNewValue());
                }
            }
        });
    }
}
