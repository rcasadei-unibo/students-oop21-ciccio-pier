package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;

/**
 * Contains methods used for creating {@link Entity} instances.
 */
public interface EntityFactory {

    /**
     * Create the player.
     *
     * @return the created player
     */
    Player createPlayer();

    /**
     * Create an entity and add it to the game world.
     *
     * @param type the entity type
     * @return the created entity
     */
    Entity createEntity(final EntityType type);

}
