package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;

/**
 * Contains view objects and manages the game windows.
 */
public interface View {

    /**
     * Load game window.
     * @throws Exception error
     */
    void load() throws Exception;

    /**
     * Open the game window.
     */
    void start();

    /**
     * Update the game window one time.
     */
    void render();

    /**
     * Get the game engine.
     * @return the game engine
     */
    Engine getEngine();

}
