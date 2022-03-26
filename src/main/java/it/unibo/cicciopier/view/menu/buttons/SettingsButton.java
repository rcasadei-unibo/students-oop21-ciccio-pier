package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.ViewPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class SettingsButton extends JComponent implements MouseListener {

    private final Dimension dimension;
    private final MainMenuController mainMenuController;
    private final BufferedImage[] image;
    private int buttonStatus;

    /**
     * This constructor calls the fathers constructor and adds the implementation of a mouse listener
     */
    public SettingsButton(MainMenuController mainMenuController) {
        super();

        this.dimension = new Dimension(85, 85);
        this.mainMenuController = mainMenuController;
        this.enableInputMethods(true);
        this.addMouseListener(this);
        this.buttonStatus = 0;
        this.image = new BufferedImage[3];
        this.image[0] = Texture.SETTINGS_BUTTON.getTexture();
        this.image[1] = Texture.SETTINGS_BUTTON_HOVER.getTexture();
        this.image[2] = Texture.SETTINGS_BUTTON_PRESSED.getTexture();
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

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.buttonStatus = 2;
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.buttonStatus = 0;
        this.repaint();
        mainMenuController.show(ViewPanels.SETTINGS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.buttonStatus = 1;
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.buttonStatus = 0;
        this.repaint();
    }
}


