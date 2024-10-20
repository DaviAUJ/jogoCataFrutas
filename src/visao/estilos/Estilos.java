package visao.estilos;
import com.formdev.flatlaf.FlatDarkLaf;
import visao.VisaoPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Estilos {

    public static void visaoPrincipal(VisaoPrincipal principal) {
        System.out.println(principal.getGerenciador().getNomeTelaAtual());
        switch (principal.getGerenciador().getNomeTelaAtual()){
            default:{
                principal.setBounds(0, 0, 1280, 740);
                break;
            }
        }





    }

    public static void animacaoClicavel(JComponent elemento) {
        if (elemento instanceof JButton elementoBotao){

            elementoBotao.addMouseListener(new MouseAdapter() {
               private int posX = elementoBotao.getX();
               private int posY = elementoBotao.getY();
               private int height = elementoBotao.getHeight();
               private int width = elementoBotao.getWidth();
               @Override
               public void mouseEntered(MouseEvent e) {
                   elemento.setBounds(posX, posY-5, width, height);
               }

               @Override
                public void mouseExited(MouseEvent e) {
                   elemento.setBounds(posX, posY+5, width, height);
               }
            });

        }
    }
}
