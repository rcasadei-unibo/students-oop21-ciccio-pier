package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenuView extends JPanel {
    private static final int BUTTON_HEIGHT = 322;
    private static final int BUTTON_SPACE = 15;
    private final BufferedImage background;


    public MainMenuView(MainMenuController mainMenuController) {

        BufferedImage[] bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.SETTINGS_BUTTON.getTexture();
        bufferedImage[1] = Texture.SETTINGS_BUTTON_PRESSED.getTexture();
        bufferedImage[2] = Texture.SETTINGS_BUTTON_HOVER.getTexture();
        CustomButton settings = new CustomButton(mainMenuController,new Dimension(85, 85),bufferedImage, MenuAction.SHOW,true,ViewPanels.SETTINGS);

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.PLAY_BUTTON.getTexture();
        bufferedImage[1] = Texture.PLAY_BUTTON_PRESSED.getTexture();
        CustomButton play = new CustomButton(mainMenuController,new Dimension(280, 106),bufferedImage, MenuAction.SHOW,false,ViewPanels.LEVEL_SELECTION);

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.LEADERBOARD_BUTTON.getTexture();
        bufferedImage[1] = Texture.LEADERBOARD_BUTTON_PRESSED.getTexture();
        CustomButton leaderboard = new CustomButton(mainMenuController,new Dimension(280, 106),bufferedImage, MenuAction.SHOW,false,ViewPanels.LEADERBOARD);

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.QUIT_BUTTON.getTexture();
        bufferedImage[1] = Texture.QUIT_BUTTON_PRESSED.getTexture();
        CustomButton quit = new CustomButton(mainMenuController,new Dimension(280, 106),bufferedImage, MenuAction.QUIT,false);

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
