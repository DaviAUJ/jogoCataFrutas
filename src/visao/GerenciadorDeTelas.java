package visao;
import jogoCataFrutas.Configuracoes;
import jogoCataFrutas.Jogo;
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

/**
 * Classe responsável por gerenciar as telas da aplicação.
 * Permite a navegação entre diferentes telas e armazena informações em cache.
 */
public class GerenciadorDeTelas {
    private JFrame framePrincipal;
    private JPanel telaPrincipal;
    private CardLayout layout;
    private HashMap<String, JPanel> telasGerenciadas;
    private String nomeTelaAtual;
    private Stack<String> caminhoAtual;

    private HashMap <String, Object> CACHE;

    /**
     * Construtor da classe GerenciadorDeTelas.
     * Inicializa a janela principal e o layout das telas.
     *
     * @param framePrincipal A janela principal da aplicação.
     */
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

    /**
     * Adiciona uma nova tela ao gerenciador.
     *
     * @param nomeTela O nome identificador da tela.
     * @param tela     O painel que representa a tela.
     */
    public void adicionarNovaTela(String nomeTela, JPanel tela) {
        telasGerenciadas.put(nomeTela, tela);
        telaPrincipal.add(tela, nomeTela);

    }

    /**
     * Mostra uma tela específica.
     *
     * @param nomeTela O nome da tela a ser mostrada.
     * @return true se a tela foi mostrada com sucesso; false caso contrário.
     */
    private boolean mostrarTela(String nomeTela) {
        if (telasGerenciadas.containsKey(nomeTela)) {
            this.nomeTelaAtual = nomeTela;
            ajustarFramePrincipal(telasGerenciadas.get(nomeTela));
            layout.show(this.telaPrincipal, nomeTela);

            return true;
        }
        return false;
    }


    /**
     * Navega para uma tela específica, armazenando o caminho atual.
     *
     * @param nomeTela O nome da tela para a qual navegar.
     */
    public void irParaTela(String nomeTela) {
        if (this.mostrarTela(nomeTela)) {
            this.caminhoAtual.push(nomeTela);
        }
    }

    /**
     * Volta para a tela anterior na pilha de navegação.
     */
    public void voltarTela() {
        if (!this.caminhoAtual.peek().equals("raiz")) {
            String nomeTelaAntigo = this.caminhoAtual.pop();
            String nomeTela = this.caminhoAtual.peek();
            if (!this.mostrarTela(nomeTela)) {
                this.caminhoAtual.push(nomeTelaAntigo);
            }
            return;
        }
    }

    /**
     * Ajusta a aparência da janela principal ao exibir uma nova tela.
     *
     * @param tela O painel que está sendo mostrado.
     */
    private void ajustarFramePrincipal(JPanel tela) {
        Estilos.visaoPrincipal((VisaoPrincipal) this.framePrincipal);

    }

    /**
     * Retorna a janela principal.
     *
     * @return O JFrame principal da aplicação.
     */
    public JFrame getFramePrincipal() {
        return framePrincipal;
    }

    /**
     * Retorna o nome da tela atual.
     *
     * @return O nome da tela que está sendo exibida.
     */
    public String getNomeTelaAtual() {
        return nomeTelaAtual;
    }

    /**
     * Adiciona uma informação ao cache.
     *
     * @param chave A chave para acessar a informação.
     * @param info  A informação a ser armazenada.
     */
    public void addNoCache(String chave, Object info){
        this.CACHE.put(chave, info);
    }

    /**
     * Recupera uma informação do cache.
     *
     * @param chave A chave da informação a ser recuperada.
     * @return O objeto armazenado no cache ou null se não existir.
     */
    public Object pegarInformacaoCache(String chave){
        return this.CACHE.get(chave);
    }

    /**
     * Constrói um HashMap validável a partir dos campos de entrada.
     *
     * @param nomeJogador1     Campo de texto do nome do jogador 1.
     * @param nomeJogador2     Campo de texto do nome do jogador 2.
     * @param camposDeEntrada   Lista de campos de entrada para outros dados.
     * @return Um HashMap com os dados validados.
     */
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

        return hash;
    }

    /**
     * Exibe uma mensagem de erro ao usuário.
     *
     * @param mensagem A mensagem de erro a ser exibida.
     */
    public void gerarAvisoErro(String mensagem){
        JOptionPane.showMessageDialog(framePrincipal, mensagem, "Altere as informações", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Solicita a criação de um novo jogo através do transmissor.
     */
    public void solicitarNovoJogo(){
        Transmissor.solicitacaoNovoJogo();
    }

    /**
     * Adiciona a visão do jogo ao gerenciador de telas.
     * Se a visão já existir, ela é atualizada.
     */
    public void adicionarVisaoJogo(){
        if (!this.telasGerenciadas.containsKey("JOGO")){
            this.adicionarNovaTela("JOGO", new VisaoJogo(this));
        }
        else{
            this.telasGerenciadas.put("JOGO", new VisaoJogo(this));
        }
    }
    
    /**
     * Solicita os salvamentos disponíveis através do transmissor.
     *
     * @return Uma lista de GerenciadorArquivo com os salvamentos.
     */
    public ArrayList<GerenciadorArquivo> solicitarSalvamentos (){
        Transmissor.buscarSalvamentos();
        return Configuracoes.arquvosSaves;
    }
}
