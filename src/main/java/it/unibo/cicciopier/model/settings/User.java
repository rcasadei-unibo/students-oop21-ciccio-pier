package it.unibo.cicciopier.model.settings;


import com.google.gson.annotations.SerializedName;
import it.unibo.cicciopier.model.Level;

import java.awt.*;
import java.util.HashMap;

/**
 * This class define the values that a user is composed by and store them in this class
 */
public class User {
    @SerializedName("username")
    private final String username;
    @SerializedName("sound_volume")
    private int soundVolume;
    @SerializedName("music_volume")
    private int musicVolume;
    @SerializedName("scores")
    private HashMap<String, Integer> levelScores;
    @SerializedName("resolution")
    private Dimension resolution;

    /**
     * this constructor generates a new user with default values giving him the username given
     *
     * @param username of the user that will be created
     */
    public User(String username) {
        this.levelScores = new HashMap<>();
        this.username = username;
        this.soundVolume = 50;
        this.musicVolume = 50;
        Level.getLevels().forEach(level -> this.levelScores.put(level.getJsonId(), 34));
        this.resolution = Screen.getScreenMaxSize();
    }

    /**
     * This function returns the sound volume setting of the user
     *
     * @return the sound setting of the user
     */
    public int getSoundVolume() {
        return soundVolume;
    }

    /**
     * This function sets the sound volume setting
     *
     * @param soundVolume the sound volume setting
     */
    public void setSoundVolume(final int soundVolume) {
        this.soundVolume = soundVolume;
    }

    /**
     * This function returns the music volume setting of the user
     *
     * @return the music setting of the user
     */
    public int getMusicVolume() {
        return musicVolume;
    }

    /**
     * This function sets the music volume setting
     *
     * @param musicVolume the music volume setting
     */
    public void setMusicVolume(final int musicVolume) {
        this.musicVolume = musicVolume;
    }

    /**
     * This function returns the level score of a specific level of the user
     *
     * @param level the level that you want the score of
     * @return the level score of the given level
     */
    public int getLevelScore(final String level) {
        return this.levelScores.get(level);
    }

    /**
     * This function sets the level score of a specific level with a given score
     *
     * @param level the level that you want to update the score of
     * @param score the score that will be inserted
     */
    public void setLevelScore(final String level, final int score) {
        this.levelScores.replace(level, score);
    }

    /**
     * This function returns the username of the user
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * This function checks if the user has every level in his level score variable if not it adds them
     */
    public void updateLevels() {
        if (levelScores != null) {
            Level.getLevels().forEach(level -> this.levelScores.putIfAbsent(level.getJsonId(), -1));
        }
    }

    /**
     * This function returns the resolution setting of the user
     *
     * @return the resolution setting
     */
    public Dimension getResolution() {
        return resolution;
    }

    /**
     * This function set the resolution setting of the user with a given resolution setting
     *
     * @param resolution the resolution setting
     */
    public void setResolution(final Dimension resolution) {
        this.resolution = resolution;
    }
}
