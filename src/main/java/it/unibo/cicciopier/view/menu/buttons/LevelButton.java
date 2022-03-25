package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.view.Texture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LevelButton extends JComponent implements MouseListener {

    private final Dimension dimension;
    private final MainMenuController mainMenuController;
    private final BufferedImage[] image;
    private int buttonStatus;
    private final Texture button;
    private final Texture pressedButton;

    /**
     * This constructor calls the fathers constructor and adds the implementation of a mouse listener
     */
    public LevelButton(final MainMenuController mainMenuController, final Texture button, final Texture pressedButton) {
        super();
        this.dimension = new Dimension(90, 90);
        this.mainMenuController = mainMenuController;
        this.enableInputMethods(true);
        this.addMouseListener(this);
        this.buttonStatus = 0;
        this.button = button;
        this.pressedButton = pressedButton;
        this.image = new BufferedImage[2];
        image[0] = this.button.getTexture();
        image[1] = this.pressedButton.getTexture();
    }

    private void load() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        g.drawImage(image[buttonStatus],0,0,null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize() {
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize() {
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMaximumSize() {
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        mainMenuController.settingsAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        buttonStatus = 1;
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        buttonStatus = 0;
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent e) {
        buttonStatus = 0;
        this.repaint();
    }
}


