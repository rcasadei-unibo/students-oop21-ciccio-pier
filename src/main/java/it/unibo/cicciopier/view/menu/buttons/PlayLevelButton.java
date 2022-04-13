package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.Level;

import java.awt.*;

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
     * @param level              defines the level name that will be played
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
        if (MainMenuController.isDeveloperModeOn() || this.level.getPrevLevel().isEmpty() || this.getMainMenuController().getPlayer().getLevelScore(this.level.getPrevLevel().get().getJsonId()) != -1) {
            this.getMainMenuController().startLevel(this.level);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        this.level.getPrevLevel().ifPresent(l -> {
            if (!MainMenuController.isDeveloperModeOn() && this.getMainMenuController().getPlayer().getLevelScore(l.getJsonId()) == -1) {
                this.setButtonStatus(1);
            }
        });
        super.paintComponent(g);
    }

}
