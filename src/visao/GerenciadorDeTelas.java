package visao;
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
    }

    public void adicionarNovaTela(String nomeTela, JPanel tela) {
        telasGerenciadas.put(nomeTela, tela);
        telaPrincipal.add(tela, nomeTela);
        System.out.println(telasGerenciadas.toString());
    }

    public void mostrarTela(String nomeTela) {
        if (telasGerenciadas.containsKey(nomeTela)) {
            layout.show(this.telaPrincipal, nomeTela);
            return;
        }
        System.out.println("Tela não encontrada!");
    }

    public JFrame getFramePrincipal() {
        return framePrincipal;
    }


}
