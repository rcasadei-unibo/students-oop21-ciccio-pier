package it.unibo.cicciopier.model.entities.base;

/**
 * Simple enum that contains all types of collisions
 */
public enum Collision {
    /**
     * The entity is colliding up
     */
    COLLIDING_UP,
    /**
     * The entity is colliding right
     */
    COLLIDING_RIGHT,
    /**
     * The entity is colliding down
     */
    COLLIDING_DOWN,
    /**
     * The entity is colliding left
     */
    COLLIDING_LEFT,
    /**
     * The entity is going to collide up
     */
    NEAR_COLLIDING_UP,
    /**
     * The entity is going to collide right
     */
    NEAR_COLLIDING_RIGHT,
    /**
     * The entity is going to collide down
     */
    NEAR_COLLIDING_DOWN,
    /**
     * The entity is going to collide left
     */
    NEAR_COLLIDING_LEFT,
    /**
     * The entity is falling out of the world
     */
    FALLING
}
