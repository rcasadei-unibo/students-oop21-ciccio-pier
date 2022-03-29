package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.view.menu.ViewPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class CustomButton extends JComponent implements MouseListener {

    private final Dimension dimension;
    private final MainMenuController mainMenuController;
    private final BufferedImage[] image;
    private int buttonStatus;
    private final MenuAction menuAction;
    private final ViewPanels viewPanels;
    private final boolean hasHover;
    private final GameEngine gameEngine;


    /**
     * This constructor calls the fathers constructor and adds the implementation of a mouse listener
     */
    public CustomButton(MainMenuController mainMenuController, Buttons button) {
        super();

        this.dimension = button.dimension;
        this.mainMenuController = mainMenuController;
        this.enableInputMethods(true);
        this.addMouseListener(this);
        this.buttonStatus = 0;
        this.image = button.bufferedImage;
        this.hasHover = button.hasHover;
        this.menuAction = button.menuAction;
        this.viewPanels = button.viewPanels;
        this.gameEngine = button.gameEngine;


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        g.drawImage(image[buttonStatus], 0, 0, null);
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
        AudioController.getAudioController().playSound(Sound.MAIN_BUTTON);
        this.buttonStatus = 1;
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.buttonStatus = 0;
        this.repaint();
        if (viewPanels != null) {
            this.mainMenuController.show(this.viewPanels);
        } else if (this.menuAction == MenuAction.PLAY_LEVEL && this.gameEngine != null) {
            this.mainMenuController.startLevel(this.gameEngine);
        } else this.mainMenuController.action(this.menuAction);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.hasHover) {
            AudioController.getAudioController().playSound(Sound.HOVER_BUTTON);
            this.buttonStatus = 2;
            this.repaint();
        }
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


