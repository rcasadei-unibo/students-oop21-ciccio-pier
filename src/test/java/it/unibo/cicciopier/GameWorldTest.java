package it.unibo.cicciopier;

import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.utility.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GameWorldTest {
    private static final int WORLD_SIZE = 100;
    private World world;

    @BeforeEach
    public void init() {
        this.world = new GameWorld();
        this.world.setHeight(WORLD_SIZE);
        this.world.setWidth(WORLD_SIZE);
        this.world.clear();
    }

    @Test
    @DisplayName("Entities test")
    public void testEntities() {
        // Test create
        final Optional<Entity> e = this.world.getEntityFactory().createEntity(EntityType.SHOOTING_PEA);
        if(e.isEmpty()) {
            fail("Cannot create entity!");
        }
        // Test add
        this.world.addEntity(e.get());
        assertTrue(this.world.getEntities().contains(e.get()), "Entity add not working!");
        // Test range
        e.get().setPos(new Vector2d(100, 100));
        assertEquals(1, this.world.getEntitiesInRange(new Vector2d(50, 170), 100).size(), "Entity inRange not working!");
        assertEquals(1, this.world.getEntitiesInRange(new Vector2d(25, 100), 100).size(), "Entity inRange not working!");
        assertEquals(1, this.world.getEntitiesInRange(new Vector2d(175, 100), 100).size(), "Entity inRange not working!");
        assertEquals(0, this.world.getEntitiesInRange(new Vector2d(250, 100), 100).size(), "Entity inRange not working!");
        assertEquals(0, this.world.getEntitiesInRange(new Vector2d(50, 300), 100).size(), "Entity inRange not working!");
        // Test remove
        this.world.removeEntity(e.get());
        assertFalse(this.world.getEntities().contains(e.get()), "Entity remove not working!");
    }

    @Test
    @DisplayName("Blocks test")
    public void testBlocks() {
        // Test create
        final Optional<Block> b = this.world.getBlockFactory().createBlock(BlockType.DIRT);
        if(b.isEmpty()) {
            fail("Cannot create block!");
        }
        b.get().setPos(new Vector2d(0, 0));
        this.world.setBlock(0, 0, b.get());
        // Test get
        assertEquals(b.get(), this.world.getBlock(0, 0), "Block get not working!");
        assertNull(this.world.getBlock(1, 0), "Block get not working!");
        assertNull(this.world.getBlock(10, 0), "Block get not working!");
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> this.world.getBlock(WORLD_SIZE, 0), "Block get not working!");
        // Test iterator
        final Iterator<Block> i = this.world.iterator();
        assertEquals(b.get(), i.next(), "Block iterator not working!");
        assertNull(i.next(), "Block iterator not working!");
    }

}
