package it.unibo.cicciopier.view.blocks;

import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.utility.Pair;

import java.util.EnumMap;
import java.util.Map;

/**
 * Class containing the texture coordinates for every {@link BlockType}.
 */
public class BlockTexture {
    /**
     * Map containing every {@link BlockType} with it's {@link Pair} of texture coordinates.
     */
    private static final Map<BlockType, Pair<Integer>> TEXTURES = new EnumMap<>(BlockType.class) {
        {
            put(BlockType.AIR, new Pair<>(0, 0));
            put(BlockType.GRASS, new Pair<>(Block.SIZE, 0));
            put(BlockType.DIRT, new Pair<>(Block.SIZE * 2, 0));
            put(BlockType.CURSED_DIRT, new Pair<>(Block.SIZE * 3, 0));
            put(BlockType.STONE, new Pair<>(Block.SIZE * 4, 0));
            put(BlockType.STONE_BRICK, new Pair<>(Block.SIZE * 5, 0));
            put(BlockType.CRACKED_STONE_BRICK, new Pair<>(Block.SIZE * 6, 0));
            put(BlockType.MOSSY_STONE_BRICK, new Pair<>(Block.SIZE * 7, 0));
            put(BlockType.CHISELED_STONE_BRICK, new Pair<>(Block.SIZE * 8, 0));
            put(BlockType.COAL_ORE, new Pair<>(Block.SIZE * 9, 0));
            put(BlockType.WOOD_PLANK, new Pair<>(0, Block.SIZE));
            put(BlockType.WOOD_LOG_SIDE, new Pair<>(Block.SIZE, Block.SIZE));
            put(BlockType.WOOD_LOG_TOP, new Pair<>(Block.SIZE * 2, Block.SIZE));
            put(BlockType.COBBLESTONE, new Pair<>(Block.SIZE * 3, Block.SIZE));
            put(BlockType.MOSSY_COBBLESTONE, new Pair<>(Block.SIZE * 4, Block.SIZE));
            put(BlockType.WHITE_TULIP, new Pair<>(Block.SIZE * 5, Block.SIZE));
            put(BlockType.RED_TULIP, new Pair<>(Block.SIZE * 6, Block.SIZE));
            put(BlockType.GLOW_STONE, new Pair<>(Block.SIZE * 7, Block.SIZE));
            put(BlockType.LAPIS_ORE, new Pair<>(Block.SIZE * 8, Block.SIZE));
            put(BlockType.DIAMOND_ORE, new Pair<>(Block.SIZE * 9, Block.SIZE));
            put(BlockType.NETHER_DIRT, new Pair<>(0, Block.SIZE * 2));
            put(BlockType.QUARTZ_ORE, new Pair<>(Block.SIZE, Block.SIZE * 2));
            put(BlockType.NETHER_GOLD_ORE, new Pair<>(Block.SIZE * 2, Block.SIZE * 2));
            put(BlockType.RED_NETHER_BRICK, new Pair<>(Block.SIZE * 3, Block.SIZE * 2));
            put(BlockType.NETHER_BRICK, new Pair<>(Block.SIZE * 4, Block.SIZE * 2));
            put(BlockType.CRACKED_NETHER_BRICK, new Pair<>(Block.SIZE * 5, Block.SIZE * 2));
            put(BlockType.CHISELED_NETHER_BRICK, new Pair<>(Block.SIZE * 6, Block.SIZE * 2));
            put(BlockType.OBSIDIAN, new Pair<>(Block.SIZE * 7, Block.SIZE * 2));
            put(BlockType.PORTAL, new Pair<>(Block.SIZE * 8, Block.SIZE * 2));
            put(BlockType.NETHER_FLOWER, new Pair<>(Block.SIZE * 9, Block.SIZE * 2));
            put(BlockType.GRAVEYARD_GRASS, new Pair<>(0, Block.SIZE * 3));
            put(BlockType.GRAVE_1, new Pair<>(Block.SIZE, Block.SIZE * 3));
            put(BlockType.GRAVE_2, new Pair<>(Block.SIZE * 2, Block.SIZE * 3));
            put(BlockType.GRAVE_3_TOP, new Pair<>(Block.SIZE * 3, Block.SIZE * 3));
            put(BlockType.GRAVE_4_TOP, new Pair<>(Block.SIZE * 4, Block.SIZE * 3));
            put(BlockType.GRAVE_5_TOP, new Pair<>(Block.SIZE * 5, Block.SIZE * 3));
            put(BlockType.DEAD_TREE_1_TOP, new Pair<>(Block.SIZE * 6, Block.SIZE * 3));
            put(BlockType.DEAD_TREE_2_TOP, new Pair<>(Block.SIZE * 7, Block.SIZE * 3));
            put(BlockType.MOSSY_ROCK_LEFT, new Pair<>(Block.SIZE * 8, Block.SIZE * 3));
            put(BlockType.MOSSY_ROCK_RIGHT, new Pair<>(Block.SIZE * 9, Block.SIZE * 3));
            put(BlockType.GRAVEYARD_DIRT, new Pair<>(0, Block.SIZE * 4));
            put(BlockType.GRAVE_6, new Pair<>(Block.SIZE, Block.SIZE * 4));
            put(BlockType.GRAVE_7, new Pair<>(Block.SIZE * 2, Block.SIZE * 4));
            put(BlockType.GRAVE_3_BOTTOM, new Pair<>(Block.SIZE * 3, Block.SIZE * 4));
            put(BlockType.GRAVE_4_BOTTOM, new Pair<>(Block.SIZE * 4, Block.SIZE * 4));
            put(BlockType.GRAVE_5_BOTTOM, new Pair<>(Block.SIZE * 5, Block.SIZE * 4));
            put(BlockType.DEAD_TREE_1_BOTTOM, new Pair<>(Block.SIZE * 6, Block.SIZE * 4));
            put(BlockType.DEAD_TREE_2_BOTTOM, new Pair<>(Block.SIZE * 7, Block.SIZE * 4));
            put(BlockType.MAUSOLEUM_TOP_LEFT, new Pair<>(Block.SIZE * 8, Block.SIZE * 4));
            put(BlockType.MAUSOLEUM_TOP_RIGHT, new Pair<>(Block.SIZE * 9, Block.SIZE * 4));
            put(BlockType.GRAVEYARD_STONE, new Pair<>(0, Block.SIZE * 5));
            put(BlockType.BOSS_BRICK, new Pair<>(Block.SIZE, Block.SIZE * 5));
            put(BlockType.BOSS_BRICK_LEFT, new Pair<>(Block.SIZE * 2, Block.SIZE * 5));
            put(BlockType.BOSS_BRICK_LEFT_TOP, new Pair<>(Block.SIZE * 3, Block.SIZE * 5));
            put(BlockType.BOSS_BRICK_TOP_FLAT, new Pair<>(Block.SIZE * 4, Block.SIZE * 5));
            put(BlockType.BOSS_BRICK_RIGHT_TOP, new Pair<>(Block.SIZE * 5, Block.SIZE * 5));
            put(BlockType.BOSS_BRICK_RIGHT, new Pair<>(Block.SIZE * 6, Block.SIZE * 5));
            put(BlockType.BOSS_BRICK_TOP_ARC, new Pair<>(Block.SIZE * 7, Block.SIZE * 5));
            put(BlockType.MAUSOLEUM_BOTTOM_LEFT, new Pair<>(Block.SIZE * 8, Block.SIZE * 5));
            put(BlockType.MAUSOLEUM_BOTTOM_RIGHT, new Pair<>(Block.SIZE * 9, Block.SIZE * 5));
        }
    };

    /**
     * Get texture coordinates for the given {@link BlockType}
     *
     * @param type the block type
     * @return a {@link Pair} representing the coordinates
     */
    public static Pair<Integer> getCoordinates(final BlockType type) {
        return TEXTURES.get(type);
    }

}
