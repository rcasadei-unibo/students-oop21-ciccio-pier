package it.unibo.cicciopier.controller.menu;


import it.unibo.cicciopier.App;
import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.view.menu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainMenuController implements MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private final AudioController audioController;
    private static int MAX_VOLUME = 1;
    private static int MIN_VOLUME = 0;
    private final MenuManagerView menu;

    public MainMenuController() {
        this.menu = new MenuManagerView(this);
        this.audioController = AudioController.getAudioController();
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
                    audioController.setMusicVolume((float) ((Math.round(audioController.getMusicVolume()*100) + 10))/100);
                    menu.getSettingsView().updateMusicAudioText();
                    LOGGER.info("Music volume set to:" + audioController.getMusicVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_MUSIC_AUDIO: {
                if (audioController.getMusicVolume() != MIN_VOLUME) {
                    audioController.setMusicVolume((float) ((Math.round(audioController.getMusicVolume()*100) - 10))/100);
                    menu.getSettingsView().updateMusicAudioText();
                    LOGGER.info("Music volume set to:" + audioController.getMusicVolume() * 100 + "%");
                }
                break;
            }
            case INCREASE_GAME_AUDIO: {
                if (audioController.getSoundVolume() != MAX_VOLUME) {
                    audioController.setSoundVolume((float) ((Math.round(audioController.getSoundVolume()*100) + 10))/100);
                    menu.getSettingsView().updateGameAudioText();
                    LOGGER.info("Game volume set to:" + audioController.getSoundVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_GAME_AUDIO: {
                if (audioController.getSoundVolume() != MIN_VOLUME) {
                    audioController.setSoundVolume((float) ((Math.round(audioController.getSoundVolume()*100) - 10))/100);
                    menu.getSettingsView().updateGameAudioText();
                    LOGGER.info("Game volume set to:" + audioController.getSoundVolume() * 100 + "%");
                }
                break;
            }
            case QUIT: {
                quitAction();
            }
        }
    }

    public void action(MenuAction menuAction, ViewPanels viewPanels) {
        switch (menuAction) {
            case SHOW: {
                show(viewPanels);
                break;
            }
            case PLAY_LEVEL: {
                LOGGER.info("Action PLAY_LEVEL called Wrongly");
                break;
            }
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

    /**
     * {@inheritDoc}
     */
    public void hide() {

    }

    public void quitAction() {
        LOGGER.info("Exiting...");
        System.exit(0);
    }


}