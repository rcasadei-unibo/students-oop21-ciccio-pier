package it.unibo.cicciopier.model.blocks;

import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.blocks.base.SimpleBlock;

/**
 * Simple implementation of the interface {@link BlockFactory}.
 */
public class SimpleBlockFactory implements BlockFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Block createBlock(final BlockType type) {
        return new SimpleBlock(type);
    }

}
