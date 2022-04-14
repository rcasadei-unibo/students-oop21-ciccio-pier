package it.unibo.cicciopier.model.blocks.base;

import it.unibo.cicciopier.model.SimpleGameObject;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.blocks.BlockView;

/**
 * Simple implementation of the interface {@link Block}.
 */
public class SimpleBlock extends SimpleGameObject implements Block {
    private final BlockView view;
    private BlockType type;

    /**
     * Constructor for this class, it instantiates a block with the specific {@link BlockType}.
     *
     * @param type block type
     */
    public SimpleBlock(final BlockType type) {
        super();
        this.type = type;
        this.view = new BlockView(this);
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
    public boolean isSolid() {
        return this.type.isSolid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

}
