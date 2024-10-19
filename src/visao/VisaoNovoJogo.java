package visao;

import visao.componentes.BarrinhaConfiguracoes;
import visao.estilos.EstiloNovoJogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    }


}
