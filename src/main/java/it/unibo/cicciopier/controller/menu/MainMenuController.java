package it.unibo.cicciopier.controller.menu;

import javax.swing.*;

public class MainMenuController implements MenuController {
    private static final String TITLE = "Ciccio Pier THE GAME";
    private MenuPanel currentLayout;
    private int volume;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        //TODO load volume settings from json
        //TODO load view of the MAIN_MENU
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showLeaderboard(){
        //TODO load leaderboard json
        //TODO call leaderboard view init
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMainMenu() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSettings() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGamesSelection() {

    }
}