package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

/**
 * Represents a playable Entity
 */
public interface Player extends LivingEntity {

    /**
     * Returns the Player current stamina
     *
     * @return Player's stamina
     */
    int getStamina();

    /**
     * Returns the Player total stamina
     *
     * @return Player's stamina
     */
    int getMaxStamina();

    /**
     * Gives stamina to the Player
     *
     * @param amount The stamina's amount
     */
    void addStamina(final int amount);

    /**
     * Removes stamina from the Player
     *
     * @param amount The stamina's amount
     */
    void decreaseStamina(final int amount);

    /**
     * The player attacks, prioritizing the nearest enemy
     */
    void attackNearest();

    /**
     * Get the current score of the player
     */
    public int getScore();

    /**
     * Get the current coin counter
     *
     * @return coins number
     */
    public int getCoin();

    /**
     * Add a score to the score of the player
     *
     * @param score need to add
     */
    public void addScore(final int score);

    /**
     * Update the number of coins by 1
     */
    public void addCoin();
}
