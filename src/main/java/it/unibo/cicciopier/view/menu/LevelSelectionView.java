package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.PlayLevelButton;
import it.unibo.cicciopier.view.menu.buttons.ViewPanelButton;

import javax.swing.*;
import java.awt.*;


public class LevelSelectionView extends JPanel {
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;

    public LevelSelectionView(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;

        this.loggedUser = new JLabel("Logged user: " + this.mainMenuController.getUsername());

        CustomButton settings = new ViewPanelButton(this.mainMenuController, Buttons.SETTINGS, ViewPanels.SETTINGS);

        CustomButton home = new ViewPanelButton(this.mainMenuController, Buttons.HOME, ViewPanels.MAIN_MENU);

        CustomButton level1 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL1, Level.FIRST_LEVEL);

        CustomButton level2 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL2, Level.SECOND_LEVEL);

        CustomButton level3 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL3, Level.THIRD_LEVEL);

        CustomButton level4 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL_BOSS, Level.BOSS_LEVEL);


        this.loggedUser.setFont(loggedUser.getFont().deriveFont(Font.BOLD, 20));
        this.loggedUser.setForeground(Color.WHITE);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);

        this.setLayout(null);
        this.add(this.loggedUser);
        this.add(home);
        this.add(settings);
        this.add(level1);
        this.add(level2);
        this.add(level3);
        this.add(level4);

        final int homeWidthOffset = size.width / 25;
        final int settingsWidthOffset = size.width - settings.getPreferredSize().width - homeWidthOffset;
        final int settingsHeightOffset = (int) (size.height / 38.4);
        final Pair<Integer> level1Pos = new Pair<>((int) (size.width / 3.50), (int) (size.height / 1.30));
        final Pair<Integer> level2Pos = new Pair<>((int) (size.width / 2.50), (int) (size.height / 1.97));
        final Pair<Integer> level3Pos = new Pair<>((int) (size.width / 1.75), (int) (size.height / 1.97));
        final Pair<Integer> level4Pos = new Pair<>((int) (size.width / 1.49), (int) (size.height / 1.30));


        this.loggedUser.setBounds(homeWidthOffset, settingsHeightOffset + settings.getPreferredSize().height,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);

        settings.setBounds(settingsWidthOffset, settingsHeightOffset, settings.getPreferredSize().width,
                settings.getPreferredSize().height);

        home.setBounds(homeWidthOffset, settingsHeightOffset, home.getPreferredSize().width,
                home.getPreferredSize().height);

        level1.setBounds(level1Pos.getX(), level1Pos.getY(), level1.getPreferredSize().width,
                level1.getPreferredSize().height);

        level2.setBounds(level2Pos.getX(), level2Pos.getY(), level2.getPreferredSize().width,
                level2.getPreferredSize().height);

        level3.setBounds(level3Pos.getX(), level3Pos.getY(), level3.getPreferredSize().width,
                level3.getPreferredSize().height);

        level4.setBounds(level4Pos.getX(), level4Pos.getY(), level4.getPreferredSize().width,
                level4.getPreferredSize().height);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Texture.LEVEL_SELECTION_BACKGROUND.getTexture(), 0, 0, null);
    }

    /**
     * This function update the current logged user username in the text area
     */
    public void updateLoggedUser() {
        loggedUser.setText(("Logged user: " + mainMenuController.getUsername()));
        this.loggedUser.setBounds(loggedUser.getBounds().x,loggedUser.getBounds().y,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);
    }

}
