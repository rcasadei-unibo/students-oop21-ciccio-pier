package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.model.Music;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.effects.BiteView;
import it.unibo.cicciopier.view.entities.effects.ExplosionView;
import it.unibo.cicciopier.view.entities.enemies.boss.LaserView;
import it.unibo.cicciopier.view.entities.PlayerView;
import it.unibo.cicciopier.view.entities.enemies.*;
import it.unibo.cicciopier.view.entities.enemies.boss.BroccoliView;
import it.unibo.cicciopier.view.entities.items.CoinView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Collection;

public class GameLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameLoader.class);

    /**
     * Load game resources.
     */
    public void load() {
        this.loadSounds();
        this.loadMusics();
        this.loadAudio();
        this.loadTextures();
        this.loadAnimations(PlayerView.ANIMATIONS.values());
        this.loadAnimation(PlayerView.BLOOD_ANIMATION);
        this.loadAnimations(ShootingPeaView.ANIMATIONS.values());
        this.loadAnimations(NinjaPotatoView.ANIMATIONS.values());
        this.loadAnimations(RollingPeachView.ANIMATIONS.values());
        this.loadAnimations(MindPineappleView.ANIMATIONS.values());
        this.loadAnimations(CryingOnionView.ANIMATIONS.values());
        this.loadAnimations(BroccoliView.ANIMATIONS.values());
        this.loadAnimation(CoinView.ANIMATION);
        this.loadAnimation(ExplosionView.ANIMATION);
        this.loadAnimation(LaserView.ANIMATION);
        this.loadAnimation(BiteView.ANIMATION);
    }

    /**
     * Load all the sounds
     */
    private void loadSounds() {
        for (final Sound sound : Sound.values()) {
            try {
                sound.load();
            } catch (NullPointerException | IOException e) {
                LOGGER.error("Error loading sound {}...", sound, e);
                System.exit(1);
            }
        }
    }

    /**
     * Load all the musics
     */
    private void loadMusics() {
        for (final Music music : Music.values()) {
            try {
                music.load();
            } catch (NullPointerException | IOException e) {
                LOGGER.error("Error loading music {}...", music, e);
                System.exit(1);
            }
        }
    }

    /**
     * Load audio system
     */
    private void loadAudio() {
        try {
            AudioController.getInstance().load();
        } catch (LineUnavailableException e) {
            LOGGER.error("Error loading AudioController!", e);
            AudioController.getInstance().disable();
        }
    }

    /**
     * Load all the textures
     */
    private void loadTextures() {
        for (final Texture texture : Texture.values()) {
            try {
                texture.load();
            } catch (IOException e) {
                LOGGER.error("Error loading texture...", e);
                System.exit(1);
            }
        }
    }

    /**
     * Load all the animations
     */
    private void loadAnimations(final Collection<Animation> animations) {
        for (final Animation a : animations) {
            loadAnimation(a);
        }
    }

    /**
     * Load the animation
     */
    private void loadAnimation(final Animation a) {
        try {
            a.load();
        } catch (Exception e) {
            LOGGER.error("Error loading animation...", e);
            System.exit(1);
        }
    }

}
