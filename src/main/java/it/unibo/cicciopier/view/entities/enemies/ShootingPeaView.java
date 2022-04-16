package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.enemies.ShootingPea;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.model.entities.enemies.EnemyState;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleLivingEntityView;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the view aspect of a ShootingPea enemy
 */
public class ShootingPeaView extends SimpleLivingEntityView {

    /**
     * Animations map for the ShootingPea enemy
     */
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final Texture texture = Texture.SHOOTING_PEA;
            final int w = EntityType.SHOOTING_PEA.getWidth();
            final int h = EntityType.SHOOTING_PEA.getHeight();
            put(EnemyState.IDLE, new Animation(texture, 5, 10, new Pair<>(0, 0), w, h));
            put(EnemyState.RUNNING, new Animation(texture, 13, 5, new Pair<>(0, h), w, h));
            put(EnemyState.ATTACKING, new Animation(texture, 9, ShootingPea.ATTACK_DURATION / 9, new Pair<>(0, h * 2), w, h));
            put(EnemyState.DEAD, new Animation(texture, 11, (int)Math.ceil(SimpleEnemy.DEATH_DURATION / 11d), new Pair<>(0, h * 3), w, h));
        }
    };

    private final ShootingPea shootingPea;

    /**
     * Constructor for this class
     *
     * @param shootingPea The ShootingPea of this view
     */
    public ShootingPeaView(final ShootingPea shootingPea) {
        this.shootingPea = shootingPea;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingEntity getObject() {
        return this.shootingPea;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.shootingPea.getCurrentState());
    }
}
