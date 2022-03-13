package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.SimpleGameObject;
import it.unibo.cicciopier.model.World;

public abstract class SimpleEntity extends SimpleGameObject implements Entity {

    private final World world;
    private final EntityType type;
    private boolean removed;

    /**
     * Constructor for this class
     * @param type The Entity's type
     * @param world The game's world
     */
    protected SimpleEntity(EntityType type, World world) {
        super();
        this.type = type;
        this.world = world;
        this.removed = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.type.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.type.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRemoved() {
        return this.removed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        this.removed = true;
    }

    /**
     * World's getter
     * @return The game's world
     */
    protected World getWorld(){
        return this.world;
    }
}
