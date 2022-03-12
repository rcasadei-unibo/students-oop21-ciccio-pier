package it.unibo.cicciopier.model;

import it.unibo.cicciopier.utility.Vector2d;

/**
 * Simple implementation of the interface {@link GameObject}.
 */
public abstract class SimpleGameObject implements GameObject {
    private Vector2d pos;

    /**
     * Constructor for this class, it instantiates pos at 0, 0.
     */
    protected SimpleGameObject() {
        this.pos = new Vector2d(0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d getPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final Vector2d pos) {
        this.pos = pos;
    }

}