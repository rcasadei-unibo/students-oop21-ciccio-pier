package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.SpikesView;

/**
 * Class representing the Spikes projectile
 */
public class Spikes extends SimpleProjectile {

    private SpikesView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Spikes(final World world) {
        super(EntityType.SPIKES, world, MindPineapple.ATTACK_DURATION_TICKS);
        this.view = new SpikesView(this);
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
