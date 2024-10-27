package visao;
import jogoCataFrutas.Jogo;
import sons.EfeitoSonoro;
import utilitarios.GerenciadorArquivo;
import utilitarios.ReagirMudanca;
import utilitarios.Transmissor;
import visao.estilos.Estilos;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class GerenciadorDeTelas {
    private JFrame framePrincipal;
    private JPanel telaPrincipal;
    private CardLayout layout;
    private HashMap<String, JPanel> telasGerenciadas;
    private String nomeTelaAtual;
    private Stack<String> caminhoAtual;
    private EfeitoSonoro musica;

    private HashMap <String, Object> CACHE;

    public GerenciadorDeTelas(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
        this.telasGerenciadas = new HashMap<>();
        this.layout = new CardLayout();
        this.nomeTelaAtual = "raiz";
        this.telaPrincipal = new JPanel(layout);
        this.CACHE = new HashMap<>();
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
            escolherMusica(nomeTela);

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

    public void addNoCache(String chave, Object info){
        this.CACHE.put(chave, info);
    }

    public Object pegarInformacaoCache(String chave){
        return this.CACHE.get(chave);
    }

    public HashMap<String, Object> constroiHashValidavel(JTextField nomeJogador1,
                                                         JTextField nomeJogador2,
                                                         ArrayList <JFormattedTextField> camposDeEntrada
                                                         ) {

        HashMap <String, Object> hash = new HashMap<>();

        hash.put("nomeJogador1", nomeJogador1.getText());
        hash.put("nomeJogador2", nomeJogador2.getText());
        hash.put("dimensao", Integer.parseInt(Objects.equals(camposDeEntrada.getFirst().getText(), "") ? "0" : camposDeEntrada.getFirst().getText()));
        hash.put("totalMaracujas", Integer.parseInt(Objects.equals(camposDeEntrada.get(2).getText(), "") ? "0" : camposDeEntrada.get(2).getText()));
        hash.put("espacoMochila", Integer.parseInt(Objects.equals(camposDeEntrada.get(3).getText(), "") ? "0" : camposDeEntrada.get(3).getText()));

        hash.put("chanceBichadas", Integer.parseInt(Objects.equals(camposDeEntrada.get(4).getText(), "") ? "0" : camposDeEntrada.get(4).getText()));
        hash.put("quantPedras", Integer.parseInt(Objects.equals(camposDeEntrada.get(5).getText(), "") ? "0" : camposDeEntrada.get(5).getText()));


        ArrayList <Integer> qtdTipoArvores = new ArrayList <>(6);
        ArrayList <Integer> qtdFrutasChao = new ArrayList <>(7);


        qtdFrutasChao.add(Integer.parseInt(Objects.equals(camposDeEntrada.get(1).getText(), "") ? "0" : camposDeEntrada.get(1).getText()));
        for (int i = 6; i < camposDeEntrada.size(); i+=2){
            qtdTipoArvores.add(Integer.parseInt(Objects.equals(camposDeEntrada.get(i).getText(), "") ? "0" : camposDeEntrada.get(i).getText()));
            qtdFrutasChao.add(Integer.parseInt(Objects.equals(camposDeEntrada.get(i+1).getText(), "") ? "0" : camposDeEntrada.get(i+1).getText()));
        }

        hash.put("qtdTipoArvores", qtdTipoArvores);
        hash.put("qtdFrutasChao", qtdFrutasChao);

        System.out.println("Arvores: " + qtdTipoArvores);
        System.out.println("Chao: " +qtdFrutasChao);

        return hash;
    }

    public void gerarAvisoErro(String mensagem){
        JOptionPane.showMessageDialog(framePrincipal, mensagem, "Altere as informações", JOptionPane.ERROR_MESSAGE);
    }

    public void solicitarNovoJogo(){
        Transmissor.solicitacaoNovoJogo();
        adicionarVisaoJogo(Transmissor.getJogoDoMomento());
    }

    public void adicionarVisaoJogo(Jogo jogo){
        if (!this.telasGerenciadas.containsKey("JOGO")){
            this.adicionarNovaTela("JOGO", new VisaoJogo(this));
        }
        else{
            this.telasGerenciadas.put("JOGO", new VisaoJogo(this));
        }
    }
    
    public ArrayList<GerenciadorArquivo> solicitarSalvamentos (){
        Transmissor.buscarSalvamentos();
        return (ArrayList<GerenciadorArquivo>) Transmissor.getDados("salvamentos");
    }


    public void escolherMusica(String nomeTela) {
        try {
            if(nomeTela.equals("INICIO")) {
                try {
                    musica.parar();
                }
                catch (Exception _) {  }

                musica = new EfeitoSonoro(EfeitoSonoro.pasta + "Abstração.wav", 0.5f);
                musica.tocar();
            }
            else if(nomeTela.equals("JOGO")) {
                try {
                    musica.parar();
                }
                catch (Exception _) {  }

                musica = new EfeitoSonoro(EfeitoSonoro.pasta + "Exceção.wav", 0.5f);
                musica.tocar();
            }
        }
        catch(Exception e) {
            System.out.println(e + "");
        }
    }
}
