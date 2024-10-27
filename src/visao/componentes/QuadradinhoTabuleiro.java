package visao.componentes;

import visao.estilos.EstiloComponentes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class QuadradinhoTabuleiro extends JButton {
    public HashMap<String, ArrayList<Image>> imagens;
    private int TAMANHO;
    public String tipoPlural;
    public int indicador = 0;
    private int indiceAleatorio = 0;

    public QuadradinhoTabuleiro(int tamanho, String tipo, int indicador, HashMap<String, ArrayList<Image>> imagens) {
        this.TAMANHO = tamanho;
        this.tipoPlural = tipo + "s";
        this.indicador = indicador;
        this.indiceAleatorio = gerarIndiceAleatorio(tipo);
        this.imagens = imagens;


        EstiloComponentes.aplicarEstiloQuadradinhoTabuleiro(this);
    }

    public int getTamanho() {
        return TAMANHO;
    }

    private int gerarIndiceAleatorio(String tipo) {
        Random random = new Random();
        int max = 0;
        int min = 0;

        if (tipo.equals("pedra")) {
            max = 6;
        }
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image imagemFundo = null;

        if (this.tipoPlural.equals("pedras")){
            imagemFundo = this.imagens.get("pedras").get(this.indiceAleatorio);
        }
        else{
            switch (this.tipoPlural){
                case "jogador1s":{
                    imagemFundo = this.imagens.get("gramas").getFirst();
                    g.setColor(Color.BLUE);
                    g.drawRect(0, 0, TAMANHO, TAMANHO);
                    break;
                }
                case "jogador2s":{
                    imagemFundo = this.imagens.get("gramas").getFirst();
                    g.setColor(Color.RED);
                    g.drawRect(0, 0, TAMANHO, TAMANHO);
                    break;
                }
                default: {
                    imagemFundo = this.imagens.get(this.tipoPlural).get(this.indicador);
                }
            }
        }


        if (imagemFundo != null){
            Image imagemRedimencionada = imagemFundo.getScaledInstance(this.TAMANHO, this.TAMANHO, Image.SCALE_DEFAULT);
            ImageIcon imagemIcon = new ImageIcon(imagemRedimencionada);
            g.drawImage(imagemIcon.getImage(), 0, 0, null);
        }
    }

}
