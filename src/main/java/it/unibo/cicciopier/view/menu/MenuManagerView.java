package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.DeveloperMode;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.view.StaticView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class MenuManagerView extends JFrame implements StaticView {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuManagerView.class);
    private final MainMenuView mainMenuView;
    private final LevelSelectionView levelSelectionView;
    private final SettingsView settingsView;
    private final LeaderboardView leaderboardView;
    private final LoginView loginView;

    public MenuManagerView(MainMenuController mainMenuController) {
        this.setTitle("CICCIO PIER THE GAME!");

        this.mainMenuView = new MainMenuView(mainMenuController);
        this.levelSelectionView = new LevelSelectionView(mainMenuController);
        this.settingsView = new SettingsView(mainMenuController);
        this.leaderboardView = new LeaderboardView(mainMenuController);
        this.loginView = new LoginView(mainMenuController);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }


    @Override
    public void start() {

    }

    public void setVisible(ViewPanels viewPanels) {
        this.getContentPane().removeAll();
        switch (viewPanels) {
            case LEVEL_SELECTION: {
                levelSelectionView.updateLoggedUser();
                this.getContentPane().add(levelSelectionView);
                levelSelectionView.repaint();
                break;
            }
            case MAIN_MENU: {
                mainMenuView.updateLoggedUser();
                this.getContentPane().add(mainMenuView);
                mainMenuView.repaint();
                break;
            }
            case LOGIN: {
                this.getContentPane().add(loginView);
                loginView.repaint();
                break;
            }
            case SETTINGS: {
                settingsView.updateLoggedUser();
                this.getContentPane().add(settingsView);
                settingsView.repaint();
                break;
            }
            case LEADERBOARD: {
                leaderboardView.updateLoggedUser();
                this.getContentPane().add(leaderboardView);
                leaderboardView.updateLeaderboard(Level.FIRST_LEVEL);
                leaderboardView.repaint();
                break;
            }
        }
        this.pack();
    }

    public SettingsView getSettingsView() {
        return settingsView;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LeaderboardView getLeaderboardView() {
        return leaderboardView;
    }
}
