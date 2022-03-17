package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.view.Texture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        LOGGER.info("Starting...");
        for(Texture t : Texture.values()) {
            try {
                t.load();
            } catch (IOException e) {
                LOGGER.error("Error loading texture...", e);
                System.exit(1);
            }
        }
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
