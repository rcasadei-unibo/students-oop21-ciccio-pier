package it.unibo.cicciopier.model;

import it.unibo.cicciopier.model.blocks.Block;
import it.unibo.cicciopier.model.blocks.BlockType;

/**
 * Contains game objects, like blocks, entities and player.
 */
public interface World extends Iterable<Block> {

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
     * Reset the world.
     */
    void clear();

}
