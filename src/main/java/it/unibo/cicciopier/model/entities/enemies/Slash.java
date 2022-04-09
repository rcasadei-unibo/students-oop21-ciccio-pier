package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;

/**
 * Class representing the Slash projectile
 */
public class Slash extends SimpleProjectile {

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Slash(final World world) {
        super(EntityType.SLASH,world,Projectiles.SLASH, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
    }
}
