package visao;

import frutas.*;
import jogoCataFrutas.Configuracoes;
import utilitarios.Transmissor;
import sons.EfeitoSonoro;
import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloVisaoJogo;
import javax.swing.*;
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

    public VisaoJogo(GerenciadorDeTelas gerenciador){

        int dimensao = Configuracoes.dimensao;
        this.tabuleiro = new TabuleiroJogo(600);
        this.rodada = new JLabel("1");
        this.valoresInventario1 = new ArrayList<>(8);
        this.valoresInventario2 = new ArrayList<>(8);

        for (int i = 0; i < 8; i++){
            valoresInventario1.add(new JLabel("0"));
            valoresInventario2.add(new JLabel("0"));
        }
        EstiloVisaoJogo.aplicarEstilo(this);

        configurarListerners();
        Transmissor.iniciarPartida();
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
    }
}
