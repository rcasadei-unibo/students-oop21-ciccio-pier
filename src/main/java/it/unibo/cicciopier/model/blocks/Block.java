package it.unibo.cicciopier.model.blocks;

import it.unibo.cicciopier.model.GameObject;
import it.unibo.cicciopier.model.World;

/**
 * Represents a block in the {@link World}.
 */
public interface Block extends GameObject {
    /**
     * Constant representing the size of the block.
     */
    int SIZE = 32;

    /**
     * Get the width of the block.
     *
     * @return the width
     */
    @Override
    default int getWidth() {
        return SIZE;
    }

    /**
     * Get the height of the block.
     *
     * @return the height
     */
    @Override
    default int getHeight() {
        return SIZE;
    }

    /**
     * Get the type of the block.
     *
     * @return the type
     */
    BlockType getType();

    /**
     * Set the type of the block.
     *
     * @param type the new type
     */
    void setType(final BlockType type);

}
