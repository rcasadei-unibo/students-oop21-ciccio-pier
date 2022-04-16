package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.AudioController;
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
            AudioController.getInstance().setMusicVolume(0.2F);
            AudioController.getInstance().setSoundVolume(0.2F);
            Screen.setCurrentDimension(Screen.getScreenMaxSize());
            CustomFont.getInstance().load();
            DeveloperMode.setActive(true);
            GameEngine e = new GameEngine(null, Level.SECOND_LEVEL);
            e.load();
            e.getMusic().ifPresent(m -> AudioController.getInstance().playMusic(m));
            e.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

}
