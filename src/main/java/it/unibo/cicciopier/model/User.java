package it.unibo.cicciopier.model;


import com.google.gson.annotations.SerializedName;
import it.unibo.cicciopier.model.settings.Screen;

import java.awt.*;
import java.util.HashMap;

public class User {
    @SerializedName("username")
    private final String username;
    @SerializedName("sound_volume")
    private int soundVolume;
    @SerializedName("music_volume")
    private int musicVolume;
    @SerializedName("scores")
    private HashMap<String,Integer> levelScores;
    @SerializedName("resolution")
    private Dimension resolution;

    public User(String username) {
        this.levelScores = new HashMap<>();
        this.username = username;
        this.soundVolume = 50;
        this.musicVolume = 50;
        Level.getLevels().forEach(level -> levelScores.put(level.getJsonId(), -1));
        this.resolution = Screen.getScreenMaxSize();
    }


    public int getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(int soundVolume) {
        this.soundVolume = soundVolume;
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public int getLevelScore(String level) {
        return this.levelScores.get(level);
    }

    public void setLevelScore(String level, int score){
        this.levelScores.replace(level,score);
    }

    public String getUsername() {
        return username;
    }
    public void updateLevels(){
        if (levelScores != null){
            Level.getLevels().forEach(level -> this.levelScores.putIfAbsent(level.getJsonId(),-1));
        }
    }

    public Dimension getResolution() {
        return resolution;
    }

    public void setResolution(Dimension resolution) {
        this.resolution = resolution;
    }
}
