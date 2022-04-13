package it.unibo.cicciopier.controller.menu;

/**
 * This class represents the developer mode setting
 */
public class DeveloperMode {
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
    }
}
