package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

public interface Enemy extends LivingEntity {

    /**
     * Get the Entity attack damage
     * @return The damage amount
     */
    int getAttackDamage();

    /**
     * Attacks the Player
     */
    void attackPlayer();
}
