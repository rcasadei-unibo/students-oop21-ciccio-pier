package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.GameObject;

/**
 * Represents an interactive static GameObject
 */
public interface Entity extends GameObject {

    /**
     * @return The {@link EntityType} of the Entity
     */
    EntityType getType();

    /**
     * @return If the Entity was removed
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
