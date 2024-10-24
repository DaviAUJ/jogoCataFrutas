package visao;

import visao.componentes.TabuleiroJogo;
import visao.estilos.EstiloVisaoJogo;

import javax.swing.*;
import java.util.HashMap;

public class VisaoJogo extends JPanel {

    public TabuleiroJogo tabuleiro;

    public VisaoJogo(GerenciadorDeTelas gerenciador){

        HashMap<String, Object> hashValidado = (HashMap<String, Object>) gerenciador.pegarInformacaoCache("infoJogo");
        int dimensao = (Integer) hashValidado.get("dimensao");
        this.tabuleiro = new TabuleiroJogo(680, dimensao);
        EstiloVisaoJogo.aplicarEstilo(this);

    }
}
