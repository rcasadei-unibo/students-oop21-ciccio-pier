package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;

/**
 * Define a button implementation with his level name
 */
public class PlayLevelButton extends ControllerButton {
    private final String levelName;

    /**
     * This constructor calls the fathers constructor and adds the levelName variable
     *
     * @param mainMenuController the instance of the controller
     * @param button             define the button type
     * @param levelName          defines the level name that will be played
     */
    public PlayLevelButton(final MainMenuController mainMenuController, final Buttons button, final String levelName) {
        super(mainMenuController, button);

        this.levelName = levelName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonAction() {
        this.getMainMenuController().startLevel(this.levelName);
    }
}
