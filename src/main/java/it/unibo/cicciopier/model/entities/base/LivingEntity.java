package it.unibo.cicciopier.model.entities.base;

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
     * @return jump force value
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
     * Checks if the Entity is facing right
     *
     * @return true if its facing right else false
     */
    boolean isFacingRight();

    /**
     * Checks if the Entity is facing left
     *
     * @return true if its facing left else false
     */
    boolean isFacingLeft();

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
