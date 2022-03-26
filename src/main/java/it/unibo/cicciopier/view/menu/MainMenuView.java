package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.LeaderboardButton;
import it.unibo.cicciopier.view.menu.buttons.PlayButton;
import it.unibo.cicciopier.view.menu.buttons.QuitButton;
import it.unibo.cicciopier.view.menu.buttons.SettingsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenuView extends JPanel {
    private static final int BUTTON_HEIGHT = 322;
    private static final int BUTTON_SPACE = 15;
    private final BufferedImage background;


    public MainMenuView(MainMenuController mainMenuController) {

        PlayButton play = new PlayButton(mainMenuController);
        LeaderboardButton leaderboard = new LeaderboardButton(mainMenuController);
        SettingsButton settings = new SettingsButton(mainMenuController);
        QuitButton quit = new QuitButton(mainMenuController);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        background = Texture.MENU_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(play);
        this.add(leaderboard);
        this.add(settings);
        this.add(quit);

        final Dimension sizeButton = play.getPreferredSize();
        final Dimension sizeSettings = settings.getPreferredSize();
        final int width = size.width / 2 - sizeButton.width / 2;
        final int spacing = sizeButton.height + BUTTON_SPACE;
        final int settingsWidth = size.width - sizeSettings.width - 60;
        final int settingsOffset = 20;

        play.setBounds(width, BUTTON_HEIGHT, sizeButton.width, sizeButton.height);

        leaderboard.setBounds(width, BUTTON_HEIGHT + spacing, sizeButton.width, sizeButton.height);

        quit.setBounds(width, BUTTON_HEIGHT + spacing * 2, sizeButton.width, sizeButton.height);

        settings.setBounds(settingsWidth, settingsOffset, sizeSettings.width, sizeSettings.height);

        this.repaint();


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
