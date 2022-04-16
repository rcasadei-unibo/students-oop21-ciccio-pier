package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.enemies.MindPineapple;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.model.entities.enemies.EnemyState;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleLivingEntityView;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the view aspect of a MindPineapple enemy
 */
public class MindPineappleView extends SimpleLivingEntityView {

    /**
     * Animations map for the MindPineApple enemy
     */
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final Texture texture = Texture.MIND_PINEAPPLE;
            final int w = EntityType.MIND_PINEAPPLE.getWidth();
            final int h = EntityType.MIND_PINEAPPLE.getHeight();
            put(EnemyState.IDLE, new Animation(texture, 5, 40, new Pair<>(0, 0), w, h));
            put(EnemyState.RUNNING, new Animation(texture, 5, 20, new Pair<>(0, 0), w, h));
            put(EnemyState.ANGERED, new Animation(texture, 5, MindPineapple.ANGERED_TICKS / 5, new Pair<>(0, h * 2), w, h));
            put(EnemyState.ATTACKING, new Animation(texture, 5, 30, new Pair<>(0, h), w, h));
            put(EnemyState.DEAD, new Animation(texture, 8, (int)Math.ceil(SimpleEnemy.DEATH_DURATION / 8d), new Pair<>(0, h * 3), w, h));
        }
    };

    private final MindPineapple mindPineapple;

    /**
     * Constructor for this class
     *
     * @param mindPineapple The MindPineapple of this view
     */
    public MindPineappleView(final MindPineapple mindPineapple) {
        this.mindPineapple = mindPineapple;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.mindPineapple.getCurrentState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingEntity getObject() {
        return this.mindPineapple;
    }
}
