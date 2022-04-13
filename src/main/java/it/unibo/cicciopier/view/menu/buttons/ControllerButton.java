package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;

/**
 * Define a button implementation with his action
 */
public abstract class ControllerButton extends CustomButton {
    private final MainMenuController mainMenuController;

    /**
     * This constructor calls the fathers constructor and adds the {@link MainMenuController} variable
     *
     * @param mainMenuController the instance of the controller
     * @param button             define the button type
     */
    public ControllerButton(final MainMenuController mainMenuController, final Buttons button) {
        super(button);

        this.mainMenuController = mainMenuController;
    }

    /**
     * Returns the instance of the controller used by this button
     *
     * @return the instance of the controller
     */
    public MainMenuController getMainMenuController() {
        return this.mainMenuController;
    }
}
