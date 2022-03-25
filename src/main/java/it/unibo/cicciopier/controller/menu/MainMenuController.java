package it.unibo.cicciopier.controller.menu;

import it.unibo.cicciopier.view.menu.LeaderboardView;
import it.unibo.cicciopier.view.menu.LevelSelectionView;
import it.unibo.cicciopier.view.menu.MainMenuView;
import it.unibo.cicciopier.view.menu.SettingsView;

import javax.swing.*;

public class MainMenuController implements MenuController {
    private int volume;
    private  MainMenuView menu;
    private final LeaderboardMenuController leaderboardController;
    private final SettingsMenuController settingsController;
    private final LevelSelectionMenuController levelSelectionController;
    private MenuController active;

    public MainMenuController() {
        this.menu = new MainMenuView(this);
        this.settingsController = new SettingsMenuController(this);
        this.leaderboardController = new LeaderboardMenuController(this);
        this.levelSelectionController = new LevelSelectionMenuController(this);
        this.active = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        active.hide();
        menu.start();
        active = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        menu.hideView();
    }

    public void leaderboardAction() {
        active.hide();
        leaderboardController.show();
        active = leaderboardController;
    }

    public void playAction(){
        active.hide();
        levelSelectionController.show();
        active = levelSelectionController;
    }

    public void settingsAction(){
        active.hide();
        settingsController.show();
        active = settingsController;
    }
    public void quitAction(){
        System.exit(0);
    }


}