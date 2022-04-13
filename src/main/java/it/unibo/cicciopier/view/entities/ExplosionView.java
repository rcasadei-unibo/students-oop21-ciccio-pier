package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.enemies.boss.Explosion;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;

/**
 * Simple class to render an explosion
 */
public class ExplosionView extends SimpleEntityView {
    public static final Animation ANIMATION = new Animation(Texture.EXPLOSION, 8, 6,
            new Pair<>(0, 0), 64, 64);
    private final Explosion explosion;

    /**
     * Constructor for this class, create a instance of an explosion view
     *
     * @param explosion what explosion to render
     */
    public ExplosionView(final Explosion explosion) {
        this.explosion = explosion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getObject() {
        return this.explosion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATION;
    }
}
