package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.PlayLevelButton;
import it.unibo.cicciopier.view.menu.buttons.ViewPanelButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;


public class LevelSelectionView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelSelectionView.class);
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;
    private final CustomButton settings;
    private final CustomButton home;
    private final CustomButton level1;
    private final CustomButton level2;
    private final CustomButton level3;
    private final CustomButton level4;

    public LevelSelectionView(MainMenuController mainMenuController) {
        LevelSelectionView.LOGGER.info("Initializing the class... ");
        this.mainMenuController = mainMenuController;
        this.loggedUser = new JLabel("Logged user: " + this.mainMenuController.getUsername());
        this.settings = new ViewPanelButton(this.mainMenuController, Buttons.SETTINGS, ViewPanels.SETTINGS);
        this.home = new ViewPanelButton(this.mainMenuController, Buttons.HOME, ViewPanels.MAIN_MENU);
        this.level1 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL1, Level.FIRST_LEVEL);
        this.level2 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL2, Level.SECOND_LEVEL);
        this.level3 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL3, Level.THIRD_LEVEL);
        this.level4 = new PlayLevelButton(this.mainMenuController, Buttons.LEVEL_BOSS, Level.BOSS_LEVEL);

        this.load();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Texture.LEVEL_SELECTION_BACKGROUND.getTexture(), 0, 0, Screen.getCurrentDimension().width, Screen.getCurrentDimension().height, null);

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLoggedUser() {
        this.loggedUser.setText(("Logged user: " + this.mainMenuController.getUsername()));
        this.loggedUser.setBounds(this.loggedUser.getBounds().x, this.loggedUser.getBounds().y, this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimations() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        LevelSelectionView.LOGGER.info("Updating the class...");
        this.setPreferredSize(Screen.getCurrentDimension());

        this.updateLoggedUser();
        this.loggedUser.setFont(CustomFont.getInstance().getFontOrDefault());

        final int homeWidthOffset = this.getPreferredSize().width / 25;
        final int settingsWidthOffset = this.getPreferredSize().width - settings.getPreferredSize().width - homeWidthOffset;
        final int settingsHeightOffset = (int) (this.getPreferredSize().height / 38.4);

        final Pair<Integer> loggedUserPos = new Pair<>(this.getPreferredSize().width / 25,
                (int)(this.getPreferredSize().height/1.01 - this.loggedUser.getPreferredSize().height));
        final Pair<Integer> level1Pos = new Pair<>((int) (this.getPreferredSize().width / 3.50),
                (int) (this.getPreferredSize().height / 1.30));

        final Pair<Integer> level2Pos = new Pair<>((int) (this.getPreferredSize().width / 2.50),
                (int) (this.getPreferredSize().height / 1.97));

        final Pair<Integer> level3Pos = new Pair<>((int) (this.getPreferredSize().width / 1.75),
                (int) (this.getPreferredSize().height / 1.97));

        final Pair<Integer> level4Pos = new Pair<>((int) (this.getPreferredSize().width / 1.49),
                (int) (this.getPreferredSize().height / 1.30));


        this.loggedUser.setBounds(loggedUserPos.getX(), loggedUserPos.getY(),
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);

        this.settings.setBounds(settingsWidthOffset, settingsHeightOffset,
                settings.getPreferredSize().width, settings.getPreferredSize().height);

        this.home.setBounds(homeWidthOffset, settingsHeightOffset, home.getPreferredSize().width,
                home.getPreferredSize().height);

        this.level1.setBounds(level1Pos.getX(), level1Pos.getY(), level1.getPreferredSize().width,
                level1.getPreferredSize().height);

        this.level2.setBounds(level2Pos.getX(), level2Pos.getY(), level2.getPreferredSize().width,
                level2.getPreferredSize().height);

        this.level3.setBounds(level3Pos.getX(), level3Pos.getY(), level3.getPreferredSize().width,
                level3.getPreferredSize().height);

        this.level4.setBounds(level4Pos.getX(), level4Pos.getY(), level4.getPreferredSize().width,
                level4.getPreferredSize().height);

        this.repaint();
    }

    @Override
    public void load() {
        LevelSelectionView.LOGGER.info("Loading the class...");
        this.loggedUser.setForeground(Color.WHITE);

        this.setLayout(null);
        this.add(this.loggedUser);
        this.add(home);
        this.add(settings);
        this.add(level1);
        this.add(level2);
        this.add(level3);
        this.add(level4);
    }
}
