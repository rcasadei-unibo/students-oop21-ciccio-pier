package it.unibo.cicciopier.model.settings;

import java.awt.*;

/**
 *  This class identify a resolution treated as a {@link Dimension}
 */
public class Resolution extends Dimension {

    /**
     *  This constructor generates a new resolution by calling  a {@link Dimension} constructor
     */
    public Resolution(final int width, final int height) {
        super(width,height);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String toString() {
        return this.width + " x " + this.height;
    }
}
