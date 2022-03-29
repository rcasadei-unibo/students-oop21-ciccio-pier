package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelSelectionView extends JPanel {
    private final BufferedImage background;
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;

    public LevelSelectionView(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;

        this.loggedUser = new JLabel("Logged user: " + this.mainMenuController.getUsername());

        CustomButton settings = new CustomButton(this.mainMenuController, Buttons.SETTINGS);

        CustomButton home = new CustomButton(this.mainMenuController, Buttons.HOME);

        CustomButton level1 = new CustomButton(this.mainMenuController, Buttons.LEVEL1);

        CustomButton level2 = new CustomButton(this.mainMenuController, Buttons.LEVEL2);

        CustomButton level3 = new CustomButton(this.mainMenuController, Buttons.LEVEL3);

        CustomButton levelBoss = new CustomButton(this.mainMenuController, Buttons.LEVEL_BOSS);


        loggedUser.setFont(loggedUser.getFont().deriveFont(Font.BOLD, 20));

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
        this.add(loggedUser);


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
        loggedUser.setBounds(homeWidthOffset, settingsHeightOffset + sizeSettings.height + 10, 300, 30);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    public void updateLoggedUser() {
        loggedUser.setText(("Logged user: " + mainMenuController.getUsername()));
    }

}
