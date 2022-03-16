package it.unibo.cicciopier.model.blocks.base;

import it.unibo.cicciopier.model.SimpleGameObject;
import it.unibo.cicciopier.view.GameObjectView;

/**
 * Simple implementation of the interface {@link Block}.
 */
public class SimpleBlock extends SimpleGameObject implements Block {
    private BlockType type;

    /**
     * Constructor for this class, it instantiates a block with the specific {@link BlockType}.
     */
    public SimpleBlock(final BlockType type) {
        super();
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(final BlockType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return null;
    }
}
