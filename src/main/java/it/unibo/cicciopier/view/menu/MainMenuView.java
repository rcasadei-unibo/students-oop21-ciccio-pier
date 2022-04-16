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

/**
 * This class represents an instance of the main menu view
 */
public class MainMenuView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuView.class);
    private final JLabel loggedUser;
    private final CustomAnimationView animationView;
    private final CustomButton settings;
    private final CustomButton play;
    private final CustomButton leaderboard;
    private final CustomButton quit;

    /**
     * This constructor creates the whole panel with his components
     *
     * @param mainMenuController the instance of the {@link MainMenuController}
     */
    public MainMenuView(final MainMenuController mainMenuController) {
        MainMenuView.LOGGER.info("Initializing the class...");
        this.loggedUser = new JLabel();
        this.animationView = new CustomAnimationView();
        this.settings = new ViewPanelButton(
                mainMenuController,
                Buttons.SETTINGS,
                ViewPanels.SETTINGS
        );
        this.play = new ViewPanelButton(
                mainMenuController,
                Buttons.PLAY,
                ViewPanels.LEVEL_SELECTION
        );
        this.leaderboard = new ViewPanelButton(
                mainMenuController,
                Buttons.LEADERBOARD,
                ViewPanels.LEADERBOARD
        );
        this.quit = new MenuActionButton(
                mainMenuController,
                Buttons.QUIT,
                MenuAction.QUIT
        );
        this.load();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        MainMenuView.LOGGER.info("Updating the class...");
        this.setPreferredSize(Screen.getCurrentDimension());
        this.loggedUser.setFont(CustomFont.getInstance().getFontOrDefault());

        final int buttonSpacing = (int) (play.getPreferredSize().height + this.getPreferredSize().height / 51.2);
        final Pair<Integer> playPos = new Pair<>(
                this.getPreferredSize().width / 2 - play.getPreferredSize().width / 2,
                (int) (this.getPreferredSize().height / 2.38)
        );
        final Pair<Integer> leaderboardPos = new Pair<>(
                this.getPreferredSize().width / 2 - play.getPreferredSize().width / 2,
                (int) (this.getPreferredSize().height / 2.38 + buttonSpacing)
        );
        final Pair<Integer> quitPos = new Pair<>(
                this.getPreferredSize().width / 2 - play.getPreferredSize().width / 2,
                (int) (this.getPreferredSize().height / 2.38 + buttonSpacing * 2)
        );
        final Pair<Integer> loggedUserPos = new Pair<>(
                this.getPreferredSize().width / 25,
                (int) (this.getPreferredSize().height / 1.01 - this.loggedUser.getPreferredSize().height)
        );
        this.play.setBounds(
                playPos.getX(),
                playPos.getY(),
                this.play.getPreferredSize().width,
                this.play.getPreferredSize().height
        );
        this.leaderboard.setBounds(
                leaderboardPos.getX(),
                leaderboardPos.getY(),
                this.leaderboard.getPreferredSize().width,
                this.leaderboard.getPreferredSize().height
        );
        this.quit.setBounds(
                quitPos.getX(),
                quitPos.getY(),
                this.quit.getPreferredSize().width,
                this.quit.getPreferredSize().height
        );
        this.loggedUser.setBounds(
                loggedUserPos.getX(),
                loggedUserPos.getY(),
                this.loggedUser.getPreferredSize().width,
                this.loggedUser.getPreferredSize().height
        );
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(
                Texture.MENU_BACKGROUND.getTexture(),
                0,
                0,
                Screen.getCurrentDimension().width,
                Screen.getCurrentDimension().height,
                null);

    }
}
