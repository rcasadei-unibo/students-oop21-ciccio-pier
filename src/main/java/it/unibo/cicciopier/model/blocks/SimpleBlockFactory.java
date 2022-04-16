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
        if (type == BlockType.MAUSOLEUM_TOP_LEFT || type == BlockType.MAUSOLEUM_TOP_RIGHT
                || type == BlockType.MAUSOLEUM_BOTTOM_LEFT || type == BlockType.MAUSOLEUM_BOTTOM_RIGHT
                || type == BlockType.PORTAL) {
            return Optional.of(new VictoryBlock(type));
        }
        return Optional.of(new SimpleBlock(type));
    }

}
