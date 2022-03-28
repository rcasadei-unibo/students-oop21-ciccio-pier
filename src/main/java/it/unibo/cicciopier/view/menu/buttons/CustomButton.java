package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.AudioController;
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
    private ViewPanels viewPanels;
    private final boolean hasHover;


    /**
     * This constructor calls the fathers constructor and adds the implementation of a mouse listener
     */
    public CustomButton(MainMenuController mainMenuController, Dimension dimension, BufferedImage[] image, MenuAction action, boolean hasHover) {
        super();

        this.dimension = dimension;
        this.mainMenuController = mainMenuController;
        this.enableInputMethods(true);
        this.addMouseListener(this);
        this.buttonStatus = 0;
        this.image = image;
        this.hasHover = hasHover;
        this.menuAction = action;


    }
    public CustomButton(MainMenuController mainMenuController, Dimension dimension, BufferedImage[] image, MenuAction action,boolean hasHover, ViewPanels viewPanels) {
        this(mainMenuController,dimension,image,action,hasHover);
        this.viewPanels = viewPanels;

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
        if(viewPanels!=null){
            mainMenuController.action(this.menuAction,this.viewPanels);
        }else mainMenuController.action(this.menuAction);

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


