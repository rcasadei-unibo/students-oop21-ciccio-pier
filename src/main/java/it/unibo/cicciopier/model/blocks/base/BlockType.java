package it.unibo.cicciopier.model.blocks.base;

/**
 * Represents the block types.
 */
public enum BlockType {
    /**
     * Represents a null block.
     */
    AIR(false),
    /**
     * Represents a grass block.
     */
    GRASS(true),
    /**
     * Represents a stone brick block.
     */
    STONE_BRICK(true),
    /**
     * Represents a dirt block.
     */
    DIRT(true),
    /**
     * Represents a grass snow block.
     */
    GRASS_SNOW(true);

    private final boolean solid;

    /**
     * Create a new block type given the texture coordinates.
     *
     * @param solid if the block is solid or not
     */
    BlockType(final boolean solid) {
        this.solid = solid;
    }

    /**
     * Check if this type of block is solid or not.
     *
     * @return true if solid, false otherwise
     */
    public boolean isSolid() {
        return solid;
    }

}
