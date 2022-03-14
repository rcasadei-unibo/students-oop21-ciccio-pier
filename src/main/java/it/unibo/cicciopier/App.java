package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.GameEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        LOGGER.info("Starting...");
        try {
            GameEngine e = new GameEngine("level-1-1.tmx");
            e.load();
            e.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
        }
    }

}
