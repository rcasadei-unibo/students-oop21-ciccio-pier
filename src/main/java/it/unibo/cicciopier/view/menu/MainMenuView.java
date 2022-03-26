package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.view.StaticView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.LeaderboardButton;
import it.unibo.cicciopier.view.menu.buttons.PlayButton;
import it.unibo.cicciopier.view.menu.buttons.QuitButton;
import it.unibo.cicciopier.view.menu.buttons.SettingsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenuView extends JPanel {
    private static final int BUTTONHEIGHT = 322;
    private static final int BUTTONSPACE = 15;
    private BufferedImage background;
    private final PlayButton play;
    private final LeaderboardButton leaderboard;
    private final SettingsButton settings;
    private final QuitButton quit;
    private final Dimension size;


    public MainMenuView(MainMenuController mainMenuController) {

        this.play = new PlayButton(mainMenuController);
        this.leaderboard = new LeaderboardButton(mainMenuController);
        this.settings = new SettingsButton(mainMenuController);
        this.quit = new QuitButton(mainMenuController);

        size = new Dimension(1536, 768);
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
        final int spacing = sizeButton.height + BUTTONSPACE;
        final int settingsWidth = size.width - sizeSettings.width - 60;
        final int settingsOffset = 20;

        play.setBounds(width, BUTTONHEIGHT, sizeButton.width, sizeButton.height);

        leaderboard.setBounds(width, BUTTONHEIGHT + spacing, sizeButton.width, sizeButton.height);

        quit.setBounds(width, BUTTONHEIGHT + spacing * 2, sizeButton.width, sizeButton.height);

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
