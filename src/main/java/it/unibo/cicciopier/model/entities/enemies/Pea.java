package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.PeaView;

/**
 * Class representing the Pea projectile
 */
public class Pea extends SimpleProjectile {

    private PeaView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Pea(final World world) {
        super(EntityType.PEA, world, ShootingPea.ATTACK_DURATION_TICKS);
        this.view = new PeaView(this);
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
        super.tick(ticks);
    }
}
