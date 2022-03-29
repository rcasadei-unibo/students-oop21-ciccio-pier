package it.unibo.cicciopier.model;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Enum to store all the music files, and load them
 */
public enum Music {
    BACKGROUND("/audios/menu.wav"),
    GAME("/audios/coin.wav");

    private final String fileName;
    private Clip clip;

    /**
     * Constructor for this class
     *
     * @param fileName path to the file that needs to be open
     */
    Music(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * load all music files
     *
     * @throws UnsupportedAudioFileException in case the data is not valid
     * @throws IOException                   any type of I/O exception has occurred
     * @throws LineUnavailableException      when the line cannot be opened
     */
    public void load() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        InputStream is = getClass().getResourceAsStream(this.fileName);
        assert is != null;
        AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        this.clip = AudioSystem.getClip();
        this.clip.open(ais);
        //close the streams
        is.close();
        ais.close();
    }

    /**
     * play the music
     *
     * @param musicVolume how much the volume must be
     */
    public void play(final float musicVolume) {
        this.changeVolume(musicVolume);
        this.clip.setFramePosition(0);
        this.clip.start();
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop the music from playing
     */
    public void stop() {
        this.clip.stop();
    }

    /**
     * Change the volume of all the music
     *
     * @param musicVolume how much it needs to change
     */
    public void changeVolume(final float musicVolume) {
        final float startingDecibel  = 80F;
        FloatControl volume = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(startingDecibel * (float) Math.log10(musicVolume));
    }
}
