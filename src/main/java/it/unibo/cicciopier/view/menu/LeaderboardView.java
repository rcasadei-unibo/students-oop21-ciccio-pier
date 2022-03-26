package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.view.StaticView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.View;
import it.unibo.cicciopier.view.menu.buttons.HomeButton;
import it.unibo.cicciopier.view.menu.buttons.LevelButton;
import it.unibo.cicciopier.view.menu.buttons.SettingsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LeaderboardView extends JPanel {
    private final Dimension size;
    private final BufferedImage background;
    private final SettingsButton settings;
    private final HomeButton home;

    public LeaderboardView(MainMenuController mainMenuController) {

        this.settings = new SettingsButton(mainMenuController);
        this.home = new HomeButton(mainMenuController);


        this.size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LEADERBOARD_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(settings);

        final Dimension sizeSettings = settings.getPreferredSize();
        final int settingsWidthOffset = this.size.width - sizeSettings.width - 60;
        final int homeWidthOffset = 60;
        final int settingsHeightOffset = 20;

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);




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
