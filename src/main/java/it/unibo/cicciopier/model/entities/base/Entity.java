package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.model.GameObject;

/**
 * Represents an interactive static GameObject
 */
public interface Entity extends GameObject {

    /**
     * Returns the type of the Entity
     * @return Entity's type
     */
    EntityType getType();

    /**
     * Load the entity
     */
    void load();

    /**
     * Checks if Entity was removed
     * @return If removed
     */
    boolean isRemoved();

    /**
     * Checks the collision between Entity and another GameObject
     * @param object The GameObject to check
     * @return If they are colliding
     */
    boolean checkCollision(final GameObject object);

    /**
     * Removes the Entity from the world
     */
    void remove();

    /**
     * Called every game cycle to update the Entity
     *
     * @param ticks {@link Engine#getTicks()}
     */
    void tick(final long ticks);

}
