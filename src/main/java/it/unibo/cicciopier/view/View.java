package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;

/**
 * Contains view objects and manages the game windows.
 */
public interface View extends StaticView {


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
