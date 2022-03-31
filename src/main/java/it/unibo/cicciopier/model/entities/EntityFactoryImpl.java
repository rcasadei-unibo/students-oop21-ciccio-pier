package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.NinjaPotato;
import it.unibo.cicciopier.model.entities.enemies.ShootingPea;
import it.unibo.cicciopier.model.entities.enemies.boss.CannonBall;
import it.unibo.cicciopier.model.entities.enemies.boss.Explosion;
import it.unibo.cicciopier.model.entities.enemies.boss.Laser;
import it.unibo.cicciopier.model.entities.enemies.boss.Missile;
import it.unibo.cicciopier.model.items.Chicken;
import it.unibo.cicciopier.model.items.Coin;

import java.util.Optional;

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
        return new PlayerImpl(this.world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Entity> createEntity(final EntityType type) {
        switch (type) {
            case PLAYER:
                return Optional.of(this.createPlayer());
            case MISSILE:
                return Optional.of(new Missile(this.world));
            case LASER:
                return Optional.of(new Laser(this.world));
            case CANNON_BALL:
                return Optional.of(new CannonBall(this.world));
            case COIN:
                return Optional.of(new Coin(this.world));
            case CHICKEN:
                return Optional.of(new Chicken(this.world));
            case EXPLOSION:
                return Optional.of(new Explosion(this.world));
            case SHOOTING_PEA:
                return Optional.of(new ShootingPea(this.world));
            case NINJA_POTATO:
                return Optional.of(new NinjaPotato(this.world));
            default:
                return Optional.empty();
        }
    }
}
