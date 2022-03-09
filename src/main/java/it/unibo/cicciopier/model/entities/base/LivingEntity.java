package it.unibo.cicciopier.model.entities.base;

/**
 * Represents an Entity whom can die
 */
public interface LivingEntity extends MovingEntity {

    /**
     * @return An {@link Integer} representing the Entity current HP
     */
    int getHp();

    /**
     * @return An {@link Integer} representing the Entity maximum HP
     */
    int getMaxHp();

    /**
     * Deals damage to the Entity
     * @param amount An {@link Integer} representing the damage the Entity has received
     */
    void damage(final int amount);

    /**
     * Heals the Entity
     * @param amount An {@link Integer} representing the healing the Entity has received
     */
    void heal(final int amount);

    /**
     * @return True, if the Entity has died
     */
    boolean isDead();

    /**
     * Kills the Entity
     */
    void die();
}
