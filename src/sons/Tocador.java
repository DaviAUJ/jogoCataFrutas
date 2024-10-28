package sons;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;


public abstract class Tocador {
    private static final EfeitoSonoro[] canaisSFX = new EfeitoSonoro[3];
    private static EfeitoSonoro canalMusica;

    private static void decidirCanal(EfeitoSonoro som) {
        if(som.getCaminhoArquivo().charAt(7) == 'm') {
            try {
                canalMusica.parar();
            }
            catch(Exception _) {}

            canalMusica = som;
            canalMusica.setVolume(0.65f);
            canalMusica.tocarLoopando();

            return;
        }

        for(int i = 0; i < canaisSFX.length; i++) {
            if(canaisSFX[i] == null || !canaisSFX[i].estaTocando()) {
                canaisSFX[i] = som;
                canaisSFX[i].setVolume(0.5f);
                canaisSFX[i].tocar();

                return;
            }
        }
    }

    public static void configurarListener() {
        EventoSonoroHandler.adicionarEvento(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    decidirCanal(new EfeitoSonoro("assets" + File.separator + evt.getPropertyName()));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
