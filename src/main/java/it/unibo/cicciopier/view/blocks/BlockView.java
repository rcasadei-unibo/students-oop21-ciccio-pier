package it.unibo.cicciopier.view.blocks;

import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Simple implementation of the interface {@link GameObjectView} for rendering {@link Block} instances.
 */
public class BlockView implements GameObjectView {
    private final Block block;

    /**
     * Constructor for this class.
     *
     * @param block the block to render
     */
    public BlockView(final Block block) {
        this.block = block;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        if (this.block.getType() == BlockType.AIR) {
            return;
        }
        g.drawImage(Texture.BLOCK.getTexture(),
                this.block.getPos().getX(),
                this.block.getPos().getY(),
                this.block.getPos().getX() + Block.SIZE,
                this.block.getPos().getY() + Block.SIZE,
                this.block.getType().getTextureX(),
                this.block.getType().getTextureY(),
                this.block.getType().getTextureX() + Block.SIZE,
                this.block.getType().getTextureY() + Block.SIZE,
                null);
    }

}
