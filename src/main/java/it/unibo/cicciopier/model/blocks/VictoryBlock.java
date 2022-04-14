package it.unibo.cicciopier.model.blocks;

import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.blocks.base.SimpleBlock;
import it.unibo.cicciopier.model.blocks.base.ShapelessBlock;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;

/**
 * Simple implementation of the interface {@link ShapelessBlock} that let you win the game when go through it.
 */
public class VictoryBlock extends SimpleBlock implements ShapelessBlock {

    /**
     * Constructor for this class, it instantiates a block with the specific {@link BlockType}.
     *
     * @param type block type
     */
    public VictoryBlock(BlockType type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final Entity entity) {
        if (entity.getType() == EntityType.PLAYER) {
            final Player p = (Player) entity;
            p.win();
        }
    }
}
