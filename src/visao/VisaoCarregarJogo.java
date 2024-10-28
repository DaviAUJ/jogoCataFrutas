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

public class VisaoCarregarJogo extends JPanel {

    private ArrayList<GerenciadorArquivo> listaDeArquivosSaves;

    public ArrayList<EspacoSalvamento> saves;
    public JButton btnVoltar;




    public VisaoCarregarJogo(GerenciadorDeTelas gerenciador) {

        this.saves = new ArrayList<>();
        this.listaDeArquivosSaves = new ArrayList<>(4);

        this.listaDeArquivosSaves = gerenciador.solicitarSalvamentos();

        int posI = 0;
        for (GerenciadorArquivo arquivo : listaDeArquivosSaves) {

            HashMap<String, Object> hashValidavel = arquivo.constroiHashValidavel();

            Validador validador = new Validador();
            String resposta = validador.validarInformacoes(hashValidavel);

            if (resposta.equals("Validado")) {
                saves.add(new EspacoSalvamento(posI+1, true, arquivo.pegarDimensao(),
                        arquivo.pegarEspacoMochila(), arquivo.pegarChanceBichadas()));
            }
            else{
                saves.add(new EspacoSalvamento(posI+1, false, 0, 0, 0));
                gerenciador.gerarAvisoErro("Sobre a configuração " + (posI + 1) +": " + resposta);
            }


            posI++;
            System.out.println(posI);

            if (posI == 4) {
                break;
            }
        }
        if (posI <= 3){
            for (int c = posI; c < 4; c++){
                saves.add(new EspacoSalvamento(c+1, false, 0, 0, 0));
            }
        }

        EstiloVisaoCarregarJogo.aplicarEstilo(this);


        this.btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciador.voltarTela();
            }
        });
    }

    public ArrayList<GerenciadorArquivo> getListaDeArquivos() {
        return this.listaDeArquivosSaves;
    }
}
