package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelSelectionView extends JPanel {
    private final BufferedImage background;

    public LevelSelectionView(MainMenuController mainMenuController) {

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

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.LEVEL_BUTTON_1.getTexture();
        bufferedImage[1] = Texture.LEVEL_BUTTON_1_PRESSED.getTexture();
        CustomButton level1 = new CustomButton(mainMenuController,new Dimension(90, 90),bufferedImage, MenuAction.PLAY_LEVEL,false);

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.LEVEL_BUTTON_2.getTexture();
        bufferedImage[1] = Texture.LEVEL_BUTTON_2_PRESSED.getTexture();
        CustomButton level2 = new CustomButton(mainMenuController,new Dimension(90, 90),bufferedImage, MenuAction.PLAY_LEVEL,false);

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.LEVEL_BUTTON_3.getTexture();
        bufferedImage[1] = Texture.LEVEL_BUTTON_3_PRESSED.getTexture();
        CustomButton level3 = new CustomButton(mainMenuController,new Dimension(90, 90),bufferedImage, MenuAction.PLAY_LEVEL,false);

        bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.LEVEL_BUTTON_BOSS.getTexture();
        bufferedImage[1] = Texture.LEVEL_BUTTON_BOSS_PRESSED.getTexture();
        CustomButton levelBoss = new CustomButton(mainMenuController,new Dimension(90, 90),bufferedImage, MenuAction.PLAY_LEVEL,false);


        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LEVEL_SELECTION_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(settings);
        this.add(level1);
        this.add(level2);
        this.add(level3);
        this.add(levelBoss);

        final Dimension sizeSettings = settings.getPreferredSize();
        final int settingsWidthOffset = size.width - sizeSettings.width - 60;
        final int homeWidthOffset = 60;
        final Dimension levelButtonSize = level1.getPreferredSize();
        final int settingsHeightOffset = 20;

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        level1.setBounds(445, 600, levelButtonSize.width, levelButtonSize.height);
        level2.setBounds(605, 390, levelButtonSize.width, levelButtonSize.height);
        level3.setBounds(880, 390, levelButtonSize.width, levelButtonSize.height);
        levelBoss.setBounds(1030, 610, levelButtonSize.width, levelButtonSize.height);



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
