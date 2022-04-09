package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

/**
 * Represents a playable Entity
 */
public interface Player extends LivingEntity {

    /**
     * Get player speed.
     *
     * @return the speed
     */
    int getSpeed();

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
    int getScore();

    /**
     * Get the current coin counter
     *
     * @return coins number
     */
    int getCoin();

    /**
     * Add a score to the score of the player
     *
     * @param score need to add
     */
    void addScore(final int score);

    /**
     * Update the number of coins by 1
     */
    void addCoin();

    /**
     * Sets the jump modifier
     */
    void setJumpModifier(final int modifier);

    /**
     * Sets the speed modifier
     */
    void setSpeedModifier(final int modifier);

    /**
     * Checks if the player has won
     *
     * @return true if the player has won, false otherwise
     */
    boolean hasWon();

    /**
     * Let the player win the game.
     */
    void win();
}
