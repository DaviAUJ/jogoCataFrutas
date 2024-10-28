package visao.componentes;

import utilitarios.Transmissor;
import visao.GerenciadorDeTelas;
import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class EspacoSalvamento extends JPanel {
    private int tipoTitulo;
    private boolean estaHabilitado;
    private ArrayList<Integer> valores;

    public ArrayList<JLabel> labels;
    public ImageIcon imagemFundo;
    public ImageIcon imagemGradiente;
    public ImageIcon imagemCover;

    public EspacoSalvamento(int tipoTitulo, boolean estaHabilitado, int dimensao, int qtdFrutasOuro, int qtdFrutasBichadas) {
        this.tipoTitulo = tipoTitulo;
        this.estaHabilitado = estaHabilitado;
        this.valores = new ArrayList<>();

        valores.add(dimensao);
        valores.add(qtdFrutasOuro);
        valores.add(qtdFrutasBichadas);

        EstiloComponentes.aplicarEstiloEspacoSalvamento(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemGradiente.getImage(), 55, 50, imagemGradiente.getIconWidth(), imagemGradiente.getIconHeight(), this);
        g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public int getTipoTitulo() {
        return tipoTitulo;
    }

    public boolean getEstaHabilitado() {
        return estaHabilitado;
    }

    public int getInfo(String info){
        switch (info){
            case "dimensao":{
                return valores.getFirst();
            }
            case "frutasOuro":{
                return valores.get(1);
            }
            case "frutasBichadas":{
                return valores.get(2);
            }
            default:
                return -1;
        }
    }

    public void fazerClicavel(GerenciadorDeTelas gerenciador) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Transmissor.solicitacaoNovoJogo();
                Transmissor.getJogoDoMomento().configurarListeners();
                gerenciador.adicionarVisaoJogo();
                gerenciador.irParaTela("JOGO");
            }
        });
    }
}
