package visao;

import utilitarios.Transmissor;
import utilitarios.Validador;
import visao.componentes.BarrinhaConfiguracoes;
import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloComponentes;
import visao.estilos.EstiloNovoJogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que representa a visão para configuração de um novo jogo.
 * Permite aos jogadores ajustar as configurações e visualizar um preview do tabuleiro.
 */
public class VisaoNovoJogo extends JPanel {

    public JLabel tituloTela;
    public JScrollPane scrollConfigs;
    public JPanel conteudoRolavel;
    public JPanel preview;
    public JButton botaoPreview;
    public JButton botaoOk;
    public JButton botaoVoltar;

    public ArrayList <JFormattedTextField> camposDeEntrada;

    /**
     * Construtor da classe VisaoNovoJogo.
     * Inicializa os componentes da visão e configura os listeners para os botões.
     *
     * @param gerenciador O gerenciador de telas que controla a navegação entre as diferentes visões.
     */
    public VisaoNovoJogo(GerenciadorDeTelas gerenciador) {
        EstiloNovoJogo.aplicarEstilo(this);

        this.botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciador.voltarTela();
            }
        });

        botaoOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (gerenciador.pegarInformacaoCache("infoJogo") != null) {
                    Transmissor.getJogoDoMomento().configurarListeners();
                    gerenciador.adicionarVisaoJogo();
                    gerenciador.irParaTela("JOGO");
                }
            }
        });

        botaoPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preview.removeAll();
                HashMap<String, Object> hashValidavel = gerenciador.constroiHashValidavel(
                        ((JTextField) gerenciador.pegarInformacaoCache("nomeJogador1")),
                        ((JTextField) gerenciador.pegarInformacaoCache("nomeJogador2")),
                        camposDeEntrada);

                Validador validador = new Validador();
                String resposta = validador.validarInformacoes(hashValidavel);
                if (!resposta.equals("Validado")) {
                    gerenciador.gerarAvisoErro(resposta);
                    return;
                }

                gerenciador.addNoCache("infoJogo", hashValidavel);
                gerenciador.solicitarNovoJogo();
                TabuleiroJogo tabuleiroJogo = new TabuleiroJogo(preview.getHeight() - 60);
                tabuleiroJogo.setLocation((preview.getWidth() - tabuleiroJogo.getWidth()) / 2, (preview.getHeight() - tabuleiroJogo.getHeight()) / 2);
                preview.add(tabuleiroJogo);
                preview.revalidate(); //Isso aqui é gambiarra professora :D






            }
        });


    }





}
