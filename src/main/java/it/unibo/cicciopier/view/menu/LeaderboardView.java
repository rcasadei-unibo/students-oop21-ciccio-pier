package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LeaderboardView extends JPanel {
    private final BufferedImage background;
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;

    public LeaderboardView(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;

        this.loggedUser = new JLabel("Logged user: " + this.mainMenuController.getUsername());

        CustomButton settings = new CustomButton(this.mainMenuController, Buttons.SETTINGS);

        CustomButton home = new CustomButton(this.mainMenuController, Buttons.HOME);

        this.loggedUser.setFont(loggedUser.getFont().deriveFont(Font.BOLD, 20));


        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.LEADERBOARD_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(settings);
        this.add(loggedUser);

        final Dimension sizeSettings = settings.getPreferredSize();
        final int settingsWidthOffset = size.width - sizeSettings.width - 60;
        final int homeWidthOffset = 60;
        final int settingsHeightOffset = 20;

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeSettings.width, sizeSettings.height);
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
