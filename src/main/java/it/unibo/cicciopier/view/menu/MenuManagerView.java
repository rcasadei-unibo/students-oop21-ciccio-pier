package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.ViewPanelButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MenuManagerView extends JFrame implements ManagerView {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuManagerView.class);
    private final MainMenuView mainMenuView;
    private final LevelSelectionView levelSelectionView;
    private final SettingsView settingsView;
    private final LeaderboardView leaderboardView;
    private final LoginView loginView;
    private final TutorialView tutorialView;
    private final MainMenuController controller;
    private final CustomAnimationView customAnimationView;
    private final ViewPanelButton tutorialButton;
    private final ViewPanelButton settingsButton;
    private final ViewPanelButton homeButton;
    private final JLabel loggedUser;
    private MenuPanel activePanel;

    public MenuManagerView(MainMenuController mainMenuController) {
        MenuManagerView.LOGGER.info("Initializing the Main Menu Controller");
        this.setTitle("CICCIO PIER THE GAME!");
        this.tutorialButton = new ViewPanelButton(
                mainMenuController,
                Buttons.TUTORIAL,
                ViewPanels.TUTORIAL
        );
        this.settingsButton = new ViewPanelButton(
                mainMenuController,
                Buttons.SETTINGS,
                ViewPanels.SETTINGS
        );
        this.homeButton = new ViewPanelButton(
                mainMenuController,
                Buttons.HOME,
                ViewPanels.HOME
        );
        this.loggedUser = new JLabel();
        this.controller = mainMenuController;
        this.mainMenuView = new MainMenuView(mainMenuController);
        this.levelSelectionView = new LevelSelectionView(mainMenuController);
        this.settingsView = new SettingsView(mainMenuController);
        this.leaderboardView = new LeaderboardView(mainMenuController);
        this.loginView = new LoginView(mainMenuController);
        this.tutorialView = new TutorialView();
        this.customAnimationView = new CustomAnimationView();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        Screen.setCurrentDimension(Screen.getScreenMaxSize());
        Timer timer = new Timer(1000 / 60, e -> this.updateAnimations());
        timer.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.setPreferredSize(Screen.getCurrentDimension());
        this.customAnimationView.setPreferredSize(new Dimension(this.getPreferredSize().width,  Screen.scale((384 / 1.75))));
        this.loggedUser.setFont(CustomFont.getInstance().getFontOrDefault());
        this.loggedUser.setText("Logged user: " + controller.getUsername());
        this.loggedUser.setForeground(Color.WHITE);
        final Pair<Integer> settingsPos = new Pair<>(
                this.getPreferredSize().width - this.settingsButton.getPreferredSize().width - this.getPreferredSize().width / 25,
                (int) (this.getPreferredSize().height / 38.4)
        );
        final Pair<Integer> homePos = new Pair<>(
                this.getPreferredSize().width / 25,
                (int) (this.getPreferredSize().height / 38.4)
        );
        final Pair<Integer> tutorialPos = new Pair<>(
                this.getPreferredSize().width - this.settingsButton.getPreferredSize().width - this.getPreferredSize().width / 25,
                (int) (this.getPreferredSize().height - this.getPreferredSize().height / 38.4 - this.tutorialButton.getPreferredSize().height)
        );
        final Pair<Integer> loggedUserPos = new Pair<>(
                this.getPreferredSize().width / 25,
                (int) (this.getPreferredSize().height / 1.01 - this.loggedUser.getPreferredSize().height)
        );
        this.settingsButton.setBounds(
                settingsPos.getX(),
                settingsPos.getY(),
                this.settingsButton.getPreferredSize().width,
                this.settingsButton.getPreferredSize().height
        );
        this.homeButton.setBounds(
                homePos.getX(),
                homePos.getY(),
                this.homeButton.getPreferredSize().width,
                this.homeButton.getPreferredSize().height
        );
        this.tutorialButton.setBounds(
                tutorialPos.getX(),
                tutorialPos.getY(),
                this.tutorialButton.getPreferredSize().width,
                this.tutorialButton.getPreferredSize().height
        );
        this.loggedUser.setBounds(
                loggedUserPos.getX(),
                loggedUserPos.getY(),
                this.loggedUser.getPreferredSize().width,
                this.loggedUser.getPreferredSize().height
        );
        this.customAnimationView.setBounds(
                0,
                this.getPreferredSize().height - this.customAnimationView.getPreferredSize().height,
                this.customAnimationView.getPreferredSize().width,
                this.customAnimationView.getPreferredSize().height
        );
        this.repaint();
    }


    public void setVisible(ViewPanels viewPanels) {
        this.getContentPane().removeAll();
        if (this.activePanel == this.settingsView && !Objects.equals(this.settingsView.getList().getSelectedValue(), Screen.getCurrentDimension()) && viewPanels != ViewPanels.LOGIN) {
            this.controller.action(MenuAction.CHANGE_RESOLUTION);
            this.settingsView.getList().setSelectedValue(Screen.getCurrentDimension(), true);
        }
        switch (viewPanels) {
            case LEVEL_SELECTION: {
                this.levelSelectionView.add(this.homeButton);
                this.levelSelectionView.add(this.settingsButton);
                this.levelSelectionView.add(this.tutorialButton);
                this.levelSelectionView.add(this.loggedUser);
                this.levelSelectionView.add(this.customAnimationView);
                this.getContentPane().add(this.levelSelectionView);
                this.activePanel = this.levelSelectionView;
                break;
            }
            case HOME: {
                this.mainMenuView.add(this.settingsButton);
                this.mainMenuView.add(this.loggedUser);
                this.mainMenuView.add(this.tutorialButton);
                this.mainMenuView.add(this.customAnimationView);
                this.getContentPane().add(this.mainMenuView);
                this.activePanel = this.mainMenuView;
                break;
            }
            case LOGIN: {
                this.loginView.add(this.customAnimationView);
                this.getContentPane().add(this.loginView);
                this.activePanel = this.loginView;
                break;
            }
            case SETTINGS: {
                this.settingsView.add(this.homeButton);
                this.settingsView.add(this.loggedUser);
                this.settingsView.add(this.tutorialButton);
                this.settingsView.add(this.customAnimationView);
                this.getContentPane().add(this.settingsView);
                this.activePanel = this.settingsView;
                break;
            }
            case LEADERBOARD: {
                this.leaderboardView.add(this.homeButton);
                this.leaderboardView.add(this.settingsButton);
                this.leaderboardView.add(this.tutorialButton);
                this.leaderboardView.add(this.loggedUser);
                this.leaderboardView.add(this.customAnimationView);
                this.getContentPane().add(this.leaderboardView);
                this.activePanel = this.leaderboardView;
                this.leaderboardView.updateLeaderboard(Level.FIRST_LEVEL);
                break;
            }
            case TUTORIAL:{
                this.tutorialView.add(this.homeButton);
                this.tutorialView.add(this.settingsButton);
                this.tutorialView.add(this.loggedUser);
                this.getContentPane().add(this.tutorialView);
            }

        }
        assert this.activePanel != null;
        this.activePanel.update();
        this.update();
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
        this.customAnimationView.repaint();
    }
}
