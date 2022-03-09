package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.GameObject;
import it.unibo.cicciopier.utility.Vector2d;

/**
 * Represents a moving Entity
 */
public interface MovingEntity extends Entity {

    /**
     * @return A @{@link Vector2d} representing the Entity movement
     */
    Vector2d getVel();

    /**
     * Sets the Entity movement
     * @param vel A {@link Vector2d} representing how the Entity has moved
     */
    void setVel(final Vector2d vel);

    /**
     * Makes the Entity jump
     */
    void jump();

    /**
     * @return True, if the Entity is currently jumping
     */
    boolean isJumping();

    /**
     * @param object {@link GameObject}
     * @return True, if the Entity is colliding
     */
    boolean checkCollision(final GameObject object);
}
