package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.*;
import it.unibo.cicciopier.model.entities.enemies.boss.*;
import it.unibo.cicciopier.model.items.Chicken;
import it.unibo.cicciopier.model.items.Coin;
import it.unibo.cicciopier.model.items.JumpBoost;

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
            case METEOR:
                return Optional.of(new Meteor(this.world));
            case BROCCOLI:
                return Optional.of(new Broccoli(this.world));
            case LASER:
                return Optional.of(new Laser(this.world));
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
            case ROLLING_PEACH:
                return Optional.of(new RollingPeach(this.world));
            case CRYING_ONION:
                return Optional.of(new CryingOnion(this.world));
            case MIND_PINEAPPLE:
                return Optional.of(new MindPineapple(this.world));
            case NUT:
                return Optional.of(new Nut(this.world));
            case PEA:
                return Optional.of(new Pea(this.world));
            case SLASH:
                return Optional.of(new Slash(this.world));
            case SPIKES:
                return Optional.of(new Spikes(this.world));
            case JUMP_BOOST:
                return Optional.of(new JumpBoost(this.world));
            default:
                return Optional.empty();
        }
    }
}
