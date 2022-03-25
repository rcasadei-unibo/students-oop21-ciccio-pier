package it.unibo.cicciopier.controller.menu;

import it.unibo.cicciopier.view.menu.LeaderboardView;

public class LeaderboardMenuController implements MenuController{
    private final MainMenuController menuController;
    private final LeaderboardView leaderboardView;

    LeaderboardMenuController(MainMenuController mainMenuController){
        this.menuController = mainMenuController;
        this.leaderboardView = new LeaderboardView();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
