package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LeaderboardView extends JPanel {
    private final BufferedImage background;

    public LeaderboardView(MainMenuController mainMenuController) {

        BufferedImage[] bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.SETTINGS_BUTTON.getTexture();
        bufferedImage[1] = Texture.SETTINGS_BUTTON_PRESSED.getTexture();
        bufferedImage[2] = Texture.SETTINGS_BUTTON_HOVER.getTexture();
        CustomButton settings = new CustomButton(mainMenuController,new Dimension(85, 85),bufferedImage, MenuAction.SHOW,true,ViewPanels.SETTINGS);

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.HOME_BUTTON.getTexture();
        bufferedImage[1] = Texture.HOME_BUTTON_PRESSED.getTexture();
        bufferedImage[2] = Texture.HOME_BUTTON_HOVER.getTexture();
        CustomButton home = new CustomButton(mainMenuController,new Dimension(85, 85),bufferedImage, MenuAction.SHOW,true,ViewPanels.MAIN_MENU);


        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LEADERBOARD_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(settings);

        final Dimension sizeSettings = settings.getPreferredSize();
        final int settingsWidthOffset = size.width - sizeSettings.width - 60;
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
