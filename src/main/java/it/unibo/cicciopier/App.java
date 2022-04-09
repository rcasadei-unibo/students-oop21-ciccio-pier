package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.view.LoadAnimation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.model.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public final class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        LOGGER.info("Starting...");
        for (Texture t : Texture.values()) {
            try {
                t.load();
            } catch (IOException e) {
                LOGGER.error("Error loading texture...", e);
                System.exit(1);
            }
        }
        //load all the sounds
        for (Sound sound : Sound.values()) {
            try {
                sound.load();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        //load all the music
        for (Music music : Music.values()) {
            try {
                music.load();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        //load all the animation for the game
        LoadAnimation loadAnimation = LoadAnimation.getLoadAnimation();
        loadAnimation.loadAllAnimation();
        try {
            GameEngine e = new GameEngine("level-1-1.tmx");
            e.load();
            e.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

}
