package sons;

import excecoes.VolumeInvalidoException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class EfeitoSonoro {
    private AudioInputStream fluxo;
    private String caminhoArquivo;
    private Clip clip;

    private float volume;

    public EfeitoSonoro(String caminhoArquivo)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        fluxo = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());

        this.caminhoArquivo = caminhoArquivo;
        clip = AudioSystem.getClip();
        clip.open(fluxo);
    }

    public EfeitoSonoro(String caminhoArquivo, float volume)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        fluxo = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());

        this.caminhoArquivo = caminhoArquivo;
        clip = AudioSystem.getClip();
        clip.open(fluxo);
        setVolume(volume);
    }

    public void tocar() {
        clip.start();
    }

    public void tocarLoopando() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public boolean estaTocando() {
        return clip.isRunning();
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

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}
