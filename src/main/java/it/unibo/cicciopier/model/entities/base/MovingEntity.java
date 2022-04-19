package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.utility.Vector2d;

/**
 * Represents a moving Entity
 */
public interface MovingEntity extends Entity {

    /**
     * Returns the Entity movement
     *
     * @return Entity's movement's 2dVector
     */
    Vector2d getVel();

    /**
     * Sets the Entity movement
     *
     * @param vel Entity's new movement
     */
    void setVel(final Vector2d vel);
}
