package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.Music;

/**
 * Singleton class for controlling the audio system
 */
public class AudioController {
    private static AudioController audioController = null;
    private static final float MAX_VOLUME = 1;

    private float musicVolume;
    private float soundVolume;

    /**
     * Private Constructor for this class, so only one instance of this class can be created
     */
    private AudioController() {
        this.musicVolume = AudioController.MAX_VOLUME;
        this.soundVolume = AudioController.MAX_VOLUME;
    }

    /**
     * Get the one and only instance of audioController
     *
     * @return audioController
     */
    public static AudioController getAudioController() {
        if (audioController == null) {
            AudioController.audioController = new AudioController();
        }
        return AudioController.audioController;
    }

    /**
     * Get the current music volume
     *
     * @return music volume
     */
    public float getMusicVolume() {
        return this.musicVolume;
    }

    /**
     * Set the music volume
     *
     * @param musicVolume how much the volume must be
     */
    public void setMusicVolume(final float musicVolume) {
        this.musicVolume = musicVolume;
        for (Music music : Music.values()) {
            music.changeVolume(musicVolume);
        }
    }

    /**
     * Play the music in loop
     *
     * @param music what music must play
     */
    public void playMusic(final Music music) {
        music.play(this.musicVolume);

    }

    /**
     * Stop the music from loop
     *
     * @param music what music must stop
     */
    public void stopMusic(final Music music) {
        music.stop();
    }

    /**
     * Play the sound
     *
     * @param sound what sound must  play
     */
    public void playSound(final Sound sound) {
        sound.playSound(this.soundVolume);
    }

    /**
     * Get the current sound volume
     *
     * @return sound volume
     */
    public float getSoundVolume() {
        return this.soundVolume;
    }

    /**
     * Set the sound volume
     *
     * @param soundVolume how much the sound volume must be
     */
    public void setSoundVolume(final float soundVolume) {
        this.soundVolume = soundVolume;
    }

}
