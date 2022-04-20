package it.unibo.cicciopier;

import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.ShootingPea;
import it.unibo.cicciopier.model.entities.items.Burger;
import it.unibo.cicciopier.model.entities.items.Coin;
import it.unibo.cicciopier.utility.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
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
    @DisplayName("Reduce stamina test")
    public void testStaminaReduction() {
        final Player player = this.world.getPlayer();
        player.setPos(new Vector2d(0, 0));
        final int decrease = 100;
        player.decreaseStamina(decrease);
        assertEquals(player.getMaxStamina() - decrease, player.getStamina(), "failed to reduce stamina");
        player.decreaseStamina(player.getMaxStamina());
        //create a entity
        final Optional<Entity> e = this.world.getEntityFactory().createEntity(EntityType.SHOOTING_PEA);
        if (e.isEmpty()) {
            fail("Cannot create entity!");
        }
        e.get().setPos(new Vector2d(64, 0));
        final ShootingPea shootingPea = (ShootingPea) e.get();
        shootingPea.die();
        assertTrue(player.getStamina() != 0, "failed to add stamina");
    }

    @Test
    @DisplayName("Pick up a food test")
    public void testUnhealthyFood() {
        final Player player = this.world.getPlayer();
        assertEquals(player.getMaxStamina(), player.getStamina(), "player does not start with max stamina");
        //set 0 the player stamina
        player.decreaseStamina(player.getMaxStamina());
        assertSame(0, player.getStamina(), "Stamina decrease failed");
        final Optional<Entity> e = this.world.getEntityFactory().createEntity(EntityType.BURGER);
        if (e.isEmpty()) {
            fail("Cannot create entity!");
        }
        final Burger burger = (Burger) e.get();
        // Test add
        this.world.addEntity(e.get());
        assertTrue(this.world.getEntities().contains(e.get()), "Burger add not working!");
        e.get().setPos(new Vector2d(0, 0));
        int ticks = 0;
        player.tick(ticks);
        burger.tick(ticks);
        assertTrue(player.getStamina() > 0, "failed to increase stamina");
    }

    @Test
    @DisplayName("Coin update test")
    public void testCoinUpdate() {
        final Player player = world.getPlayer();
        player.setPos(new Vector2d(0, 0));
        final Optional<Entity> e = world.getEntityFactory().createEntity(EntityType.COIN);
        if (e.isEmpty()) {
            fail("Cannot create coin!");
        }
        final Coin coin = (Coin) e.get();
        coin.setPos(new Vector2d(0, 20));
        player.tick(0);
        coin.tick(0);
        assertEquals(1, player.getCoin(), "Coin number does not match");
    }

    @Test
    @DisplayName("Coin score test")
    public void testScore() {
        final Player player = world.getPlayer();
        player.setPos(new Vector2d(0, 0));
        final Optional<Entity> e = world.getEntityFactory().createEntity(EntityType.COIN);
        if (e.isEmpty()) {
            fail("Cannot create coin!");
        }
        final Coin coin = (Coin) e.get();
        coin.setPos(new Vector2d(0, 20));
        player.tick(0);
        coin.tick(0);
        assertTrue(player.getScore() != 0, "Score was not updated");
    }

    @Test
    @DisplayName("Player health test")
    public void testHealth() {
        final Player player = this.world.getPlayer();
        player.setPos(new Vector2d(0, 0));
        assertEquals(player.getMaxHp(), player.getHp(), "Player did not start with max health");
        final int decrease = player.getMaxHp() - 1;
        player.damage(decrease);
        assertSame(player.getMaxHp() - decrease, player.getHp(), "failed to reduce hp");
        //create a entity
        final Optional<Entity> e = this.world.getEntityFactory().createEntity(EntityType.SHOOTING_PEA);
        if (e.isEmpty()) {
            fail("Cannot create entity!");
        }
        e.get().setPos(new Vector2d(64, 0));
        final ShootingPea shootingPea = (ShootingPea) e.get();
        shootingPea.die();
        assertTrue(player.getHp() > 1, "failed to heal player");
    }
}
