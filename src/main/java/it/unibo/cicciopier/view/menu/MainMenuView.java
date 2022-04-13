package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenuView extends JPanel {
    private static final int BUTTON_HEIGHT = 322;
    private static final int BUTTON_SPACE = 15;
    private final BufferedImage background;
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;


    public MainMenuView(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;

        this.loggedUser = new JLabel("Logged user: " + this.mainMenuController.getUsername());
        this.loggedUser.setForeground(Color.WHITE);

        CustomButton settings = new ViewPanelButton(this.mainMenuController, Buttons.SETTINGS, ViewPanels.SETTINGS);

        CustomButton play = new ViewPanelButton(this.mainMenuController, Buttons.PLAY, ViewPanels.LEVEL_SELECTION);

        CustomButton leaderboard = new ViewPanelButton(this.mainMenuController, Buttons.LEADERBOARD, ViewPanels.LEADERBOARD);

        CustomButton quit = new MenuActionButton(this.mainMenuController, Buttons.QUIT, MenuAction.QUIT);

        this.loggedUser.setFont(loggedUser.getFont().deriveFont(Font.BOLD, 20));

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        background = Texture.MENU_BACKGROUND.getTexture();


        this.setLayout(null);
        this.add(play);
        this.add(leaderboard);
        this.add(settings);
        this.add(quit);
        this.add(loggedUser);


        final Dimension sizeButton = play.getPreferredSize();
        final Dimension sizeSettings = settings.getPreferredSize();
        final int width = size.width / 2 - sizeButton.width / 2;
        final int spacing = sizeButton.height + BUTTON_SPACE;
        final int settingsWidth = size.width - sizeSettings.width - 60;
        final int settingsHeightOffset = 20;

        play.setBounds(width, BUTTON_HEIGHT, sizeButton.width, sizeButton.height);

        leaderboard.setBounds(width, BUTTON_HEIGHT + spacing, sizeButton.width, sizeButton.height);

        quit.setBounds(width, BUTTON_HEIGHT + spacing * 2, sizeButton.width, sizeButton.height);

        settings.setBounds(settingsWidth, settingsHeightOffset, sizeSettings.width, sizeSettings.height);

        loggedUser.setBounds(60, settingsHeightOffset, 300, 30);


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
