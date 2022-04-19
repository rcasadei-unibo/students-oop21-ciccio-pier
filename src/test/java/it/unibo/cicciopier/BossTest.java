package it.unibo.cicciopier;

import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.boss.BossState;
import it.unibo.cicciopier.model.entities.enemies.boss.Broccoli;
import it.unibo.cicciopier.utility.Vector2d;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {
    @Test
    @DisplayName("Test if boss change state")
    public void testBossState() {
        World world = new GameWorld();
        world.setHeight(600);
        world.setWidth(600);
        world.clear();
        final Player player = world.getPlayer();
        player.setPos(new Vector2d(0, 0));
        //create a entity

        final Optional<Entity> e = world.getEntityFactory().createEntity(EntityType.BROCCOLI);
        if (e.isEmpty()) {
            fail("Failed to create boss!");
        }
        e.get().setPos(new Vector2d(200, 0));
        final Broccoli broccoli = (Broccoli) e.get();
        assertEquals(broccoli.getMaxHp(), broccoli.getHp(), "Boss did not start with max health");
        assertEquals(BossState.IDLE, broccoli.getCurrentState(), "Boss does not start with idle state");
        for (int i = 0; i < 61; i++) {
            broccoli.tick(i);
        }
        assertEquals(BossState.SEEK, broccoli.getCurrentState(), "Boss does not change to seek state");
        for (int i = 0; i < 300; i++) {
            broccoli.tick(i);
        }
        assertNotEquals(BossState.SEEK, broccoli.getCurrentState(), "Boss does not change to attack state");
        assertNotEquals(BossState.IDLE, broccoli.getCurrentState(), "Boss does not change to attack state");
    }
}
