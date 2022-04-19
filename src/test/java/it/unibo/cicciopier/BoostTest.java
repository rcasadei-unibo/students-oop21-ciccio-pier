package it.unibo.cicciopier;

import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.items.Boost;
import it.unibo.cicciopier.utility.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BoostTest {
    private World world;

    @BeforeEach
    public void init() {
        this.world = new GameWorld();
        this.world.setWidth(100);
        this.world.setHeight(32);
        this.world.clear();
        this.world.getPlayer().setPos(new Vector2d(0, 0));
    }
    @Test
    public void speedBoostTest(){
        assertSame(0, this.world.getPlayer().getSpeedModifier());
        Optional<Entity> e = this.world.getEntityFactory().createEntity(EntityType.SPEED_BOOST);
        if(e.isEmpty()){
            fail("Cannot create boost");
        }
        Boost speedBoost = (Boost) e.get();
        speedBoost.setPos(new Vector2d(10,32));
        int ticks = 0;
        this.world.getPlayer().tick(ticks);
        speedBoost.tick(ticks);
        assertSame(2, this.world.getPlayer().getSpeedModifier());
    }
    @Test
    public void invulnerabilityBoostTest(){
        assertFalse(this.world.getPlayer().isInvulnerable());
        Optional<Entity> e = this.world.getEntityFactory().createEntity(EntityType.INVULNERABILITY_BOOST);
        if(e.isEmpty()){
            fail("Cannot create boost");
        }
        Boost speedBoost = (Boost) e.get();
        speedBoost.setPos(new Vector2d(10,32));
        int ticks = 0;
        this.world.getPlayer().tick(ticks);
        speedBoost.tick(ticks);
        assertTrue(this.world.getPlayer().isInvulnerable());
    }
    @Test
    public void jumpBoostTest(){
        assertSame(0, this.world.getPlayer().getJumpModifier());
        Optional<Entity> e = this.world.getEntityFactory().createEntity(EntityType.JUMP_BOOST);
        if(e.isEmpty()){
            fail("Cannot create boost");
        }
        Boost speedBoost = (Boost) e.get();
        speedBoost.setPos(new Vector2d(10,32));
        int ticks = 0;
        this.world.getPlayer().tick(ticks);
        speedBoost.tick(ticks);
        assertSame(3, this.world.getPlayer().getJumpModifier());
    }
}
