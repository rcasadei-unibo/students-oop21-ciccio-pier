package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.model.entities.base.Entity;

/**
 * Simple interface representing an item
 */
public interface Item extends Entity {

    /**
     * Action executed when the item is picked up
     *
     * @param ticks the current game tick
     */
    void onPickup(final long ticks);

}
