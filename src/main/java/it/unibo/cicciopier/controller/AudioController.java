package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton class for controlling the audio system
 */
public class AudioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudioController.class);
    private static final AudioController INSTANCE = new AudioController();
    private static final float STARTING_VOLUME = 0.5F;

    private final ExecutorService executor;
    private float musicVolume;
    private float soundVolume;
    private Clip musicClip;
    private boolean enabled;

    /**
     * Private Constructor for this class, so only one instance of this class can be created
     */
    private AudioController() {
        this.executor = Executors.newFixedThreadPool(25);
        this.musicVolume = STARTING_VOLUME;
        this.soundVolume = STARTING_VOLUME;
        this.enabled = true;
    }

    /**
     * Get the one and only instance of audioController
     *
     * @return audioController
     */
    public static AudioController getInstance() {
        return AudioController.INSTANCE;
    }

    /**
     * Load audio clip used for playing music
     */
    public void load() {
        try {
            this.musicClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            LOGGER.error("Fatal error loading AudioController!", e);
            this.enabled = false;
        }
        // load all the sounds
        for (final Sound sound : Sound.values()) {
            try {
                sound.load();
            } catch (NullPointerException | IOException e) {
                LOGGER.error("Error loading sound {}", sound, e);
            }
        }
        // load all the musics
        for (final Music music : Music.values()) {
            try {
                music.load();
            } catch (NullPointerException | IOException e) {
                LOGGER.error("Error loading music {}", music, e);
            }
        }
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
        if (this.enabled && this.musicClip.isOpen()) {
            this.setClipVolume(this.musicClip, this.musicVolume);
        }
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

    /**
     * Set volume of a given clip
     *
     * @param clip   the clip
     * @param volume the volume
     */
    private void setClipVolume(final Clip clip, final float volume) {
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(20F * (float) Math.log10(volume));
    }

    /**
     * Play the music in loop
     *
     * @param music what music must play
     */
    public void playMusic(final Music music) {
        if (!this.enabled) {
            return;
        }
        if (this.musicClip.isOpen()) {
            this.stopMusic();
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(music.getBytes());
             AudioInputStream ais = AudioSystem.getAudioInputStream(bis)) {
            this.musicClip.open(ais);
            this.setClipVolume(this.musicClip, this.musicVolume);
            this.musicClip.setFramePosition(0);
            this.musicClip.start();
            this.musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            LOGGER.error("Error playing music!", e);
        }
    }

    /**
     * Stop the music from loop
     */
    public void stopMusic() {
        if (this.enabled && this.musicClip.isOpen()) {
            this.musicClip.stop();
            this.musicClip.close();
        }
    }

    /**
     * Play a sound until has finished
     *
     * @param sound sound
     */
    public void playSound(final Sound sound) {
        if (!this.enabled) {
            return;
        }
        long time = System.currentTimeMillis();
        this.executor.submit(() -> {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(sound.getBytes());
                 AudioInputStream ais = AudioSystem.getAudioInputStream(bis)) {
                final Clip clip = AudioSystem.getClip();
                clip.open(ais);
                this.setClipVolume(clip, this.soundVolume);
                clip.setFramePosition(0);
                clip.start();
                clip.addLineListener(e -> {
                    if (e.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                LOGGER.error("Error playing sound!", e);
            }
        });
        LOGGER.info("Playing {} took {}ms", sound, System.currentTimeMillis() - time);
    }

}
