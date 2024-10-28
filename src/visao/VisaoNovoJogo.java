package visao;

import utilitarios.Transmissor;
import utilitarios.Validador;
import visao.componentes.BarrinhaConfiguracoes;
import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloNovoJogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class VisaoNovoJogo extends JPanel {

    public JLabel tituloTela;
    public JScrollPane scrollConfigs;
    public JPanel conteudoRolavel;
    public JPanel preview;
    public JButton botaoPreview;
    public JButton botaoOk;
    public JButton botaoVoltar;

    public ArrayList <JFormattedTextField> camposDeEntrada;

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
                    preview.removeAll();
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
                TabuleiroJogo tabuleiroJogo = new TabuleiroJogo(preview.getHeight() - 60, "preview");
                tabuleiroJogo.setLocation(2+(preview.getWidth() - tabuleiroJogo.getWidth()) / 2, (preview.getHeight() - tabuleiroJogo.getHeight()) / 2);
                preview.add(tabuleiroJogo);
                System.gc();





            }
        });


    }


}
