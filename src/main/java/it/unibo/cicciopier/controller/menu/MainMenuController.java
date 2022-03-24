package it.unibo.cicciopier.controller.menu;

import it.unibo.cicciopier.view.menu.MainMenuView;

import javax.swing.*;

public class MainMenuController implements MenuController {
    private int volume;
    private  MainMenuView menu;

    public MainMenuController() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        //TODO load volume settings from json
        menu = new MainMenuView();
        menu.load();
        menu.start();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLeaderboard() {
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
   public void exit(){
        System.exit(0);
    }
}