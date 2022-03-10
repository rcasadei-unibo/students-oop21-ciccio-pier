package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.view.View;

/**
 * Controls the logic of the game.
 * Coordinates {@link World} and {@link View}.
 */
public interface Engine {

    /**
     * Load game resources and window.
     *
     * @throws Exception error
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

}
