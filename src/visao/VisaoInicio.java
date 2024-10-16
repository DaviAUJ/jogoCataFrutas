package visao;


import javax.swing.*;


import utilitarios.ReagirMudanca;
import visao.estilos.EstiloVisaoInicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class VisaoInicio extends JPanel {
    public JLabel tituloJogo;
    public JButton iniciarJogo;
    public int tituloJogoAnimacao;
    private int teste;
    public ReagirMudanca<VisaoInicio> reagirMudanca;


    public VisaoInicio(GerenciadorDeTelas gerenciador) {

        ReagirMudanca<VisaoInicio> reacao = new ReagirMudanca<>(this);
        this.teste = 0;
        this.reagirMudanca = reacao;
        reacao.adicionarListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String nomePropriedade = evt.getPropertyName();
                Object valorAntigo = evt.getOldValue();
                Object novoValor = evt.getNewValue();

                System.out.println("Propriedade '" + nomePropriedade + "' mudou de " + valorAntigo + " para " + novoValor);

                // Simulando um "efeito" que reage à mudança

            }
        });

        EstiloVisaoInicio.aplicarEstilo(this);

        this.iniciarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reagirMudanca.setPropriedade("teste", teste+1);
            }
        });



    }

    public int getTeste() {
        return teste;
    }

    public void setTeste(int val){
        this.teste = val;
    }
}
