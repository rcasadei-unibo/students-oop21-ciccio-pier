package it.unibo.cicciopier.model.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This class represents the screen and define his resolutions and handle the screen sizing and the scale that is used
 * to adjust the size of every component showed on the screen
 */
public class Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(Screen.class);
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

    /**
     *  This function returns the current resolution of the app
     *
     * @return the current resolution of the app
     */
    public static Dimension getCurrentDimension() {
        return new Dimension(Screen.CURRENT_DIMENSION);
    }

    /**
     * This function sets the current resolution to a given resolution if and only if the resolution given is not bigger
     * than your screen, if it is it will set the resolution to the max resolution possible for your screen
     *
     * @param dimension the resolution that you want to change the current resolution to
     */
    public static void setCurrentDimension(final Dimension dimension) {
        if (dimension.width > getScreenMaxSize().width || dimension.height > getScreenMaxSize().height) {
            LOGGER.error("The dimension selected is bigger than your screen!! ");
            Screen.CURRENT_DIMENSION.setSize(Screen.getScreenMaxSize());
        } else {
            Screen.CURRENT_DIMENSION.setSize(dimension);
        }
        Screen.SCALE = CURRENT_DIMENSION.getHeight() / DEFAULT_DIMENSION.getHeight();
    }

    /**
     * This function scale a given value using the scale based on the current size of the screen
     *
     * @param value is the value that will be scaled
     * @return the value scaled
     */
    public static int scale(final double value) {
        return (int) (Screen.SCALE * value);
    }

    /**
     * This function returns the closest max resolution in 16:9 ration based on the screen
     *
     * @return the max resolution
     */
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

    /**
     * Returns the list of resolutions available
     *
     * @return the list of resolutions
     */
    public static List<Resolution> getResolutions() {
        return new ArrayList<>(Screen.RESOLUTIONS);
    }
}
