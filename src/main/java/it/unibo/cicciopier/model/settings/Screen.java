package it.unibo.cicciopier.model.settings;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(Screen.class);
    private static final GraphicsDevice DEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    private static final Dimension DEFAULT_DIMENSION = new Dimension(1366, 768);
    private static final Dimension CURRENT_DIMENSION = new Dimension(1366, 768);
    private static Dimension MAX_DIMENSION = null;
    private static final List<Resolution> RESOLUTIONS = new ArrayList<>(Arrays.asList(
            new Resolution(7680, 4320),
            new Resolution(3840, 2160),
            new Resolution(2560, 1440),
            new Resolution(1920, 1080),
            new Resolution(1600, 900),
            new Resolution(1366, 768),
            new Resolution(1280, 720),
            new Resolution(854, 480))
    );

    private static double SCALE = 1;

    public static Dimension getDefaultDimension() {
        return new Dimension(Screen.DEFAULT_DIMENSION);
    }

    public static Dimension getCurrentDimension() {
        return new Dimension(Screen.CURRENT_DIMENSION);
    }

    public static void setCurrentDimension(final Dimension dimension) {
        if (dimension.width > getScreenMaxSize().width || dimension.height > getScreenMaxSize().height) {
            LOGGER.error("The dimension selected is bigger than your screen!! ");
            Screen.CURRENT_DIMENSION.setSize(Screen.getScreenMaxSize());
        } else {
            Screen.CURRENT_DIMENSION.setSize(dimension);
        }
        Screen.SCALE = CURRENT_DIMENSION.getHeight() / DEFAULT_DIMENSION.getHeight();
    }

    public static double getScale() {
        return Screen.SCALE;
    }

    public static Dimension getScreenMaxSize() {
        if (Screen.MAX_DIMENSION == null) {
            Dimension maxDimension = Toolkit.getDefaultToolkit().getScreenSize();
            for (Dimension resolution : Screen.RESOLUTIONS) {
                LOGGER.info("Resolution tried: " + resolution);
                if (maxDimension.height >= resolution.height && maxDimension.width >= resolution.width) {
                    Screen.MAX_DIMENSION = new Dimension(resolution);
                    break;
                }
            }
        }
        return new Dimension(Screen.MAX_DIMENSION);
    }

    public static List<Resolution> getResolutions() {
        return new ArrayList<>(Screen.RESOLUTIONS);
    }
}
