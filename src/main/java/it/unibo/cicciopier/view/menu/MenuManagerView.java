package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.StaticView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Objects;

public class MenuManagerView extends JFrame implements StaticView {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuManagerView.class);
    private final MainMenuView mainMenuView;
    private final LevelSelectionView levelSelectionView;
    private final SettingsView settingsView;
    private final LeaderboardView leaderboardView;
    private final LoginView loginView;
    private final MainMenuController controller;
    private MenuPanel activePanel;

    public MenuManagerView(MainMenuController mainMenuController) {
        this.setTitle("CICCIO PIER THE GAME!");
        this.controller = mainMenuController;
        this.mainMenuView = new MainMenuView(mainMenuController);
        this.levelSelectionView = new LevelSelectionView(mainMenuController);
        this.settingsView = new SettingsView(mainMenuController);
        this.leaderboardView = new LeaderboardView(mainMenuController);
        this.loginView = new LoginView(mainMenuController);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        Screen.setCurrentDimension(Screen.getScreenMaxSize());
    }


    @Override
    public void start() {

    }

    public void setVisible(ViewPanels viewPanels) {
        this.getContentPane().removeAll();
        if (this.activePanel == this.settingsView && !Objects.equals(this.settingsView.getList().getSelectedValue(), Screen.getCurrentDimension()) && viewPanels != ViewPanels.LOGIN) {
            this.controller.action(MenuAction.CHANGE_RESOLUTION);
            this.settingsView.getList().setSelectedValue(Screen.getCurrentDimension(), true);
        }
        switch (viewPanels) {
            case LEVEL_SELECTION: {
                this.getContentPane().add(this.levelSelectionView);
                this.activePanel = this.levelSelectionView;
                break;
            }
            case MAIN_MENU: {
                this.getContentPane().add(this.mainMenuView);
                this.activePanel = this.mainMenuView;
                break;
            }
            case LOGIN: {
                this.getContentPane().add(this.loginView);
                this.activePanel = this.loginView;
                break;
            }
            case SETTINGS: {
                this.getContentPane().add(this.settingsView);
                this.activePanel = this.settingsView;
                break;
            }
            case LEADERBOARD: {
                this.getContentPane().add(this.leaderboardView);
                this.activePanel = this.leaderboardView;
                this.leaderboardView.updateLeaderboard(Level.FIRST_LEVEL);
                break;
            }
        }
        this.activePanel.update();
        this.pack();
        this.setLocationRelativeTo(null);
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

    public void updateAnimations() {
        this.activePanel.updateAnimations();
    }
}
