package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.controller.GameLoader;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.DeveloperMode;
import it.unibo.cicciopier.model.settings.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to start a level without the menu (developing purposes)
 */
public final class LevelMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelMain.class);

    /**
     * Start method of this application
     *
     * @param args parameters given when the game has been started
     */
    public static void main(final String[] args) {
        try {
            LOGGER.error("Starting the game...");
            new GameLoader().load();
            Screen.setCurrentDimension(Screen.getScreenMaxSize());
            CustomFont.getInstance().load();
            DeveloperMode.setActive(true);
            GameEngine e = new GameEngine(null, Level.FIRST_LEVEL);
            e.load();
            e.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

}
