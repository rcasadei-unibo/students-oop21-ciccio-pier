package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.entities.EntityState;

/**
 * Represents an Entity whom can die
 */
public interface LivingEntity extends MovingEntity {

    /**
     * Returns the Entity hp
     *
     * @return Entity's current hp
     */
    int getHp();

    /**
     * Returns the Entity total hp
     *
     * @return Entity's maximum hp
     */
    int getMaxHp();

    /**
     * Deals damage to the Entity
     *
     * @param amount damage
     */
    void damage(final int amount);

    /**
     * Heals the Entity
     *
     * @param amount heal
     */
    void heal(final int amount);

    /**
     * Get the jump force
     *
     * @return The jump force value
     */
    int getJumpForce();

    /**
     * Makes the Entity jump
     *
     * @return true if entity has jumped else false
     */
    boolean jump();

    /**
     * Checks if the Entity is on the ground
     *
     * @return true if is on the ground else false
     */
    boolean isGround();

    /**
     * Get the entity  old state
     *
     * @return entity old state
     */
    EntityState getOldState();

    /**
     * Get the entity current state
     *
     * @return entity state
     */
    EntityState getCurrentState();

    /**
     * Set the current state to a given state only if the entity is
     * {@link EntityState#IDLE} or {@link EntityState#RUNNING}
     *
     * @param state in what state to change in
     */
    void setCurrentState(final EntityState state);

    /**
     * Reset the current state to a given state
     *
     * @param state in what state to change in
     */
    void resetCurrentState(final EntityState state);

    /**
     * Checks if the Entity is facing right
     *
     * @return true if its facing right else false
     */
    boolean isFacingRight();

    /**
     * Sets if the Entity is facing right or not
     *
     * @param bool True, if the Entity is facing right
     */
    void setFacingRight(final boolean bool);

    /**
     * Checks if Entity is dead
     *
     * @return If Entity died
     */
    boolean isDead();

    /**
     * Kills the Entity
     */
    void die();
}
