package sons;

import excecoes.VolumeInvalidoException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Representa um efeito sonoro que pode ser tocado em um jogo.
 */
public class EfeitoSonoro {
    private AudioInputStream fluxo;
    private String caminhoArquivo;
    private Clip clip;

    private float volume;

    /**
     * Constrói um EfeitoSonoro com um caminho de arquivo.
     *
     * @param caminhoArquivo O caminho do arquivo de áudio.
     * @throws UnsupportedAudioFileException Se o arquivo de áudio não é suportado.
     * @throws IOException Se ocorrer um erro de I/O.
     * @throws LineUnavailableException Se não houver linhas disponíveis.
     */
    public EfeitoSonoro(String caminhoArquivo)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        fluxo = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());

        this.caminhoArquivo = caminhoArquivo;
        clip = AudioSystem.getClip();
        clip.open(fluxo);
    }

    /**
     * Constrói um EfeitoSonoro com um caminho de arquivo e um volume.
     *
     * @param caminhoArquivo O caminho do arquivo de áudio.
     * @param volume O volume do efeito sonoro (0 a 1).
     * @throws UnsupportedAudioFileException Se o arquivo de áudio não é suportado.
     * @throws IOException Se ocorrer um erro de I/O.
     * @throws LineUnavailableException Se não houver linhas disponíveis.
     * @throws VolumeInvalidoException Se o volume estiver fora do intervalo.
     */
    public EfeitoSonoro(String caminhoArquivo, float volume)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        fluxo = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());

        this.caminhoArquivo = caminhoArquivo;
        clip = AudioSystem.getClip();
        clip.open(fluxo);
        setVolume(volume);
    }

    /**
     * Toca o efeito sonoro uma vez.
     */
    public void tocar() {
        clip.start();
    }

    /**
     * Toca o efeito sonoro em loop contínuo.
     */
    public void tocarLoopando() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Verifica se o efeito sonoro está sendo reproduzido.
     *
     * @return True se o efeito sonoro está tocando, caso contrário, false.
     */
    public boolean estaTocando() {
        return clip.isRunning();
    }

    /**
     * Define o volume do efeito sonoro.
     *
     * @param volume O volume (0 a 1).
     * @throws VolumeInvalidoException Se o volume estiver fora do intervalo.
     */
    public void setVolume(float volume) throws VolumeInvalidoException {
        if(volume < 0f || volume > 1f) {
            throw new VolumeInvalidoException("Volume invalido");
        }

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    /**
     * Para a reprodução do efeito sonoro.
     */
    public void parar() {
        clip.stop();
        clip.close();
    }

    /**
     * Obtém o caminho do arquivo de áudio.
     *
     * @return O caminho do arquivo de áudio.
     */
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}
