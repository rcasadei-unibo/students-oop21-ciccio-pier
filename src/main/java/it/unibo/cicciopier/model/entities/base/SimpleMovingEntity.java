package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.GameObject;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.utility.Vector2d;

public abstract class SimpleMovingEntity extends SimpleEntity implements MovingEntity{

    private Vector2d vel;

    /**
     * Constructor for this class
     * @param type The Entity's type
     * @param world The game's world
     */
    protected SimpleMovingEntity(final EntityType type, final World world) {
        super(type, world);
        this.vel = new Vector2d(0,0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d getVel() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVel(final Vector2d vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkCollision(final GameObject object) {
        return this.getBounds().intersects(object.getBounds());
    }
}
