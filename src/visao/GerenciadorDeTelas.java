package visao;
import visao.estilos.Estilos;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GerenciadorDeTelas {
    private JFrame framePrincipal;
    private JPanel telaPrincipal;
    private CardLayout layout;
    private HashMap<String, JPanel> telasGerenciadas;
    private String nomeTelaAtual;

    public GerenciadorDeTelas(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
        this.telasGerenciadas = new HashMap<>();
        this.layout = new CardLayout();
        this.nomeTelaAtual = "";
        this.telaPrincipal = new JPanel(layout);

        this.framePrincipal.setContentPane(telaPrincipal);
        this.framePrincipal.setResizable(false);


    }

    public void adicionarNovaTela(String nomeTela, JPanel tela) {
        telasGerenciadas.put(nomeTela, tela);
        telaPrincipal.add(tela, nomeTela);

    }

    public void mostrarTela(String nomeTela) {
        if (telasGerenciadas.containsKey(nomeTela)) {
            this.nomeTelaAtual = nomeTela;
            ajustarFramePrincipal(telasGerenciadas.get(nomeTela));
            layout.show(this.telaPrincipal, nomeTela);

            return;
        }
        System.out.println("Tela não encontrada!");
    }

    private void ajustarFramePrincipal(JPanel tela) {
        Estilos.visaoPrincipal((VisaoPrincipal) this.framePrincipal);

    }

    public JFrame getFramePrincipal() {
        return framePrincipal;
    }

    public String getNomeTelaAtual() {
        return nomeTelaAtual;
    }


}
