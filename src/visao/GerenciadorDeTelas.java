package visao;
import visao.estilos.Estilos;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

public class GerenciadorDeTelas {
    private JFrame framePrincipal;
    private JPanel telaPrincipal;
    private CardLayout layout;
    private HashMap<String, JPanel> telasGerenciadas;
    private String nomeTelaAtual;
    private Stack<String> caminhoAtual;

    public GerenciadorDeTelas(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
        this.telasGerenciadas = new HashMap<>();
        this.layout = new CardLayout();
        this.nomeTelaAtual = "raiz";
        this.telaPrincipal = new JPanel(layout);
        framePrincipal.setLayout(null);

        this.framePrincipal.setContentPane(telaPrincipal);
        this.framePrincipal.setResizable(false);
        this.caminhoAtual = new Stack<>();
        this.caminhoAtual.push("raiz");


    }

    public void adicionarNovaTela(String nomeTela, JPanel tela) {
        telasGerenciadas.put(nomeTela, tela);
        telaPrincipal.add(tela, nomeTela);

    }

    private boolean mostrarTela(String nomeTela) {
        if (telasGerenciadas.containsKey(nomeTela)) {
            this.nomeTelaAtual = nomeTela;
            ajustarFramePrincipal(telasGerenciadas.get(nomeTela));
            layout.show(this.telaPrincipal, nomeTela);

            return true;
        }
        System.out.println("Tela não encontrada!");
        return false;
    }

    public void irParaTela(String nomeTela) {
        if (this.mostrarTela(nomeTela)) {
            this.caminhoAtual.push(nomeTela);
            System.out.println(this.caminhoAtual);
        }
    }

    public void voltarTela() {
        if (!this.caminhoAtual.peek().equals("raiz")) {
            String nomeTelaAntigo = this.caminhoAtual.pop();
            String nomeTela = this.caminhoAtual.peek();
            if (!this.mostrarTela(nomeTela)) {
                this.caminhoAtual.push(nomeTelaAntigo);
            }
            System.out.println(this.caminhoAtual);
            return;
        }

        System.out.println("Não é possível voltar mais! Já está na raíz.");
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
