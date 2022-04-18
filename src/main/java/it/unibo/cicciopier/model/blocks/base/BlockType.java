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
    GRASS,
    /**
     * Represents a dirt block.
     */
    DIRT,
    /**
     * Represents a cursed dirt block.
     */
    CURSED_DIRT,
    /**
     * Represents a stone block.
     */
    STONE,
    /**
     * Represents a stone brick block.
     */
    STONE_BRICK,
    /**
     * Represents a cracked stone brick block.
     */
    CRACKED_STONE_BRICK,
    /**
     * Represents a mossy stone brick block.
     */
    MOSSY_STONE_BRICK,
    /**
     * Represents a chiseled stone brick block.
     */
    CHISELED_STONE_BRICK,
    /**
     * Represents a coal ore block.
     */
    COAL_ORE,
    /**
     * Represents a wood plank block.
     */
    WOOD_PLANK,
    /**
     * Represents a wood log block.
     */
    WOOD_LOG_SIDE,
    /**
     * Represents a wood log block.
     */
    WOOD_LOG_TOP,
    /**
     * Represents a cobblestone block.
     */
    COBBLESTONE,
    /**
     * Represents a mossy cobblestone block.
     */
    MOSSY_COBBLESTONE,
    /**
     * Represents a white tulip block.
     */
    WHITE_TULIP(false),
    /**
     * Represents a red tulip block.
     */
    RED_TULIP(false),
    /**
     * Represents a glow stone block.
     */
    GLOW_STONE(false),
    /**
     * Represents a lapis ore block.
     */
    LAPIS_ORE,
    /**
     * Represents a diamond ore block.
     */
    DIAMOND_ORE,
    /**
     * Represents a nether dirt block.
     */
    NETHER_DIRT,
    /**
     * Represents a quartz ore block.
     */
    QUARTZ_ORE,
    /**
     * Represents a nether gold ore block.
     */
    NETHER_GOLD_ORE,
    /**
     * Represents a red nether brick block.
     */
    RED_NETHER_BRICK(false),
    /**
     * Represents a nether brick block.
     */
    NETHER_BRICK,
    /**
     * Represents a cracked nether brick block.
     */
    CRACKED_NETHER_BRICK,
    /**
     * Represents a chiseled nether brick block.
     */
    CHISELED_NETHER_BRICK,
    /**
     * Represents an obsidian block.
     */
    OBSIDIAN(false),
    /**
     * Represents a portal block.
     */
    PORTAL(false, true),
    /**
     * Represents a nether flower block.
     */
    NETHER_FLOWER(false),
    /**
     * Represents a graveyard grass block.
     */
    GRAVEYARD_GRASS,
    /**
     * Represents a grave block.
     */
    GRAVE_1(false),
    /**
     * Represents a grave block.
     */
    GRAVE_2(false),
    /**
     * Represents a grave block.
     */
    GRAVE_3_TOP(false),
    /**
     * Represents a grave block.
     */
    GRAVE_4_TOP(false),
    /**
     * Represents a grave block.
     */
    GRAVE_5_TOP(false),
    /**
     * Represents a dead tree block.
     */
    DEAD_TREE_1_TOP(false),
    /**
     * Represents a dead tree block.
     */
    DEAD_TREE_2_TOP(false),
    /**
     * Represents a mossy rock block.
     */
    MOSSY_ROCK_LEFT(false),
    /**
     * Represents a mossy rock block.
     */
    MOSSY_ROCK_RIGHT(false),
    /**
     * Represents a graveyard dirt block.
     */
    GRAVEYARD_DIRT,
    /**
     * Represents a grave block.
     */
    GRAVE_6(false),
    /**
     * Represents a grave block.
     */
    GRAVE_7(false),
    /**
     * Represents a grave block.
     */
    GRAVE_3_BOTTOM(false),
    /**
     * Represents a grave block.
     */
    GRAVE_4_BOTTOM(false),
    /**
     * Represents a grave block.
     */
    GRAVE_5_BOTTOM(false),
    /**
     * Represents a dead tree block.
     */
    DEAD_TREE_1_BOTTOM(false),
    /**
     * Represents a dead tree block.
     */
    DEAD_TREE_2_BOTTOM(false),
    /**
     * Represents a mausoleum block.
     */
    MAUSOLEUM_TOP_LEFT(false, true),
    /**
     * Represents a mausoleum block.
     */
    MAUSOLEUM_TOP_RIGHT(false, true),
    /**
     * Represents a graveyard stone block.
     */
    GRAVEYARD_STONE,
    /**
     * Represents a boss brick block.
     */
    BOSS_BRICK,
    /**
     * Represents a boss brick block.
     */
    BOSS_BRICK_LEFT,
    /**
     * Represents a boss brick block.
     */
    BOSS_BRICK_LEFT_TOP,
    /**
     * Represents a boss brick block.
     */
    BOSS_BRICK_TOP_FLAT,
    /**
     * Represents a boss brick block.
     */
    BOSS_BRICK_RIGHT_TOP,
    /**
     * Represents a boss brick block.
     */
    BOSS_BRICK_RIGHT,
    /**
     * Represents a boss brick block.
     */
    BOSS_BRICK_TOP_ARC,
    /**
     * Represents a mausoleum block.
     */
    MAUSOLEUM_BOTTOM_LEFT(false, true),
    /**
     * Represents a mausoleum block.
     */
    MAUSOLEUM_BOTTOM_RIGHT(false, true),
    /**
     * Represents a fire block.
     */
    FIRE(false);

    private final boolean solid;
    private final boolean interact;

    /**
     * Create a new block type given the texture coordinates. Solid block.
     */
    BlockType() {
        this(true);
    }

    /**
     * Create a new block type given the texture coordinates.
     *
     * @param solid if the block is solid or not
     */
    BlockType(final boolean solid) {
        this(solid, false);
    }

    /**
     * Create a new block type given the texture coordinates.
     *
     * @param solid    if the block is solid or not
     * @param interact if you can interact with the block or not
     */
    BlockType(final boolean solid, final boolean interact) {
        this.solid = solid;
        this.interact = interact;
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
     * Check if this type of block can be interacted with
     *
     * @return true if you can interact with it, false otherwise
     */
    public boolean canInteract() {
        return interact;
    }
}
