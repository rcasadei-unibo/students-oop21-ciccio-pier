package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.NutView;

/**
 * Class representing the Nut projectile.
 * The nut is a special projectile, since it does not move.
 */
public class Nut extends SimpleProjectile {

    private NutView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Nut(final World world) {
        super(EntityType.NUT, world, 0);
        this.view = new NutView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        if (this.checkCollision(this.getWorld().getPlayer())) {
            this.remove();
            this.createExplosion();
            this.getWorld().getPlayer().damage(this.getType().getAttackDamage());
        }
    }
}
