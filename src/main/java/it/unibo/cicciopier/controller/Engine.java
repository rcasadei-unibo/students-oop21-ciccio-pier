package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.Music;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.view.View;

import java.util.Optional;

/**
 * Controls the logic of the game.
 * Coordinates {@link World} and {@link View}.
 */
public interface Engine {

    /**
     * Load game resources and window.
     *
     * @throws Exception if an error occurred while loading the game
     */
    void load() throws Exception;

    /**
     * Start the game.
     */
    void start();

    /**
     * Pause/Unpause the game.
     */
    void pause();

    /**
     * Restart the game.
     */
    void restart();

    /**
     * Stop the game.
     */
    void stop();

    /**
     * Update the game one time.
     * Used in game loop.
     */
    void update();

    /**
     * Get the number of ticks updated from the game start.
     *
     * @return the ticks
     */
    long getTicks();

    /**
     * Execute an action, function for menu buttons.
     *
     * @param action the action
     */
    void action(final LevelMenuAction action);

    /**
     * Get the game loop.
     *
     * @return the loop
     */
    Loop getLoop();

    /**
     * Get the current game state.
     *
     * @return the game state
     */
    GameState getState();

    /**
     * Get the input controller.
     *
     * @return the input controller
     */
    InputController getInput();

    /**
     * Get the world loader for the current level.
     *
     * @return the world loader
     */
    WorldLoader getWorldLoader();

    /**
     * Get the game world for the current level.
     *
     * @return the world
     */
    World getWorld();

    /**
     * Get the game view for the current level.
     *
     * @return the view
     */
    View getView();

    /**
     * Get the level currently playing
     *
     * @return the level
     */
    Level getLevel();

    /**
     * Get the music of this level
     *
     * @return music
     */
    Optional<Music> getMusic();

}
