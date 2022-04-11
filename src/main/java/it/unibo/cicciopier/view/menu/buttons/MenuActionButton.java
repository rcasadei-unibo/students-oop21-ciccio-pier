package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;

/**
 * Define a button implementation with his {@link MenuAction}
 */
public class MenuActionButton extends ControllerButton {
    private final MenuAction menuAction;

    /**
     * This constructor calls the fathers constructor and adds the {@link MenuAction} variable
     *
     * @param mainMenuController the instance of the controller
     * @param button             define the button type
     * @param menuAction         define the action to be executed
     */
    public MenuActionButton(final MainMenuController mainMenuController, final Buttons button, final MenuAction menuAction) {
        super(mainMenuController, button);

        this.menuAction = menuAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonAction() {
        this.getMainMenuController().action(this.menuAction);
    }
}
