package it.unibo.cicciopier;

import it.unibo.cicciopier.controller.TmxWorldLoader;
import it.unibo.cicciopier.controller.WorldLoader;
import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.*;
import it.unibo.cicciopier.utility.Vector2d;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EnemiesTest {
    private static World world;
    private int ticks;

    @BeforeAll
    private static void init() {
        world = new GameWorld();
        WorldLoader loader = new TmxWorldLoader(world, "level-test.tmx");
        try {
            loader.load();
            loader.create();
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    @Order(1)
    @DisplayName("ShootingPea test")
    public void shootingPeaTest() {
        this.ticks = 0;
        ShootingPea shootingPea = (ShootingPea) world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.SHOOTING_PEA).findFirst().get();
        assertEquals(544, shootingPea.getPos().getX(), "Coordinate X is wrong for SHOOTING_PEA.");
        assertEquals(128, shootingPea.getPos().getY(), "Coordinate Y is wrong for SHOOTING_PEA.");
        assertSame(EnemyState.IDLE, shootingPea.getCurrentState());
        world.getPlayer().setPos(shootingPea.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));
        shootingPea.tick(++this.ticks);
        assertSame(EntityState.ATTACKING, shootingPea.getCurrentState());
        world.getPlayer().setPos(shootingPea.getPos().addVector(new Vector2d(Block.SIZE * 2, 0)));
        shootingPea.tick(++this.ticks);
        assertSame(EntityState.IDLE, shootingPea.getCurrentState());
    }

    @Test
    @Order(2)
    @DisplayName("NinjaPotato test")
    public void ninjaPotatoTest() {
        this.ticks = 0;
        NinjaPotato ninjaPotato = (NinjaPotato) world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.NINJA_POTATO).findFirst().get();
        assertEquals(1024, ninjaPotato.getPos().getX(), "Coordinate X is wrong for NINJA_POTATO.");
        assertEquals(128, ninjaPotato.getPos().getY(), "Coordinate Y is wrong for NINJA_POTATO.");
        world.getPlayer().setPos(ninjaPotato.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));
        ninjaPotato.tick(++this.ticks);
        assertSame(EnemyState.JUMPING_OUT, ninjaPotato.getCurrentState());
        for (int i = 0; i < NinjaPotato.JUMP_TICKS + NinjaPotato.SLASH_OUT_TICK_DURATION + NinjaPotato.SLASH_IN_TICK_DURATION; i++) {
            ninjaPotato.tick(++this.ticks);
        }
        assertSame(EntityState.IDLE, ninjaPotato.getCurrentState());
        world.getPlayer().setPos(ninjaPotato.getPos().addVector(new Vector2d(-(Block.SIZE * 5), 0)));
        for (int i = 0; i < NinjaPotato.IDLE_DURATION + NinjaPotato.JUMP_TICKS; i++) {
            ninjaPotato.tick(++this.ticks);
        }
        assertSame(EnemyState.HIDDEN, ninjaPotato.getCurrentState());
    }

    @Test
    @Order(3)
    @DisplayName("RollingPeach test")
    public void rollingPeachTest() {
        this.ticks = 0;
        RollingPeach rollingPeach = (RollingPeach) world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.ROLLING_PEACH).findFirst().get();
        assertEquals(1600, rollingPeach.getPos().getX(), "Coordinate X is wrong for ROLLING_PEACH.");
        assertEquals(128, rollingPeach.getPos().getY(), "Coordinate Y is wrong for ROLLING_PEACH.");
        world.getPlayer().setPos(rollingPeach.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));
        rollingPeach.tick(++this.ticks);
        assertSame(EnemyState.ANGERED, rollingPeach.getCurrentState());
        for (int i = 0; i <= RollingPeach.ANGER_DURATION_TICKS; i++) {
            rollingPeach.tick(++this.ticks);
        }
        assertSame(EnemyState.ANGERED_RUNNING, rollingPeach.getCurrentState());
    }

    @Test
    @Order(4)
    @DisplayName("CryingOnion test")
    public void cryingOnionTest() {
        this.ticks = 0;
        CryingOnion cryingOnion = (CryingOnion) world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.CRYING_ONION).findFirst().get();
        assertEquals(416, cryingOnion.getPos().getX(), "Coordinate X is wrong for CRYING_ONION.");
        assertEquals(352, cryingOnion.getPos().getY(), "Coordinate Y is wrong for CRYING_ONION.");
        world.getPlayer().setPos(cryingOnion.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));
        cryingOnion.tick(++this.ticks);
        assertSame(EnemyState.ANGERED, cryingOnion.getCurrentState());
        for (int i = 0; i <= CryingOnion.ANGER_DURATION_TICKS; i++) {
            cryingOnion.tick(++this.ticks);
        }
        assertSame(EnemyState.ANGERED_RUNNING, cryingOnion.getCurrentState());
    }

    @Test
    @Order(5)
    @DisplayName("MindPineapple test")
    public void mindPineappleTest() {
        this.ticks = 0;
        MindPineapple mindPineapple = (MindPineapple) world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.MIND_PINEAPPLE).findFirst().get();
        assertEquals(832, mindPineapple.getPos().getX(), "Coordinate X is wrong for MIND_PINEAPPLE.");
        assertEquals(320, mindPineapple.getPos().getY(), "Coordinate Y is wrong for MIND_PINEAPPLE.");
        world.getPlayer().setPos(mindPineapple.getPos().addVector(new Vector2d(-Block.SIZE * 2, Block.SIZE)));
        mindPineapple.tick(++this.ticks);
        assertSame(EnemyState.ANGERED, mindPineapple.getCurrentState());
        for (int i = 0; i <= MindPineapple.ANGERED_TICKS; i++) {
            mindPineapple.tick(++this.ticks);
        }
        assertSame(EnemyState.ATTACKING, mindPineapple.getCurrentState());
        world.getPlayer().setPos(mindPineapple.getPos().addVector(new Vector2d(Block.SIZE * 2, Block.SIZE)));
        mindPineapple.tick(++this.ticks);
        assertSame(EntityState.IDLE, mindPineapple.getCurrentState());
    }

    @Test
    @Order(6)
    @DisplayName("Player's attack test")
    public void playerAttackTest() {
        this.ticks = 0;
        ShootingPea shootingPea = (ShootingPea) world.getEntities().stream()
                .filter(e -> e.getType() == EntityType.SHOOTING_PEA).findFirst().get();
        assertSame(EnemyState.IDLE, shootingPea.getCurrentState());
        world.getPlayer().setPos(shootingPea.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));
        shootingPea.tick(++this.ticks);
        assertSame(EnemyState.ATTACKING, shootingPea.getCurrentState());
        world.getPlayer().attackNearest();
        assertSame(EntityType.SHOOTING_PEA.getMaxHp() - EntityType.PLAYER.getAttackDamage(), shootingPea.getHp());
        shootingPea.setPos(shootingPea.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));
        //assertEquals(480, shootingPea.getPos().getX(), "Coordinate X is wrong for SHOOTING_PEA.");
        //assertEquals(128, shootingPea.getPos().getY(), "Coordinate Y is wrong for SHOOTING_PEA.");
    }

    /*

            this.ticks = 0;
        ShootingPea shootingPea = (ShootingPea) world.getEntityFactory().createEntity(EntityType.SHOOTING_PEA).get();
        shootingPea.setPos(new Vector2d(544, 128));
        assertSame(EnemyState.IDLE, shootingPea.getCurrentState());
        world.getPlayer().setPos(shootingPea.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));
        world.getPlayer().attackNearest();
        assertSame(EntityType.SHOOTING_PEA.getMaxHp()-EntityType.PLAYER.getAttackDamage(),shootingPea.getHp()); ///????
        shootingPea.setPos(shootingPea.getPos().addVector(new Vector2d(-Block.SIZE * 2, 0)));

     */

    /*

    Test da fare:
    creazione enemies
    aggro enemies
    danno enemies + invulnerability?
    attacco player (in qualche modo)

     */


}
