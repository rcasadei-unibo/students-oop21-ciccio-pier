package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.model.Music;
import it.unibo.cicciopier.view.entities.ExplosionView;
import it.unibo.cicciopier.view.entities.LaserView;
import it.unibo.cicciopier.view.entities.PlayerView;
import it.unibo.cicciopier.view.entities.enemies.BroccoliView;
import it.unibo.cicciopier.view.items.CoinView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

public final class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        LOGGER.info("Starting...");
        //load audio system
        AudioController.getInstance().load();
        //load all the textures
        for (Texture t : Texture.values()) {
            try {
                t.load();
            } catch (IOException e) {
                LOGGER.error("Error loading texture...", e);
                System.exit(1);
            }
        }
        //load all the animation
        loadAnimations(PlayerView.ANIMATIONS.values());
        loadAnimations(BroccoliView.ANIMATIONS.values());
        loadAnimation(CoinView.ANIMATION);
        loadAnimation(ExplosionView.ANIMATION);
        loadAnimation(LaserView.ANIMATION);
        try {
            GameEngine e = new GameEngine(null, Level.FIRST_LEVEL);
            e.load();
            e.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

    private static void loadAnimations(final Collection<Animation> animations) {
        //load all the animations
        for (Animation a : animations) {
            loadAnimation(a);
        }
    }

    private static void loadAnimation(final Animation a) {
        try {
            //LOGGER.info("Loading animation...");
            a.load();
        } catch (Exception e) {
            LOGGER.error("Error loading animation...", e);
            System.exit(1);
        }
    }

}
