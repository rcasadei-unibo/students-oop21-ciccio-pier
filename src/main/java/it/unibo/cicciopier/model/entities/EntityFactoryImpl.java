package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;

public class EntityFactoryImpl implements EntityFactory{

    private final World world;

    /**
     * Constructor for this class
     * @param world The game's world
     */
    public EntityFactoryImpl(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player createPlayer() {
        return new PlayerImpl(EntityType.PLAYER,this.world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createEntity(final EntityType type) {
        switch (type) {
            case PLAYER:
                return this.createPlayer();
            default:
                return null;
        }
    }
}
