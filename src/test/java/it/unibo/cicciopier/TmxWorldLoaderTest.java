package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.TmxWorldLoader;
import it.unibo.cicciopier.controller.WorldLoader;
import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TmxWorldLoaderTest {
    private static WorldLoader loader;
    private static World world;

    @BeforeAll
    private static void init() {
        world = new GameWorld();
        loader = new TmxWorldLoader(world, "level-test.tmx");
    }

    @Test
    @Order(1)
    @DisplayName("Load test")
    public void testLoad() {
        try {
            loader.load();
            world.clear();
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    @Order(2)
    @DisplayName("Dimensions test")
    public void testDimensions() {
        // Check world height & width
        assertEquals(24, world.getHeight(), "World height is wrong!");
        assertEquals(64, world.getWidth(), "World width is wrong!");
    }

    @Test
    @Order(3)
    @DisplayName("Blocks test")
    public void testLoadBlocks() {
        loader.loadBlocks();
        // Test some blocks
        assertSame(BlockType.AIR, world.getBlock(0, 0).getType(), "Block at (0,0) is of the wrong type");
        assertSame(BlockType.GRASS, world.getBlock(0, 13).getType(), "Block at (0,13) is of the wrong type");
        assertSame(BlockType.DIRT, world.getBlock(0, 23).getType(), "Block at (0,23) is of the wrong type");
    }

    @Test
    @Order(4)
    @DisplayName("Entities test")
    public void testLoadEntities() {
        loader.loadEntities();
        // Check loaded entities
        assertEquals(3, world.getEntities().size(), "Loaded entities amount is wrong!");
        // Test some entities
        final Optional<Entity> e1 = world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.SHOOTING_PEA)
                .findFirst();
        if (e1.isPresent()) {
            assertEquals(704, e1.get().getPos().getX(), "Coordinate X is wrong for SHOOTING_PEA.");
            assertEquals(128, e1.get().getPos().getY(), "Coordinate Y is wrong for SHOOTING_PEA.");
        } else {
            fail("SHOOTING_PEA not found!");
        }
        final Optional<Entity> e2 = world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.ROLLING_PEACH)
                .findFirst();
        if (e2.isEmpty()) {
            fail("ROLLING_PEACH not found!");
        }
    }

    @Test
    @Order(5)
    @DisplayName("Player test")
    public void testLoadPlayer() {
        loader.loadPlayer();
        // Check player position
        assertEquals(64, world.getPlayer().getPos().getX(), "Coordinate X is wrong for PLAYER.");
        assertEquals(352, world.getPlayer().getPos().getY(), "Coordinate Y is wrong for PLAYER.");
    }

}
