package it.unibo.cicciopier.controller.menu;

import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.User;
import it.unibo.cicciopier.view.menu.MenuManagerView;
import it.unibo.cicciopier.view.menu.buttons.MenuActionButton;

import java.util.List;

public interface MenuController {

    /**
     * This function sets visible a given {@link ViewPanels} by calling {@link MenuManagerView}
     *
     * @param viewPanels the {@link ViewPanels} that will be showed
     */
    void show(final ViewPanels viewPanels);

    /**
     * This function is called by any {@link MenuActionButton} and it checks the button action and execute it
     *
     * @param menuAction Is the action that the button when pressed have to execute
     */
    void action(final MenuAction menuAction);

    /**
     * This function stops the main menu background music and starts the {@link Level}
     * creating a new game engine with his music
     *
     * @param level The level that needs to be started
     */
    void startLevel(final Level level);

    /**
     * This function is called on quit button press and just exit the game
     */
    void quitAction();

    /**
     * This function is called at the end of a {@link Level} ether by winning or quitting it in the game menu, if the
     * {@link User} won the level the score will be checked and if is higher that his previous one it will be updated
     *
     * @param score     The score made by the player in the level he just played
     * @param gameState The {@link GameState} that the player finished the level with ({@link GameState#WON} or
     *                  {@link GameState#OVER})
     * @param level     The level that was being played
     */
     void endOfLevel(final int score, final GameState gameState, final Level level);

     /**
     * This function returns the username of the logged user
     *
     * @return The username of the current logged in player
     */
    String getUsername();

    /**
     * This function returns the logged player
     *
     * @return The current logged in player
     */
    User getPlayer();

    /**
     * This function returns the list of the user loaded
     *
     * @return The list of all the users
     */
    List<User> getUsers();

    /**
     * This function returns the {@link MenuManagerView} of the {@link MainMenuController}
     *
     * @return The {@link MenuManagerView} of this {@link MainMenuController}
     */
    MenuManagerView getMenu();
}
