package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.enemies.RollingPeach;
import it.unibo.cicciopier.model.entities.enemies.EnemyState;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleLivingEntityView;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the view aspect of a RollingPeach enemy
 */
public class RollingPeachView extends SimpleLivingEntityView {

    /**
     * Animations map for the RollingPeach enemy
     */
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final Texture texture = Texture.ROLLING_PEACH;
            final int w = EntityType.ROLLING_PEACH.getWidth();
            final int h = EntityType.ROLLING_PEACH.getHeight();
            put(EnemyState.IDLE, new Animation(texture, 6, 20, new Pair<>(0, 0), w, h));
            put(EnemyState.RUNNING, new Animation(texture, 6, 10, new Pair<>(0, h), w, h));
            put(EnemyState.ANGERED, new Animation(texture, 4, 10, new Pair<>(0, h * 2), w, h));
            put(EnemyState.ANGERED_RUNNING, new Animation(texture, 4, 5, new Pair<>(0, h * 3), w, h));
            put(EnemyState.DEAD, new Animation(texture, 9, (int)Math.ceil(SimpleEnemy.DEATH_DURATION / 9d), new Pair<>(0, h * 4), w, h));
        }
    };
    private final RollingPeach rollingPeach;

    /**
     * Constructor for this class
     *
     * @param rollingPeach The RollingPeach of this view
     */
    public RollingPeachView(final RollingPeach rollingPeach) {
        this.rollingPeach = rollingPeach;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.rollingPeach.getCurrentState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingEntity getObject() {
        return this.rollingPeach;
    }
}
