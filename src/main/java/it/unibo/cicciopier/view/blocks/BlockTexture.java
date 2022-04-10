package it.unibo.cicciopier.view.blocks;

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
            put(BlockType.GRASS, new Pair<>(32, 0));
            put(BlockType.STONE_BRICK, new Pair<>(64, 0));
            put(BlockType.DIRT, new Pair<>(96, 0));
            put(BlockType.GRASS_SNOW, new Pair<>(128, 0));
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
