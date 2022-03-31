package it.unibo.cicciopier.model.blocks;

import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;

import java.util.Optional;

/**
 * Contains methods used for creating {@link Block} instances.
 */
public interface BlockFactory {

    /**
     * Create a block and add it to the game world.
     *
     * @param type the block type
     * @return the created block
     */
    Optional<Block> createBlock(final BlockType type);

}
