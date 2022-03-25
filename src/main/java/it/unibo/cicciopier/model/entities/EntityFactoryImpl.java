package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.ShootingPea;
import it.unibo.cicciopier.model.entities.enemies.boss.CannonBall;
import it.unibo.cicciopier.model.entities.enemies.boss.Explosion;
import it.unibo.cicciopier.model.entities.enemies.boss.Laser;
import it.unibo.cicciopier.model.entities.enemies.boss.Missile;
import it.unibo.cicciopier.model.items.Chicken;
import it.unibo.cicciopier.model.items.Coin;

public class EntityFactoryImpl implements EntityFactory {

    private final World world;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public EntityFactoryImpl(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player createPlayer() {
        return new PlayerImpl(EntityType.PLAYER, this.world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createEntity(final EntityType type) {
        switch (type) {
            case PLAYER:
                return this.createPlayer();
            case MISSILE:
                return new Missile(this.world);
            case LASER:
                return new Laser(this.world);
            case CANNON_BALL:
                return new CannonBall(this.world);
            case COIN:
                return new Coin(this.world);
            case CHICKEN:
                return new Chicken(this.world);
            case EXPLOSION:
                return new Explosion(this.world);
            case SHOOTING_PEA:
                return new ShootingPea(this.world);
            default:
                return null;
        }
    }
}
