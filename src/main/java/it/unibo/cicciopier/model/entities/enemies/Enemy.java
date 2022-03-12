package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

public interface Enemy extends LivingEntity {

    /**
     * Get the Enemy attack damage
     * @return {@link Integer} representing the amount of damage it deals
     */
    int getAttackDamage();

    /**
     * Called when the Enemy attacks the player
     */
    void attackPlayer();
}
