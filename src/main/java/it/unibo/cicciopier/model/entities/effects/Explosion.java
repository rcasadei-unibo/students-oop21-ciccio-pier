package it.unibo.cicciopier.model.entities.effects;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.effects.ExplosionView;

/**
 * Create a simple explosion animation
 */
public class Explosion extends SimpleEntity {
    private static final int DURATION = 48;
    private ExplosionView explosionView;
    private long start;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Explosion(final World world) {
        super(EntityType.EXPLOSION, world);
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
    public void load() {
        AudioController.getInstance().playSound(Sound.EXPLOSION);
        this.explosionView = new ExplosionView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        if (this.start == -1) {
            this.start = ticks;
            this.load();
        }
        if (ticks - this.start >= DURATION) {
            this.remove();
        }
    }

}
