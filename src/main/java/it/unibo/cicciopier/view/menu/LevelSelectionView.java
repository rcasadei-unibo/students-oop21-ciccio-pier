package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.HomeButton;
import it.unibo.cicciopier.view.menu.buttons.LevelButton;
import it.unibo.cicciopier.view.menu.buttons.SettingsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelSelectionView extends JPanel {
    private final BufferedImage background;

    public LevelSelectionView(MainMenuController mainMenuController) {

        SettingsButton settings = new SettingsButton(mainMenuController);
        HomeButton home = new HomeButton(mainMenuController);
        LevelButton[] levels = new LevelButton[4];
        levels[0] = new LevelButton(Texture.LEVEL_BUTTON_1, Texture.LEVEL_BUTTON_1_PRESSED);
        levels[1] = new LevelButton(Texture.LEVEL_BUTTON_2, Texture.LEVEL_BUTTON_2_PRESSED);
        levels[2] = new LevelButton(Texture.LEVEL_BUTTON_3, Texture.LEVEL_BUTTON_3_PRESSED);
        levels[3] = new LevelButton(Texture.LEVEL_BUTTON_BOSS, Texture.LEVEL_BUTTON_BOSS_PRESSED);


        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LEVEL_SELECTION_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(settings);
        for (LevelButton level : levels) {
            this.add(level);
        }

        final Dimension sizeSettings = settings.getPreferredSize();
        final int settingsWidthOffset = size.width - sizeSettings.width - 60;
        final int homeWidthOffset = 60;
        final Dimension levelButtonSize = levels[0].getPreferredSize();
        final int settingsHeightOffset = 20;

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        levels[0].setBounds(445, 600, levelButtonSize.width, levelButtonSize.height);
        levels[1].setBounds(605, 390, levelButtonSize.width, levelButtonSize.height);
        levels[2].setBounds(880, 390, levelButtonSize.width, levelButtonSize.height);
        levels[3].setBounds(1030, 610, levelButtonSize.width, levelButtonSize.height);



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
