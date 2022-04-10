package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

/**
 * Represents a generic Enemy
 */
public interface Enemy extends LivingEntity {

    /**
     * Get the Entity attack damage
     *
     * @return The damage amount
     */
    int getAttackDamage();

    /**
     * Attacks the Player
     */
    void attackPlayer();

    /**
     * Method used to retrieve individual dying status
     *
     * @return The individual dying status
     */
    EnemyStatuses getDyingStatus();

    /**
     * Utility method for the view to retrieve information about the Enemy texture.
     * Specular means that on the image, the Enemy is facing right.
     *
     * @return True, if the texture is specular
     */
    boolean isTextureSpecular();

    /**
     * Method used to retrieve the score given for killing this enemy
     *
     * @return The score value
     */
    int getScoreValue();

    /**
     * Method used to retrieve the amount of health given for killing this enemy
     *
     * @return The health amount
     */
    int getHealValue();

    /**
     * Method used to retrieve the amount of stamina given for killing this enemy
     *
     * @return The stamina amount
     */
    int getStaminaValue();
}
