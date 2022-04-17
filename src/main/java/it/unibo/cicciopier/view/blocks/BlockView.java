package it.unibo.cicciopier.view.blocks;

import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Simple implementation of the interface {@link GameObjectView} for rendering {@link Block} instances.
 */
public class BlockView implements GameObjectView {
    private static int BLOCKS_PER_ROW;
    private final Block block;
    private final int x;
    private final int y;

    /**
     * Constructor for this class.
     *
     * @param block the block to render
     */
    public BlockView(final Block block) {
        this.block = block;
        this.x = block.getType().ordinal() % BLOCKS_PER_ROW * Block.SIZE;
        this.y = block.getType().ordinal() / BLOCKS_PER_ROW * Block.SIZE;
    }

    public static void load() {
        BLOCKS_PER_ROW = Texture.BLOCK.getTexture().getWidth() / Block.SIZE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        if (this.block.getType() == BlockType.AIR) {
            return;
        }
        g.drawImage(Texture.BLOCK.getTexture(),
                Screen.scale(this.block.getPos().getX()),
                Screen.scale(this.block.getPos().getY()),
                Screen.scale(this.block.getPos().getX() + Block.SIZE),
                Screen.scale(this.block.getPos().getY() + Block.SIZE),
                this.x,
                this.y,
                this.x + Block.SIZE,
                this.y + Block.SIZE,
                null
        );
    }

}
