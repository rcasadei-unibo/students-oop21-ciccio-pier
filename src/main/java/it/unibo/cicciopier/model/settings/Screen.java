package it.unibo.cicciopier.model.settings;

import java.awt.*;

public class Screen {
    private static final Dimension DEFAULT_DIMENSION = new Dimension(1366, 768);
    private static final Dimension CURRENT_DIMENSION = new Dimension(1366, 768);
    private static double SCALE = 1;

    public static Dimension getDefaultDimension() {
        return new Dimension(Screen.DEFAULT_DIMENSION);
    }

    public static Dimension getCurrentDimension() {
        return new Dimension(Screen.CURRENT_DIMENSION);
    }

    public static void setCurrentDimension(final Dimension dimension) {
        Screen.CURRENT_DIMENSION.setSize(dimension);
        Screen.SCALE = dimension.getHeight() / DEFAULT_DIMENSION.getHeight() ;
    }

    public static double getScale() {
        return Screen.SCALE;
    }
}
