package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.GameLoader;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class of this application
 */
public final class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * Start method of this application
     *
     * @param args parameters given when the game has been started
     */
    public static void main(final String[] args) {
        try {
            LOGGER.error("Starting the game...");
            new GameLoader().load();
            new MainMenuController().load();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

}
