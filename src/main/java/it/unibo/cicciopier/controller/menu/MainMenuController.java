package it.unibo.cicciopier.controller.menu;


import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.model.Music;
import it.unibo.cicciopier.view.menu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MainMenuController implements MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class);
    private final AudioController audioController;
    private static final int MAX_VOLUME = 1;
    private static final int MIN_VOLUME = 0;
    private final MenuManagerView menu;
    private String username;

    public MainMenuController() {
        this.menu = new MenuManagerView(this);
        this.audioController = AudioController.getAudioController();
        this.audioController.playMusic(Music.BACKGROUND);
        this.show(ViewPanels.LOGIN);
    }

    public void action(MenuAction menuAction) {
        switch (menuAction) {
            case SHOW: {
                LOGGER.info("Action SHOW called Wrongly");
                break;
            }
            case PLAY_LEVEL: {
                break;
            }
            case INCREASE_MUSIC_AUDIO: {
                if (audioController.getMusicVolume() != MAX_VOLUME) {
                    audioController.setMusicVolume((float) ((Math.round(audioController.getMusicVolume() * 100) + 10)) / 100);
                    menu.getSettingsView().updateMusicAudioText();
                    LOGGER.info("Music volume set to:" + audioController.getMusicVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_MUSIC_AUDIO: {
                if (audioController.getMusicVolume() != MIN_VOLUME) {
                    audioController.setMusicVolume((float) ((Math.round(audioController.getMusicVolume() * 100) - 10)) / 100);
                    menu.getSettingsView().updateMusicAudioText();
                    LOGGER.info("Music volume set to:" + audioController.getMusicVolume() * 100 + "%");
                }
                break;
            }
            case INCREASE_GAME_AUDIO: {
                if (audioController.getSoundVolume() != MAX_VOLUME) {
                    audioController.setSoundVolume((float) ((Math.round(audioController.getSoundVolume() * 100) + 10)) / 100);
                    menu.getSettingsView().updateGameAudioText();
                    LOGGER.info("Game volume set to:" + audioController.getSoundVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_GAME_AUDIO: {
                if (audioController.getSoundVolume() != MIN_VOLUME) {
                    audioController.setSoundVolume((float) ((Math.round(audioController.getSoundVolume() * 100) - 10)) / 100);
                    menu.getSettingsView().updateGameAudioText();
                    LOGGER.info("Game volume set to:" + audioController.getSoundVolume() * 100 + "%");
                }
                break;
            }
            case QUIT: {
                quitAction();
            }
            case LOGIN:{
                //TODO check if a Json called after the username given exist and load it or create a new one
                this.username = menu.getLoginView().getUsername();
                this.show(ViewPanels.MAIN_MENU);
                break;
            }
            case LOGOUT:{
                this.username = null;
                menu.getLoginView().logout();
                this.show(ViewPanels.LOGIN);
            }
        }
    }

    public void startLevel( GameEngine gameEngine){
        LOGGER.info("Starting level...");

        try {
            AudioController.getAudioController().stopMusic(Music.BACKGROUND);
            this.menu.setVisible(false);
            AudioController.getAudioController().playMusic(Music.GAME);
            gameEngine.load();
            gameEngine.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show(ViewPanels viewPanels) {
        LOGGER.info("Changing menu view to: " + viewPanels);
        menu.setVisible(viewPanels);
    }

    public void quitAction() {
        LOGGER.info("Exiting...");
        System.exit(0);
    }

    public String getUsername() {
        return username;
    }
}