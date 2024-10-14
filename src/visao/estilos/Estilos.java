package visao.estilos;
import visao.VisaoPrincipal;

import javax.swing.*;
import java.awt.*;

public abstract class Estilos {

    public static void principal(VisaoPrincipal principal) {
        principal.setBounds(0, 0, 800, 300);
        principal.setLocationRelativeTo(null);
    }
}
