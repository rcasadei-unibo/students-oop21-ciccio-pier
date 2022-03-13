package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;

public abstract class SimpleEnemy extends SimpleLivingEntity implements Enemy {

    private final int attackDamage;

    /**
     * Constructor for this class
     * @param type The Entity's type
     * @param world The game's world
     */
    public SimpleEnemy(EntityType type, World world) {
        super(type, world);
        this.attackDamage = this.getType().getAttackDamage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attackPlayer() {
        this.getWorld().getPlayer().damage(this.attackDamage);
    }
}
