package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.Level;

/**
 * Define a button implementation with his level name
 */
public class PlayLevelButton extends ControllerButton {
    private final Level level;

    /**
     * This constructor calls the fathers constructor and adds the levelName variable
     *
     * @param mainMenuController the instance of the controller
     * @param button             define the button type
     * @param level         defines the level name that will be played
     */
    public PlayLevelButton(final MainMenuController mainMenuController, final Buttons button, final Level level) {
        super(mainMenuController, button);

        this.level = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonAction() {
        if(this.getMainMenuController().getPlayer().getLevelScore(level.getJsonId()) != -1 || this.level == Level.FIRST_LEVEL || MainMenuController.isDeveloperModeOn){
            this.getMainMenuController().startLevel(this.level.getFileName());
        }
    }
}
