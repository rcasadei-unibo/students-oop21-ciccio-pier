package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.view.menu.LeaderboardView;

/**
 * This class is instanced to create a new button used to change {@link LeaderboardView} to show a specific
 * level leaderboard
 */
public class LeaderboardLevelSelectionButton extends ControllerButton {
    private final Level level;

    /**
     * This constructor generates a new button used in the {@link LeaderboardView}
     *
     * @param mainMenuController The instance of the main menu controller
     * @param button             The {@link Buttons} that you want to create
     * @param level              The {@link Level} witch you want to show the leaderboard of
     */
    public LeaderboardLevelSelectionButton(final MainMenuController mainMenuController, final Buttons button, final Level level) {
        super(mainMenuController, button);
        this.level = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonAction() {
        getMainMenuController().getMenu().getLeaderboardView().updateLeaderboard(this.level);
    }
}
