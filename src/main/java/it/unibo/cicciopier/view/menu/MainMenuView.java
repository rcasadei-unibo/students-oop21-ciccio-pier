package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.*;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;


    public MainMenuView(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;

        this.loggedUser = new JLabel("Logged user: " + this.mainMenuController.getUsername());
        this.loggedUser.setForeground(Color.WHITE);

        this.loggedUser.setFont(loggedUser.getFont().deriveFont(Font.BOLD, 20));

        CustomButton settings = new ViewPanelButton(this.mainMenuController, Buttons.SETTINGS, ViewPanels.SETTINGS);

        CustomButton play = new ViewPanelButton(this.mainMenuController, Buttons.PLAY, ViewPanels.LEVEL_SELECTION);

        CustomButton leaderboard = new ViewPanelButton(this.mainMenuController, Buttons.LEADERBOARD, ViewPanels.LEADERBOARD);

        CustomButton quit = new MenuActionButton(this.mainMenuController, Buttons.QUIT, MenuAction.QUIT);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);


        this.setLayout(null);
        this.add(play);
        this.add(leaderboard);
        this.add(settings);
        this.add(quit);
        this.add(loggedUser);

        final int buttonSpacing = (int) (play.getPreferredSize().height + size.height / 51.2);

        final int settingsWidth = (int) (size.width - settings.getPreferredSize().width - size.width / 25.6);

        final int settingsHeightOffset = (int) (size.height / 38.4);

        final Pair<Integer> playPos = new Pair<>(size.width / 2 - play.getPreferredSize().width / 2,
                (int) (size.height / 2.38));

        final Pair<Integer> leaderboardPos = new Pair<>(size.width / 2 - play.getPreferredSize().width / 2,
                (int) (size.height / 2.38 + buttonSpacing));

        final Pair<Integer> quitPos = new Pair<>(size.width / 2 - play.getPreferredSize().width / 2,
                (int) (size.height / 2.38 + buttonSpacing * 2));

        final Pair<Integer> loggedUserPos = new Pair<>((int) (size.width/25.6), settingsHeightOffset);

        play.setBounds(playPos.getX(), playPos.getY(), play.getPreferredSize().width, play.getPreferredSize().height);

        leaderboard.setBounds(leaderboardPos.getX(), leaderboardPos.getY(), leaderboard.getPreferredSize().width,
                leaderboard.getPreferredSize().height);

        quit.setBounds(quitPos.getX(), quitPos.getY(), quit.getPreferredSize().width, quit.getPreferredSize().height);

        settings.setBounds(settingsWidth, settingsHeightOffset, settings.getPreferredSize().width,
                settings.getPreferredSize().height);

        this.loggedUser.setBounds(loggedUserPos.getX(), loggedUserPos.getY(), this.loggedUser.getPreferredSize().width,
                this.loggedUser.getPreferredSize().height);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Texture.MENU_BACKGROUND.getTexture(), 0, 0, null);
    }

    public void updateLoggedUser() {
        loggedUser.setText(("Logged user: " + mainMenuController.getUsername()));
        this.loggedUser.setBounds(loggedUser.getBounds().x,loggedUser.getBounds().y,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);
    }
}
