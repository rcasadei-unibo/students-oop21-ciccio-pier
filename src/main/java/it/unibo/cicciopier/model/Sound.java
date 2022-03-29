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
    COIN("/audios/coin.wav"),
    /**
     * Represents the audio for player jump
     */
    JUMP("/audios/jump.wav"),
    /**
     * Represents the audio for the explosion
     */
    EXPLOSION("/audios/explosion.wav"),
    /**
     * Represents the audio when the missile get launched
     */
    LAUNCH("/audios/launch.wav"),
    /**
     * Represent the audio when you pick an item
     */
    ITEM("/audios/item.wav"),
    /**
     * Represents the audio for the click in main buttons
     */
    MAIN_BUTTON("/audios/menu/mainButton.wav"),
    /**
     * Represents the audio for the hover action
     */
    HOVER_BUTTON("/audios/menu/hoverButton.wav"),
    /**
     * Represents the audio for the click of starting the game
     */
    GAME_HOVER("/audios/menu/gameButton.wav"),
    /**
     * Represents the audio for the click in side buttons
     */
    SIDE_BUTTON("/audios/menu/sideButton.wav");

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
            final float startingDecibel  = 80F;
            Clip clip = AudioSystem.getClip();
            ByteArrayInputStream bis = new ByteArrayInputStream(this.bytes);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            clip.open(ais);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(startingDecibel * (float) Math.log10(soundVolume));
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
