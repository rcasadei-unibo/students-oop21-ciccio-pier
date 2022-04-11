package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.ExplosionView;

public class Explosion extends SimpleEntity {
    private final ExplosionView explosionView;
    private boolean isFinish;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Explosion(final World world) {
        super(EntityType.EXPLOSION, world);
        this.explosionView = new ExplosionView(this);
        this.isFinish = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.explosionView;
    }

    /**
     * Set finish equals to true
     */
    public void finished() {
        this.isFinish = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        if (this.isFinish) {
            this.remove();
        }
    }

}
