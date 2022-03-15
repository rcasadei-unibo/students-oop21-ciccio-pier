package it.unibo.cicciopier.view;

import java.awt.*;

/**
 * View interface of a game object.
 */
public interface GameObjectView {

    /**
     * Render the game object.
     * @param g graphic context
     */
    void render(final Graphics g);

}
