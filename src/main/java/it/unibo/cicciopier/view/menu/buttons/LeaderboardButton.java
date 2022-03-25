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

public class LeaderboardButton extends JComponent implements MouseListener {

    private final Dimension dimension = new Dimension(280, 106);
    ;
    private final MainMenuController mainMenuController;
    private BufferedImage[] image;
    private int buttonStatus;

    /**
     * This constructor calls the fathers constructor and adds the implementation of a mouse listener
     */
    public LeaderboardButton(MainMenuController mainMenuController) {
        super();
        this.mainMenuController = mainMenuController;
        this.enableInputMethods(true);
        this.addMouseListener(this);
        this.image = new BufferedImage[2];
        this.buttonStatus = 0;
        this.load();
    }

    private void load() {
        image[0] = Texture.LEADERBOARD_BUTTON.getTexture();
        image[1] = Texture.LEADERBOARD_BUTTON_PRESSED.getTexture();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        g.drawImage(image[this.buttonStatus], 0, 0, null);
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
        mainMenuController.leaderboardAction();
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

    }
}


