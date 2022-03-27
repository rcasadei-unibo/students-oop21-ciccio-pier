package it.unibo.cicciopier.model;

import javax.sound.sampled.*;
import java.io.*;
/**
 * Enum to store all the sound files, and load them
 */
public enum Sound {
    /**
     * Represents the audio of a coin
     */
    COIN("/audios/coin.wav");

    private final String fileName;
    private byte[] bytes;

    /**
     * Constructor for this class
     *
     * @param fileName path to the file that needs to be open
     */
    Sound(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Load all sound files
     *
     * @throws UnsupportedAudioFileException in case the data is not valid
     * @throws IOException                   any type of I/O exception has occurred
     * @throws LineUnavailableException      when the line cannot be opened
     */
    public void load() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        final int minVolume = 0;
        InputStream is = getClass().getResourceAsStream(this.fileName);
        assert is != null;
        this.bytes = is.readAllBytes();
        //use this solution, instead of adding a new dependency
        //play all the sound once at the beginning, to prevent lag and freezing of the screen
        try {
            Clip clip = AudioSystem.getClip();
            ByteArrayInputStream bis = new ByteArrayInputStream(this.bytes);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            clip.open(ais);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(20F * (float) Math.log10(minVolume));
            clip.flush();
            clip.setFramePosition(0);
            clip.start();
            //close the streams
            bis.close();
            ais.close();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play the sound
     *
     * @param soundVolume how much the volume must be
     */
    public void playSound(final float soundVolume) {
        try {
            Clip clip = AudioSystem.getClip();
            ByteArrayInputStream bis = new ByteArrayInputStream(this.bytes);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            clip.open(ais);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(20F * (float) Math.log10(soundVolume));
            clip.setFramePosition(0);
            clip.start();
            //close the streams
            bis.close();
            ais.close();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
