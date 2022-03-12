package it.unibo.cicciopier.controller.menu;

import javax.swing.*;

public interface MenuController {

    /**
     * Initialize all menu views elements
     */
    void init();

    /**
     * Load the leaderboard json and load the UI from the Leaderboard view
     */
    void showLeaderboard();

    /**
     *  load the UI frm the main menu view
     */
    void showMainMenu();

    /**
     * load the UI from the Settings view
     */
    void showSettings();

    /**
     * load the UI from the level selection view
     */
    void showGamesSelection();
}
