package visao;

import utilitarios.GerenciadorArquivo;
import utilitarios.Validador;
import visao.componentes.EspacoSalvamento;
import visao.estilos.EstiloVisaoCarregarJogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que representa a visão de carregar jogos salvos.
 * Permite ao usuário visualizar e carregar os jogos salvos disponíveis.
 */
public class VisaoCarregarJogo extends JPanel {

    private ArrayList<GerenciadorArquivo> listaDeArquivosSaves;

    public ArrayList<EspacoSalvamento> saves;
    public JButton btnVoltar;


    /**
     * Construtor da classe VisaoCarregarJogo.
     * Inicializa a visão com os arquivos de salvamento disponíveis.
     *
     * @param gerenciador O gerenciador de telas que controla a navegação.
     */

    public VisaoCarregarJogo(GerenciadorDeTelas gerenciador) {

        this.saves = new ArrayList<>();

        this.listaDeArquivosSaves = gerenciador.solicitarSalvamentos();

        int posI = 0;
        for (GerenciadorArquivo arquivo : listaDeArquivosSaves) {
            if(!(arquivo == null)) {
                HashMap<String, Object> hashValidavel = arquivo.constroiHashValidavel();

                Validador validador = new Validador();
                String resposta = validador.validarInformacoes(hashValidavel);

                if (resposta.equals("Validado")) {
                    saves.add(new EspacoSalvamento(posI+1, true, arquivo.pegarDimensao(),
                            arquivo.pegarEspacoMochila(), arquivo.pegarChanceBichadas()));

                    gerenciador.addNoCache("infoJogo", hashValidavel);
                    saves.get(posI).fazerClicavel(gerenciador);
                }
                else{
                    saves.add(new EspacoSalvamento(posI+1, false, 0, 0, 0));
                    gerenciador.gerarAvisoErro("Sobre a configuração " + (posI + 1) +": " + resposta);
                }
            }
            else {
                saves.add(new EspacoSalvamento(posI+1, false, 0, 0, 0));
            }

            posI++;
        }

        for (int c = posI; c < 4; c++){
            saves.add(new EspacoSalvamento(c+1, false, 0, 0, 0));
        }

        EstiloVisaoCarregarJogo.aplicarEstilo(this);

        this.btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciador.voltarTela();
            }
        });
    }

    /**
     * Retorna a lista de arquivos de salvamento disponíveis.
     *
     * @return Um ArrayList com os arquivos de salvamento.
     */
    public ArrayList<GerenciadorArquivo> getListaDeArquivos() {
        return this.listaDeArquivosSaves;
    }
}
