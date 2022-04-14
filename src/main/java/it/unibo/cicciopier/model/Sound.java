package it.unibo.cicciopier.model;

import java.io.*;
import java.util.Arrays;

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
     * Represents the audio for the laser
     */
    LASER("/audios/laser.wav"),
    /**
     * Represents the audio for the meteor
     */
    METEOR("/audios/meteor.wav"),
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
    SIDE_BUTTON("/audios/menu/sideButton.wav"),
    /**
     * Represents the audio for the typing
     */
    TYPING("/audios/menu/typing.wav");

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
     * Load sound file
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
