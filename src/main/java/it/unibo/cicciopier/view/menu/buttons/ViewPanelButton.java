package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.ViewPanels;

/**
 * Define a button implementation with his {@link ViewPanels}
 */
public class ViewPanelButton extends ControllerButton {
    private final ViewPanels viewPanels;

    /**
     * This constructor calls the fathers constructor and adds the {@link ViewPanels} variable
     *
     * @param mainMenuController the instance of the controller
     * @param button             define the button type
     * @param viewPanels         define the panel to show
     */
    public ViewPanelButton(final MainMenuController mainMenuController, final Buttons button, final ViewPanels viewPanels) {
        super(mainMenuController, button);

        this.viewPanels = viewPanels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonAction() {
        this.getMainMenuController().show(this.viewPanels);
    }
}
