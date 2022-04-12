package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.view.menu.LeaderboardView;

public class LeaderboardLevelSelectionButton extends ControllerButton {
    private final Level level;

    public LeaderboardLevelSelectionButton(final MainMenuController mainMenuController, final Buttons button, final Level level) {
        super(mainMenuController,button);
        this.level = level;
    }

    @Override
    protected void buttonAction() {
        getMainMenuController().getMenu().getLeaderboardView().updateLeaderboard(this.level);
    }
}
