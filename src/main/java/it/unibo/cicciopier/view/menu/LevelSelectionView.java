package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.PlayLevelButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents an instance of the level selection view
 */
public class LevelSelectionView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelSelectionView.class);
    private final CustomButton level1;
    private final CustomButton level2;
    private final CustomButton level3;
    private final CustomButton level4;

    /**
     * This constructor creates the whole panel with his components
     *
     * @param mainMenuController the instance of the {@link MainMenuController}
     */
    public LevelSelectionView(MainMenuController mainMenuController) {
        LevelSelectionView.LOGGER.info("Initializing the class... ");
        this.level1 = new PlayLevelButton(
                mainMenuController,
                Buttons.LEVEL1,
                Level.FIRST_LEVEL
        );
        this.level2 = new PlayLevelButton(
                mainMenuController,
                Buttons.LEVEL2,
                Level.SECOND_LEVEL
        );
        this.level3 = new PlayLevelButton(
                mainMenuController,
                Buttons.LEVEL3,
                Level.THIRD_LEVEL
        );
        this.level4 = new PlayLevelButton(
                mainMenuController,
                Buttons.LEVEL_BOSS,
                Level.BOSS_LEVEL
        );
        this.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        LevelSelectionView.LOGGER.info("Loading the class...");
        this.setLayout(null);
        this.add(level1);
        this.add(level2);
        this.add(level3);
        this.add(level4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        LevelSelectionView.LOGGER.info("Updating the class...");
        this.setPreferredSize(Screen.getCurrentDimension());
        final Pair<Integer> level1Pos = new Pair<>(
                (int) (this.getPreferredSize().width / 3.50),
                (int) (this.getPreferredSize().height / 1.30));
        final Pair<Integer> level2Pos = new Pair<>(
                (int) (this.getPreferredSize().width / 2.50),
                (int) (this.getPreferredSize().height / 1.97));
        final Pair<Integer> level3Pos = new Pair<>(
                (int) (this.getPreferredSize().width / 1.75),
                (int) (this.getPreferredSize().height / 1.97));
        final Pair<Integer> level4Pos = new Pair<>(
                (int) (this.getPreferredSize().width / 1.49),
                (int) (this.getPreferredSize().height / 1.30));
        this.level1.setBounds(
                level1Pos.getX(),
                level1Pos.getY(),
                this.level1.getPreferredSize().width,
                this.level1.getPreferredSize().height);
        this.level2.setBounds(
                level2Pos.getX(),
                level2Pos.getY(),
                this.level2.getPreferredSize().width,
                this.level2.getPreferredSize().height);
        this.level3.setBounds(
                level3Pos.getX(),
                level3Pos.getY(),
                this.level3.getPreferredSize().width,
                this.level3.getPreferredSize().height);
        this.level4.setBounds(
                level4Pos.getX(),
                level4Pos.getY(),
                level4.getPreferredSize().width,
                level4.getPreferredSize().height);
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(
                Texture.LEVEL_SELECTION_BACKGROUND.getTexture(),
                0,
                0,
                Screen.getCurrentDimension().width,
                Screen.getCurrentDimension().height,
                null
        );

    }
}
