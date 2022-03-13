package it.unibo.cicciopier.model.entities.base;

/**
 * Represents an Entity whom can die
 */
public interface LivingEntity extends MovingEntity {

    /**
     * Returns the Entity hp
     * @return Entity's current hp
     */
    int getHp();

    /**
     * Returns the Entity total hp
     * @return Entity's maximum hp
     */
    int getMaxHp();

    /**
     * Deals damage to the Entity
     * @param amount damage
     */
    void damage(final int amount);

    /**
     * Heals the Entity
     * @param amount heal
     */
    void heal(final int amount);

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
     * Checks if Entity is dead
     * @return If Entity died
     */
    boolean isDead();

    /**
     * Kills the Entity
     */
    void die();
}
