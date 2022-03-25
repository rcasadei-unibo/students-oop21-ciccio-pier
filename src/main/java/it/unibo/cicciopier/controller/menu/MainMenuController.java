package it.unibo.cicciopier.controller.menu;

import it.unibo.cicciopier.view.menu.LeaderboardView;
import it.unibo.cicciopier.view.menu.LevelSelectionView;
import it.unibo.cicciopier.view.menu.MainMenuView;
import it.unibo.cicciopier.view.menu.SettingsView;

import javax.swing.*;

public class MainMenuController implements MenuController {
    private int volume;
    private  MainMenuView menu;
    private LeaderboardMenuController leaderboardController;
    private SettingsMenuController settingsController;
    private LevelSelectionMenuController levelSelectionController;

    public MainMenuController() {
        menu = new MainMenuView(this);
        settingsController = new SettingsMenuController(this);
        leaderboardController = new LeaderboardMenuController(this);
        levelSelectionController = new LevelSelectionMenuController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        menu.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        menu.hideView();
    }

    public void leaderboardAction() {
        leaderboardController.show();
        this.hide();
    }

    public void playAction(){
        levelSelectionController.show();
        this.hide();
    }

    public void settingsAction(){
        settingsController.show();
        this.hide();
    }
    public void quitAction(){
        System.exit(0);
    }


}