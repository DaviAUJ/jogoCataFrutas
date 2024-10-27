package visao.estilos;
import com.formdev.flatlaf.FlatDarkLaf;
import sons.EfeitoSonoro;
import visao.VisaoPrincipal;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Estilos {

    public static void visaoPrincipal(VisaoPrincipal principal) {
        switch (principal.getGerenciador().getNomeTelaAtual()){
            default:{
                principal.setBounds(0, 0, 1280, 740);
                break;
            }
        }





    }

    public static void animacaoClicavel(JComponent elementoBotao) {
        elementoBotao.addMouseListener(new MouseAdapter() {
            private int posX = elementoBotao.getX();
            private int posY = elementoBotao.getY();
            private int height = elementoBotao.getHeight();
            private int width = elementoBotao.getWidth();
            @Override
            public void mouseEntered(MouseEvent e) {
                elementoBotao.setBounds(posX, posY-5, width, height);

                try {
                    EfeitoSonoro som = new EfeitoSonoro(
                            EfeitoSonoro.pasta + "MouseHover.wav",
                            0.3f
                    );

                    som.tocar();
                }
                catch (Exception ex) {  System.out.println(e + ""); }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                elementoBotao.setBounds(posX, posY+5, width, height);
            }
        });

    }
}
