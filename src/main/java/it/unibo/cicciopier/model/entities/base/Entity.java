package it.unibo.cicciopier.model.entities.base;

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
     * Checks if Entity was removed
     * @return If removed
     */
    boolean isRemoved();

    /**
     * Removes the Entity from the world
     */
    void remove();

    /**
     * Called every game cycle to update the Entity
     */
    void tick();

}
