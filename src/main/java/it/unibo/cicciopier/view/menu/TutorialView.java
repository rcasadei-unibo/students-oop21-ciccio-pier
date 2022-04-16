package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.Texture;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents an instance of the tutorial view
 */
public class TutorialView extends JPanel implements MenuPanel {

    /**
     * This constructor generates an instance of the tutorial view
     */
    public TutorialView() {
        load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(
                Texture.TUTORIAL_BACKGROUND.getTexture(),
                0,
                0,
                Screen.getCurrentDimension().width,
                Screen.getCurrentDimension().height,
                null
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        this.setLayout(null);
    }
}
