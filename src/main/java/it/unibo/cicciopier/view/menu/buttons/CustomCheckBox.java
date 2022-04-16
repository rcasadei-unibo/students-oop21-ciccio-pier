package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.model.settings.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomCheckBox extends JCheckBox {

    private final BufferedImage unSelected;
    private final BufferedImage selected;

    public CustomCheckBox(BufferedImage unSelected, BufferedImage selected) {
        super();
        this.unSelected = unSelected;
        this.selected = selected;

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.isSelected()) {
            g.drawImage(this.selected,
                    0,
                    0,
                    Screen.scale(this.selected.getWidth()),
                    Screen.scale(this.selected.getHeight()),
                    null);
        } else {
            g.drawImage(this.unSelected,
                    0,
                    0,
                    Screen.scale(this.unSelected.getWidth()),
                    Screen.scale(this.unSelected.getHeight()),
                    null);
        }
    }
}
