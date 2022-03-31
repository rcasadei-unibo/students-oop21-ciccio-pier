package it.unibo.cicciopier.model.blocks;

import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.blocks.base.SimpleBlock;

import java.util.Optional;

/**
 * Simple implementation of the interface {@link BlockFactory}.
 */
public class SimpleBlockFactory implements BlockFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Block> createBlock(final BlockType type) {
        return Optional.of(new SimpleBlock(type));
    }

}
