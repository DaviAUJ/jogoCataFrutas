package visao;

import utilitarios.Transmissor;
import utilitarios.Validador;
import visao.componentes.BarrinhaConfiguracoes;
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
                gerenciador.irParaTela("JOGO");



            }
        });
    }


}
