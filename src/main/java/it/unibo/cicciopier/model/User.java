package it.unibo.cicciopier.model;

import com.google.gson.annotations.Expose;

public class User {
    private final String username;
    private int soundVolume;
    private int musicVolume;
    private int level1Score;
    private int level2Score;
    private int level3Score;
    private int level4Score;

    public User(String username, int soundVolume, int musicVolume, int level1Score, int level2Score, int level3Score, int level4Score) {
        this.username = username;
        this.soundVolume = soundVolume;
        this.musicVolume = musicVolume;
        this.level1Score = level1Score;
        this.level2Score = level2Score;
        this.level3Score = level3Score;
        this.level4Score = level4Score;

    }
    public User(String username){
        this(username,50, 50,-1,-1,-1,-1);
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

    public int getLevel1Score() {
        return level1Score;
    }

    public void setLevel1Score(int level1Score) {
        this.level1Score = level1Score;
    }

    public int getLevel2Score() {
        return level2Score;
    }

    public void setLevel2Score(int level2Score) {
        this.level2Score = level2Score;
    }

    public int getLevel3Score() {
        return level3Score;
    }

    public void setLevel3Score(int level3Score) {
        this.level3Score = level3Score;
    }

    public int getLevel4Score() {
        return level4Score;
    }

    public void setLevel4Score(int levelBossScore) {
        this.level4Score = levelBossScore;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", soundVolume=" + soundVolume +
                ", musicVolume=" + musicVolume +
                ", level1Score=" + level1Score +
                ", level2Score=" + level2Score +
                ", level3Score=" + level3Score +
                ", level4Score=" + level4Score +
                '}';
    }
}
