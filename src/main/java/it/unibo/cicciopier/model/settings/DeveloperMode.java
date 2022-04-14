package it.unibo.cicciopier.model.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents the developer mode setting
 */
public class DeveloperMode {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperMode.class);
    private static boolean DEVELOPER_MODE = false;

    /**
     * This function return if the developer mode is active
     *
     * @return The status of the developer mode
     */
    public static boolean isActive() {
        return DEVELOPER_MODE;
    }

    /**
     * This function updates the developer mode status using the given boolean
     *
     * @param developerMode The status that will be set (true/false)
     */
    public static void setActive(boolean developerMode) {
        DEVELOPER_MODE = developerMode;
        LOGGER.info("Developer mode changed to: " + DEVELOPER_MODE);
    }
}
