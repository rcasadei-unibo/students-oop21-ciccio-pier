package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Define a button implementation with his action
 */
public abstract class CustomButton extends JComponent implements MouseListener {
    private final Dimension dimension;
    private final Buttons button;
    private int buttonStatus;

    /**
     * This constructor calls the fathers constructor and adds the implementation of a mouse listener
     *
     * @param button define the button type
     */
    public CustomButton(final Buttons button) {
        super();

        this.button = button;
        this.dimension = new Dimension(button.getTextures()[0].getWidth(), button.getTextures()[0].getHeight());
        this.enableInputMethods(true);
        this.addMouseListener(this);
        this.buttonStatus = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(button.getTextures()[buttonStatus], 0, 0, null);
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
        buttonAction();
    }

    /**
     * Button action that is executed on click
     */
    protected abstract void buttonAction();

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.button.hasHover()) {
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


