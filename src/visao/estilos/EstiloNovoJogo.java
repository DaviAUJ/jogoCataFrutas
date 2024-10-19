package visao.estilos;


import visao.VisaoNovoJogo;
import visao.componentes.BarrinhaConfiguracoes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public abstract class EstiloNovoJogo {
    private static int TELA_LARGURA = 1280;
    private static int TELA_ALTURA = 720;

    public static void aplicarEstilo(VisaoNovoJogo tela){

        tela.setLayout(null);
        tela.setBounds(0, 0, TELA_LARGURA, TELA_ALTURA);

        tela.conteudoRolavel = new JPanel();
        tela.scrollConfigs = new JScrollPane(tela.conteudoRolavel);
        tela.tituloTela = new JLabel();
        tela.botaoOk = new JButton();
        tela.botaoVoltar = new JButton();
        tela.botaoPreview = new JButton();
        tela.preview = estiloPreview();

        estiloTituloPrincipal(tela.tituloTela);
        estiloScroll(tela.scrollConfigs);
        estiloConfiguracoes(tela.conteudoRolavel, tela);
        estiloBotoesSelecao(tela.botaoVoltar, tela.botaoOk, tela.botaoPreview);
        estiloPreview();



        JLabel fundo = configurarFundo(tela);
        fundo.add(tela.scrollConfigs);
        fundo.add(tela.tituloTela);
        fundo.add(tela.botaoOk);
        fundo.add(tela.botaoVoltar);
        fundo.add(tela.botaoPreview);
        fundo.add(tela.preview);

    }

    public static void estiloScroll(JScrollPane scroll){
        int LARGURA = 512;
        int ALTURA = 500;

        scroll.setBounds(TELA_LARGURA-LARGURA-100, 150, 512, 500);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(5);


    }

    private static JLabel configurarFundo(VisaoNovoJogo tela){
        ImageIcon imagemBorda = new ImageIcon("./assets/imgs/geral/borda.png");
        Image imagemFundo = new ImageIcon("./assets/imgs/novoJogo/fundoPinheiros.png").getImage();

        JLabel borda = new JLabel(imagemBorda);
        borda.setBounds(0, 0, 1280, 720);
        borda.setOpaque(false);

        FundoAnimado fundo = new FundoAnimado(imagemFundo, -800, 0);
        fundo.setBounds(0, 0, 1280, 720);
        fundo.setOpaque(true);

        fundo.add(borda);
        tela.add(fundo);
        fundo.iniciarAnimacao(50);
        return borda;
    }

    private static void estiloTituloPrincipal(JLabel titulo){
        int TITULO_LARGURA = 672;
        int TITULO_ALTURA = 57;
        ImageIcon textoTitulo = new ImageIcon("./assets/imgs/novoJogo/titulo.png");
        titulo.setIcon(textoTitulo);
        titulo.setBounds((TELA_LARGURA - TITULO_LARGURA) / 2, 50, TITULO_LARGURA, TITULO_ALTURA);

    }

    private static void estiloBotoesSelecao(JButton botaoVoltar, JButton botaoOk, JButton botaoPreview){
        int botaoVoltarAltura = 34+10;
        int botaoVoltarLargura = 138+10;

        int botaoOkAltura = 34+10;
        int botaoOkLargura = 70+10;

        int botaoPreviewAltura = 42+10;
        int botaoPreviewLargura = 157+10;

        int margemEsquerda = 160;

        estiloBtnVoltar(botaoVoltar);
        botaoVoltar.setBounds(margemEsquerda + botaoOkLargura + botaoPreviewLargura, TELA_ALTURA - botaoVoltarAltura - 50, botaoVoltarLargura, botaoVoltarAltura);
        Estilos.animacaoClicavel(botaoVoltar);


        estiloBtnOk(botaoOk);
        botaoOk.setBounds(margemEsquerda + botaoPreviewLargura, TELA_ALTURA - botaoOkAltura - 51, botaoOkLargura, botaoOkAltura);
        Estilos.animacaoClicavel(botaoOk);

        estiloBtnPreview(botaoPreview);
        botaoPreview.setBounds(margemEsquerda, TELA_ALTURA - botaoPreviewAltura - 46 , botaoPreviewLargura, botaoPreviewAltura);
        Estilos.animacaoClicavel(botaoPreview);


    }

    private static void estiloBtnVoltar(JButton botao){

        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/voltar.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);

    }

    private static void estiloBtnOk(JButton botao){
        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/opcoesIniciais/ok.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
    }

    private static void estiloBtnPreview(JButton botao){
        ImageIcon spriteBotao = new ImageIcon("./assets/imgs/novoJogo/preview2.png");
        botao.setIcon(spriteBotao);
        botao.setBackground(new Color(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
    }

    private static JPanel estiloPreview(){
        int PREVIEW_LARGURA = 420;
        int PREVIEW_ALTURA = 420;
        Image fundoPainel = new ImageIcon("./assets/imgs/novoJogo/preview_caixa.png").getImage().getScaledInstance(PREVIEW_LARGURA, PREVIEW_ALTURA, Image.SCALE_DEFAULT);

        JPanel preview = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fundoPainel, 0, 0, null);
                this.revalidate();
            }
        };

        preview.setBorder(BorderFactory.createEmptyBorder(26, 28, 26, 26));
        JPanel teste = new JPanel();
        preview.add(teste);
        preview.setLayout(new BoxLayout(preview, BoxLayout.PAGE_AXIS));

        preview.setBounds(150, 145, PREVIEW_LARGURA, PREVIEW_ALTURA);
        preview.setOpaque(false);



        return preview;
    }

    private static void estiloConfiguracoes(JPanel configuracoes, VisaoNovoJogo tela){
        configuracoes.setLayout(new BoxLayout(configuracoes, BoxLayout.Y_AXIS));
        configuracoes.setOpaque(false);

        ArrayList <JFormattedTextField> campos = new ArrayList<>();
        ArrayList <String> listaImagens = new ArrayList<>();

        listaImagens.add("./assets/imgs/novoJogo/labelJogo.png");
        listaImagens.add("./assets/imgs/novoJogo/dimensoesJogo.png");
        listaImagens.add("./assets/imgs/novoJogo/maracujas.png");
        listaImagens.add("./assets/imgs/novoJogo/espacoMochila.png");
        listaImagens.add("./assets/imgs/novoJogo/chanceBichadas.png");
        listaImagens.add("./assets/imgs/novoJogo/quantidadeElementos.png");
        listaImagens.add("./assets/imgs/novoJogo/pedras.png");
        listaImagens.add("./assets/imgs/novoJogo/abacates.png");
        listaImagens.add("./assets/imgs/novoJogo/acerolas.png");
        listaImagens.add("./assets/imgs/novoJogo/amoras.png");
        listaImagens.add("./assets/imgs/novoJogo/coco.png");
        listaImagens.add("./assets/imgs/novoJogo/goiabas.png");
        listaImagens.add("./assets/imgs/novoJogo/laranjas.png");


        configuracoes.add(Box.createRigidArea(new Dimension(0, 20)));

        ImageIcon imagemSecaoJogo = new ImageIcon(listaImagens.getFirst());
        JLabel secaoJogo = new JLabel(imagemSecaoJogo);
        secaoJogo.setPreferredSize(new Dimension(imagemSecaoJogo.getIconWidth(), imagemSecaoJogo.getIconHeight()));
        configuracoes.add(secaoJogo);

        configuracoes.add(Box.createRigidArea(new Dimension(0, 40)));


        for (int i = 1; i < 5; i++){
            ImageIcon imagem = new ImageIcon(listaImagens.get(i));
            BarrinhaConfiguracoes barrinha = new BarrinhaConfiguracoes(imagem,false);
            campos.add(barrinha.valor);
            configuracoes.add(barrinha);
            configuracoes.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        configuracoes.add(Box.createRigidArea(new Dimension(0, 20)));
        ImageIcon imagemSecaoQtdElementos = new ImageIcon(listaImagens.get(5));
        JLabel secaoQtdElementos = new JLabel(imagemSecaoQtdElementos);
        secaoQtdElementos.setPreferredSize(new Dimension(imagemSecaoQtdElementos.getIconWidth(), imagemSecaoQtdElementos.getIconHeight()));
        configuracoes.add(secaoQtdElementos);

        configuracoes.add(Box.createRigidArea(new Dimension(0, 40)));

        for (int i = 6; i < listaImagens.size(); i++){
            ImageIcon imagem = new ImageIcon(listaImagens.get(i));
            BarrinhaConfiguracoes barrinha = new BarrinhaConfiguracoes(imagem,true);
            campos.add(barrinha.valor);
            campos.add(barrinha.valor2);
            configuracoes.add(barrinha);
            configuracoes.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        tela.camposDeEntrada = campos;




    }


}
