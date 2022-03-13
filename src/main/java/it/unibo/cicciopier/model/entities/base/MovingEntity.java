package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.GameObject;
import it.unibo.cicciopier.utility.Vector2d;

/**
 * Represents a moving Entity
 */
public interface MovingEntity extends Entity {

    /**
     * Returns the Entity movement
     * @return Entity's movement's 2dVector
     */
    Vector2d getVel();

    /**
     * Sets the Entity movement
     * @param vel Entity's new movement
     */
    void setVel(final Vector2d vel);

    /**
     * Checks the collision between Entity and another GameObject
     * @param object The GameObject to check
     * @return If they are colliding
     */
    boolean checkCollision(final GameObject object);
}
