package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

/**
 * Represents a playable Entity
 */
public interface Player extends LivingEntity {

    /**
     * @return An {@link Integer} representing the Player current stamina
     */
    int getStamina();

    /**
     * @return An {@link Integer} representing the Player maximum stamina
     */
    int getMaxStamina();

    /**
     * Gives stamina to the Player
     * @param amount An {@link Integer} representing the amount of stamina
     */
    void addStamina(int amount);

    /**
     * Removes stamina from the Player
     * @param amount An {@link Integer} representing the amount of stamina
     */
    void decreaseStamina(int amount);

    /**
     * The player attack, giving priority to the nearest enemy
     */
    void attackNearest();
}
