package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.menu.ViewPanels;

public interface ManagerView {

    /**
     * load the menu manager class.
     */
    void load();

    /**
     * Update the frame window.
     */
    void update();

    /**
     * Change the menu view to the given {@link ViewPanels}
     *
     * @param viewPanels the {@link ViewPanels} that will be showed
     */
    void setVisible(final ViewPanels viewPanels);

    /**
     * Change the view to the given level
     *
     * @param engine the game {@link Engine}
     */
    void setVisible(final Engine engine);

    /**
     * Returns the settings view
     *
     * @return the settings view
     */
    SettingsView getSettingsView();

    /**
     * Returns the login view
     *
     * @return the login view
     */
    LoginView getLoginView();

    /**
     * Returns the leaderboard view
     *
     * @return the leaderboard view
     */
    LeaderboardView getLeaderboardView();

    /**
     * Updates the animation showed in the main manu
     */
    void updateAnimations();
}
