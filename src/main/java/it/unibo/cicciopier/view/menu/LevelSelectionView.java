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

public class LevelSelectionView extends JPanel implements StaticView {
    private final JFrame jframe;
    private final Dimension size;
    private final BufferedImage background;
    private final SettingsButton settings;
    private final HomeButton home;
    private final LevelButton[] levels;

    public LevelSelectionView(MainMenuController mainMenuController) {
        jframe = new JFrame("CICCIO PIER THE GAME");

        this.settings = new SettingsButton(mainMenuController);
        this.home = new HomeButton(mainMenuController);
        this.levels = new LevelButton[4];
        this.levels[0] = new LevelButton(mainMenuController, Texture.LEVEL_BUTTON_1, Texture.LEVEL_BUTTON_1_PRESSED);
        this.levels[1] = new LevelButton(mainMenuController, Texture.LEVEL_BUTTON_2, Texture.LEVEL_BUTTON_2_PRESSED);
        this.levels[2] = new LevelButton(mainMenuController, Texture.LEVEL_BUTTON_3, Texture.LEVEL_BUTTON_3_PRESSED);
        this.levels[3] = new LevelButton(mainMenuController, Texture.LEVEL_BUTTON_BOSS, Texture.LEVEL_BUTTON_BOSS_PRESSED);


        this.size = new Dimension(1536, 768);
        this.background = Texture.LEVEL_SELECTION_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(settings);
        for (LevelButton level : levels) {
            this.add(level);
        }

        final Dimension sizeSettings = settings.getPreferredSize();
        final int settingsWidthOffset = this.size.width - sizeSettings.width - 60;
        final int homeWidthOffset = 60;
        final Dimension levelButtonSize = levels[0].getPreferredSize();
        final int settingsHeightOffset = 20;

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        levels[0].setBounds(445, 600, levelButtonSize.width, levelButtonSize.height);
        levels[1].setBounds(605, 390, levelButtonSize.width, levelButtonSize.height);
        levels[2].setBounds(880, 390, levelButtonSize.width, levelButtonSize.height);
        levels[3].setBounds(1030, 610, levelButtonSize.width, levelButtonSize.height);

        this.jframe.getContentPane().add(this);
        this.jframe.pack();
        this.jframe.setResizable(false);
        this.jframe.setSize(size);

        this.setPreferredSize(this.size);
        this.repaint();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void hideView() {
        this.jframe.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.jframe.setVisible(true);
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
