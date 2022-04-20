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
    COIN("/audios/entities/items/coin.wav"),
    /**
     * Represents the audio for player jump
     */
    JUMP("/audios/entities/jump.wav"),
    /**
     * Represents the audio for the explosion
     */
    EXPLOSION("/audios/entities/effects/explosion.wav"),
    /**
     * Represents the audio for the bite of the player
     */
    BITE("/audios/entities/effects/bite.wav"),
    /**
     * Represents the audio when the player picks up a boost
     */
    BOOST_PICKUP("/audios/entities/items/boost_pickup.wav"),
    /**
     * Represents the audio when the player takes damage
     */
    DAMAGE("/audios/entities/effects/damage.wav"),
    /**
     * Represents the audio for the laser
     */
    LASER("/audios/entities/enemies/boss/laser.wav"),
    /**
     * Represents the audio for the meteor
     */
    METEOR("/audios/entities/enemies/boss/meteor.wav"),
    /**
     * Represents the audio when the missile get launched
     */
    LAUNCH("/audios/entities/enemies/boss/missile_launch.wav"),
    /**
     * Represent the audio when you pick an item
     */
    FOOD_PICKUP("/audios/entities/items/food_pickup.wav"),
    /**
     * Represents the audio for the click in main buttons
     */
    MAIN_BUTTON("/audios/menu/main_button.wav"),
    /**
     * Represents the audio for the hover action
     */
    HOVER_BUTTON("/audios/menu/hover_button.wav"),
    /**
     * Represents the audio for the click of starting the game
     */
    GAME_HOVER("/audios/menu/game_button.wav"),
    /**
     * Represents the audio for the click in side buttons
     */
    SIDE_BUTTON("/audios/menu/side_button.wav"),
    /**
     * Represents the audio for the typing
     */
    TYPING("/audios/menu/typing.wav"),
    /**
     * Represents the sound of the ShootingPea attacking
     */
    POP("/audios/entities/enemies/pop.wav"),
    /**
     * Represents the sound of the NinjaPotato attacking
     */
    SLASH("/audios/entities/enemies/slash.wav"),
    /**
     * Represents the MindPineapple attacking
     */
    SPIKES("/audios/entities/enemies/spikes.wav");

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
     * Get the byte code of the audio
     *
     * @return byte array
     */
    public byte[] getBytes() {
        return Arrays.copyOf(this.bytes, this.bytes.length);
    }

}
