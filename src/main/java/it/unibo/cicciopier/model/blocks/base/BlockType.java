package it.unibo.cicciopier.model.blocks.base;

/**
 * Represents the block types.
 */
public enum BlockType {
    /**
     * Represents a null block.
     */
    AIR(false, 0, 0),
    /**
     * Represents a grass block.
     */
    GRASS(true, 32, 0),
    /**
     * Represents a stone brick block.
     */
    STONE_BRICK(true, 64, 0),
    /**
     * Represents a dirt block.
     */
    DIRT(true, 96, 0),
    /**
     * Represents a grass snow block.
     */
    GRASS_SNOW(true, 128, 0);

    private final boolean solid;
    private final int textureX;
    private final int textureY;

    /**
     * Create a new block type given the texture coordinates.
     *
     * @param textureX x pos
     * @param textureY y pos
     */
    BlockType(final boolean solid, final int textureX, final int textureY) {
        this.solid = solid;
        this.textureX = textureX;
        this.textureY = textureY;
    }

    /**
     * Check if this type of block is solid or not.
     *
     * @return true if solid, false otherwise
     */
    public boolean isSolid() {
        return solid;
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
