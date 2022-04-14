package it.unibo.cicciopier.model.settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Resolution extends Dimension {

    public Resolution(int width, int height) {
        super(width,height);
    }

    @Override
    public String toString() {
        return this.width + " x " + this.height;
    }
}
