package it.unibo.cicciopier.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Enum to store all the music files, and load them
 */
public enum Music {
    /**
     * Music for the main menu
     */
    BACKGROUND("/audios/menu.wav"),
    /**
     * Music for the normal game levels
     */
    GAME("/audios/game.wav"),
    /**
     * Music for the boss levels
     */
    BOSS("/audios/boss_music.wav");

    private final String fileName;
    private byte[] bytes;

    /**
     * Constructor for this class
     *
     * @param fileName path to the file that needs to be open
     */
    Music(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Load music file
     *
     * @throws IOException          any type of I/O exception has occurred
     * @throws NullPointerException if the given file is null
     */
    public void load() throws IOException, NullPointerException {
        InputStream is = getClass().getResourceAsStream(this.fileName);
        if (is == null) {
            throw new NullPointerException("File " + this.fileName + " does not exists!");
        }
        this.bytes = is.readAllBytes();
    }

    /**
     * Get the sound file name
     *
     * @return file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Get the byte code of the audio
     *
     * @return byte array
     */
    public byte[] getBytes() {
        return Arrays.copyOf(this.bytes, this.bytes.length);
    }

}
