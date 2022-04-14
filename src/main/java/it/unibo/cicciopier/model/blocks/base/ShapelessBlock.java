package it.unibo.cicciopier.model.blocks.base;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;

/**
 * Represents a block in the {@link World}.
 */
public interface ShapelessBlock extends Block {

    /**
     * Function executed when an entity go through this block
     *
     * @param entity the entity
     */
    void onCollision(final Entity entity);

}
