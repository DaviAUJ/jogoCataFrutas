package sons;

import excecoes.VolumeInvalidoException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class EfeitoSonoro {
    public static final String pasta = "assets" +
    File.separator + "sfx" + File.separator;

    AudioInputStream fluxo;
    String caminhoArquivo;
    Clip clip;

    private float volume;

    public EfeitoSonoro(String caminhoArquivo)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        fluxo = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());

        clip = AudioSystem.getClip();
        clip.open(fluxo);
    }

    public EfeitoSonoro(String caminhoArquivo, float volume)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        fluxo = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());

        clip = AudioSystem.getClip();
        clip.open(fluxo);
        setVolume(volume);
    }

    public void tocar() {
        clip.start();
    }

    public void setVolume(float volume) throws VolumeInvalidoException {
        if(volume < 0f || volume > 1f) {
            throw new VolumeInvalidoException("Volume invalido");
        }

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public void parar() {
        clip.stop();
        clip.close();
    }
}
