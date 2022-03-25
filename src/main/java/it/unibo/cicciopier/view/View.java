package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;

/**
 * Contains view objects and manages the game windows.
 */
public interface View extends StaticView {

    /**
     * Load game window.
     *
     * @throws Exception error
     */
    void load() throws Exception;

    /**
     * Update the game window one time.
     */
    void render();

    /**
     * Get the game engine.
     *
     * @return the game engine
     */
    Engine getEngine();

}
