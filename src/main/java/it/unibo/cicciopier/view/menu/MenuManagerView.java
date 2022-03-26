package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.view.StaticView;
import it.unibo.cicciopier.view.View;

import javax.swing.*;
import java.awt.*;

public class MenuManagerView extends JFrame implements StaticView {
    private final MainMenuController mainMenuController;
    private final MainMenuView mainMenuView;
    private final LevelSelectionView levelSelectionView;
    private final SettingsView settingsView;
    private final LeaderboardView leaderboardView;
    private final LoginView loginView;

    public MenuManagerView(MainMenuController mainMenuController) {
        this.setName("CICCIO PIER THE GAME!");
        this.mainMenuController = mainMenuController;

        this.mainMenuView = new MainMenuView(mainMenuController);
        this.levelSelectionView = new LevelSelectionView(mainMenuController);
        this.settingsView = new SettingsView();
        this.leaderboardView = new LeaderboardView(mainMenuController);
        this.loginView = new LoginView();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }


    @Override
    public void start() {

    }

    public void setVisible(ViewPanels viewPanels){
        this.getContentPane().removeAll();
        switch (viewPanels){
            case LEVEL_SELECTION:{
                this.getContentPane().add(levelSelectionView);
                levelSelectionView.repaint();
                break;
            }
            case MAIN_MENU:{
                this.getContentPane().add(mainMenuView);
                mainMenuView.repaint();
                break;
            }
            case LOGIN:{
                this.getContentPane().add(loginView);
                loginView.repaint();
                break;
            }
            case  SETTINGS:{
                this.getContentPane().add(settingsView);
                settingsView.repaint();
                break;
            }
            case LEADERBOARD:{
                this.getContentPane().add(leaderboardView);
                leaderboardView.repaint();
                break;
            }
        }
        this.pack();
    }
}
