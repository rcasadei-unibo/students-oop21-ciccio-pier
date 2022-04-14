package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuView.class);
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;
    private final CustomAnimationView animationView;
    private final CustomButton settings;
    private final CustomButton play;
    private final CustomButton leaderboard;
    private final CustomButton quit;


    public MainMenuView(MainMenuController mainMenuController) {
        MainMenuView.LOGGER.info("Initializing the class...");
        this.mainMenuController = mainMenuController;
        this.loggedUser = new JLabel();
        this.animationView = new CustomAnimationView();
        this.settings = new ViewPanelButton(this.mainMenuController, Buttons.SETTINGS, ViewPanels.SETTINGS);
        this.play = new ViewPanelButton(this.mainMenuController, Buttons.PLAY, ViewPanels.LEVEL_SELECTION);
        this.leaderboard = new ViewPanelButton(this.mainMenuController, Buttons.LEADERBOARD, ViewPanels.LEADERBOARD);
        this.quit = new MenuActionButton(this.mainMenuController, Buttons.QUIT, MenuAction.QUIT);

        this.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Texture.MENU_BACKGROUND.getTexture(), 0, 0, Screen.getCurrentDimension().width, Screen.getCurrentDimension().height, null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLoggedUser() {
        this.loggedUser.setText(("Logged user: " + this.mainMenuController.getUsername()));
        this.loggedUser.setBounds(this.loggedUser.getBounds().x, this.loggedUser.getBounds().y,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimations() {
        this.animationView.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        MainMenuView.LOGGER.info("Updating the class...");
        this.setPreferredSize(Screen.getCurrentDimension());
        this.loggedUser.setFont(CustomFont.getInstance().getFontOrDefault());
        this.updateLoggedUser();
        this.animationView.setPreferredSize(new Dimension(this.getPreferredSize().width, (int) ((384 / 1.75) * Screen.getScale())));

        final int buttonSpacing = (int) (play.getPreferredSize().height + this.getPreferredSize().height / 51.2);

        final int settingsWidth = this.getPreferredSize().width - settings.getPreferredSize().width - this.getPreferredSize().width / 25;

        final int settingsHeightOffset = (int) (this.getPreferredSize().height / 38.4);

        final Pair<Integer> playPos = new Pair<>(this.getPreferredSize().width / 2 - play.getPreferredSize().width / 2,
                (int) (this.getPreferredSize().height / 2.38));

        final Pair<Integer> leaderboardPos = new Pair<>(this.getPreferredSize().width / 2 - play.getPreferredSize().width / 2,
                (int) (this.getPreferredSize().height / 2.38 + buttonSpacing));

        final Pair<Integer> quitPos = new Pair<>(this.getPreferredSize().width / 2 - play.getPreferredSize().width / 2,
                (int) (this.getPreferredSize().height / 2.38 + buttonSpacing * 2));

        final Pair<Integer> loggedUserPos = new Pair<>(this.getPreferredSize().width / 25,
                (int) (this.getPreferredSize().height / 1.01 - this.loggedUser.getPreferredSize().height));

        this.animationView.setBounds(0, this.getPreferredSize().height - this.animationView.getPreferredSize().height, this.animationView.getPreferredSize().width, this.animationView.getPreferredSize().height);

        this.play.setBounds(playPos.getX(), playPos.getY(), play.getPreferredSize().width, play.getPreferredSize().height);

        this.leaderboard.setBounds(leaderboardPos.getX(), leaderboardPos.getY(), leaderboard.getPreferredSize().width,
                leaderboard.getPreferredSize().height);

        this.quit.setBounds(quitPos.getX(), quitPos.getY(), quit.getPreferredSize().width, quit.getPreferredSize().height);

        this.settings.setBounds(settingsWidth, settingsHeightOffset, settings.getPreferredSize().width,
                settings.getPreferredSize().height);

        this.loggedUser.setBounds(loggedUserPos.getX(), loggedUserPos.getY(), this.loggedUser.getPreferredSize().width,
                this.loggedUser.getPreferredSize().height);

        this.repaint();
    }

    @Override
    public void load() {
        MainMenuView.LOGGER.info("Loading the class...");
        this.loggedUser.setForeground(Color.WHITE);

        this.setLayout(null);
        this.add(this.play);
        this.add(this.leaderboard);
        this.add(this.settings);
        this.add(this.quit);
        this.add(this.loggedUser);
        this.add(this.animationView);

    }
}
