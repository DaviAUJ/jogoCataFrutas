package elementos;
import frutas.Fruta;


public class Jogador extends Elemento{
    private int pontosMovimento = 0;
    private Mochila mochila = new Mochila();
    private int pontosOuro = 0;
    private String estado = "";
    private boolean buffForca = false;
    private boolean buffAgilidade = false;
    private boolean nerfBichada = false;

    public Jogador() {

    }

    public Jogador(String nome, int posX, int posY) {
    	this.nome = nome;
    	this.posicaoX = posX;
    	this.posicaoY = posY;
    }

    public int getPontosMovimento() {
        return pontosMovimento;
    }

    public void setPontosMovimento(int pontosMovimento) {
        this.pontosMovimento = pontosMovimento;
    }

    public int getPontosOuro() {
        return pontosOuro;
    }

    public void setPontosOuro(int pontosOuro) {
        this.pontosOuro = pontosOuro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isBuffForca() {
        return buffForca;
    }

    public void setBuffForca(boolean buffForca) {
        this.buffForca = buffForca;
    }

    public boolean isBuffAgilidade() {
        return buffAgilidade;
    }

    public void setBuffAgilidade(boolean buffAgilidade) {
        this.buffAgilidade = buffAgilidade;
    }

    public boolean isNerfBichada() {
        return nerfBichada;
    }

    public void setNerfBichada(boolean nerfBichada) {
        this.nerfBichada = nerfBichada;
    }

    public boolean catarFruta(Fruta fruta) {
        return false;
    }

    public boolean comerFruta(Fruta fruta) {
        return false;
    }

    public boolean empurrar(Jogador alvo) {
        return false;
    }

    public boolean moverCima() {
        return false;
    }

    public boolean moverBaixo() {
        return false;
    }

    public boolean moverEsquerda() {
        return false;
    }

    public boolean moverDireita() {
        return false;
    }

    public void gerarPontos() {
    }
}
