package it.unibo.cicciopier.model;

import it.unibo.cicciopier.model.blocks.BlockFactory;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.entities.EntityFactory;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;

import java.util.List;

/**
 * Contains game objects, like blocks, entities and player.
 */
public interface World extends Iterable<Block> {

    /**
     * Get the factory for creating {@link Entity} instances.
     *
     * @return the entity factory
     */
    EntityFactory getEntityFactory();

    /**
     * Get the factory for creating {@link Block} instances.
     *
     * @return the block factory
     */
    BlockFactory getBlockFactory();

    /**
     * Get the world's height in blocks.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Set the world's height in blocks.
     *
     * @param height the new height
     */
    void setHeight(final int height);

    /**
     * Get the world's width in blocks.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Set the world's width in blocks.
     *
     * @param width the new width
     */
    void setWidth(final int width);

    /**
     * Get the block at the specific coordinates.
     *
     * @param x pos x
     * @param y pos y
     * @return the block
     */
    Block getBlock(final int x, final int y);

    /**
     * Set the block at the specific coordinates.
     *
     * @param x    pos x
     * @param y    pos y
     * @param type the new block type
     */
    void setBlock(final int x, final int y, final BlockType type);

    /**
     * Get a list containing the entities of this world.
     *
     * @return the entities
     */
    List<Entity> getEntities();

    /**
     * Add an entity to this world.
     *
     * @param entity the entity
     */
    void addEntity(final Entity entity);

    /**
     * Get the player.
     *
     * @return the player
     */
    Player getPlayer();

    /**
     * Reset the world.
     */
    void clear();

}
