package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.GameObject;
import it.unibo.cicciopier.utility.Vector2d;

/**
 * Represents a moving Entity
 */
public interface MovingEntity extends Entity {

    /**
     * Returns the Entity position
     * @return Entity's position's 2dVector
     */
    Vector2d getVel();

    /**
     * Sets the Entity position
     * @param vel New Entity's position
     */
    void setVel(final Vector2d vel);

    /**
     * Makes the Entity jump
     */
    void jump();

    /**
     * Checks if the Entity is jumping
     * @return If Entity is still jumping
     */
    boolean isJumping();

    /**
     * Checks the collision between Entity and another GameObject
     * @param object The GameObject to check
     * @return If they are colliding
     */
    boolean checkCollision(final GameObject object);
}
