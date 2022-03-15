package it.unibo.cicciopier.model;

import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;

import java.awt.*;

/**
 * Represents a generic object in the {@link World}.
 */
public interface GameObject {

    /**
     * Get the width of the object.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Get the height of the object.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Get the position inside the {@link World}.
     *
     * @return the position
     */
    Vector2d getPos();

    /**
     * Set the position inside the {@link World}.
     *
     * @param pos the new position
     */
    void setPos(final Vector2d pos);

    /**
     * Get the {@link Rectangle} that represents the occupied space inside the {@link World}.
     *
     * @return the rectangle
     */
    default Rectangle getBounds() {
        return new Rectangle(this.getPos().getX(), this.getPos().getY(), this.getWidth(), this.getHeight());
    }

    /**
     * Get the view of this game object.
     *
     * @return the view
     */
    GameObjectView getView();

}
