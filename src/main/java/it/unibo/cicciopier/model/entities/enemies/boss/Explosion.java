package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.ExplosionView;

/**
 * Create a simple explosion animation
 */
public class Explosion extends SimpleEntity {
    private static final int DURATION = 48;
    private final ExplosionView explosionView;
    private long start;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Explosion(final World world) {
        super(EntityType.EXPLOSION, world);
        this.explosionView = new ExplosionView(this);
        this.start = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.explosionView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        if(this.start == -1) {
            this.start = ticks;
        }
        if (ticks - this.start >= DURATION) {
            this.remove();
        }
    }

}
