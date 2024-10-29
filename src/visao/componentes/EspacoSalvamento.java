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

/**
 * Classe que representa um espaço de salvamento na interface do usuário.
 * Esta classe estende JPanel e permite a visualização e interação com dados de salvamento.
 */
public class EspacoSalvamento extends JPanel {
    private int tipoTitulo;
    private boolean estaHabilitado;
    private ArrayList<Integer> valores;

    public ArrayList<JLabel> labels;
    public ImageIcon imagemFundo;
    public ImageIcon imagemGradiente;
    public ImageIcon imagemCover;

    /**
     * Construtor da classe EspacoSalvamento.
     *
     * @param tipoTitulo O tipo de título do espaço de salvamento.
     * @param estaHabilitado Indica se o espaço de salvamento está habilitado.
     * @param dimensao Dimensão do espaço de salvamento.
     * @param qtdFrutasOuro Quantidade de frutas ouro.
     * @param qtdFrutasBichadas Quantidade de frutas bichadas.
     */
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

    /**
     * Obtém o tipo de título do espaço de salvamento.
     *
     * @return O tipo de título.
     */
    public int getTipoTitulo() {
        return tipoTitulo;
    }

    /**
     * Verifica se o espaço de salvamento está habilitado.
     *
     * @return true se o espaço estiver habilitado, false caso contrário.
     */
    public boolean getEstaHabilitado() {
        return estaHabilitado;
    }

    /**
     * Obtém informações do espaço de salvamento com base na chave fornecida.
     *
     * @param info A chave da informação desejada ("dimensao", "frutasOuro" ou "frutasBichadas").
     * @return O valor associado à chave ou -1 se a chave for inválida.
     */
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

    /**
     * Torna o espaço de salvamento clicável, permitindo que o usuário inicie um novo jogo ao clicar.
     *
     * @param gerenciador O gerenciador de telas que controla a navegação entre telas.
     */
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
