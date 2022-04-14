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
                    (int) (this.selected.getWidth() * Screen.getScale()),
                    (int) (this.selected.getHeight() * Screen.getScale()),
                    null);
        } else {
            g.drawImage(this.unSelected,
                    0,
                    0,
                    (int) (this.unSelected.getWidth() * Screen.getScale()),
                    (int) (this.unSelected.getHeight() * Screen.getScale()),
                    null);
        }
    }
}
