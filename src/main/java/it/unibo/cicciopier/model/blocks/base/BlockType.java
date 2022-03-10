package it.unibo.cicciopier.model.blocks.base;

/**
 * Represents the block types.
 */
public enum BlockType {
    /**
     * Represents a null block.
     */
    AIR(0, 0),
    /**
     * Represents a grass block.
     */
    GRASS(32, 0),
    /**
     * Represents a stone brick block.
     */
    STONE_BRICK(64, 0),
    /**
     * Represents a dirt block.
     */
    DIRT(96, 0),
    /**
     * Represents a grass snow block.
     */
    GRASS_SNOW(128, 0);

    private final int textureX;
    private final int textureY;

    /**
     * Create a new block type given the texture coordinates.
     *
     * @param textureX x pos
     * @param textureY y pos
     */
    BlockType(final int textureX, final int textureY) {
        this.textureX = textureX;
        this.textureY = textureY;
    }

    /**
     * Get the x coordinate of the texture.
     *
     * @return x pos
     */
    public int getTextureX() {
        return textureX;
    }

    /**
     * Get the y coordinate of the texture.
     *
     * @return y pos
     */
    public int getTextureY() {
        return textureY;
    }

}
