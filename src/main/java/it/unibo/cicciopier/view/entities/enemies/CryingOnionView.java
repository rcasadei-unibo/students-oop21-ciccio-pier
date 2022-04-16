package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.enemies.CryingOnion;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.model.entities.enemies.EnemyState;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleLivingEntityView;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the view aspect of a CryingOnion enemy
 */
public class CryingOnionView extends SimpleLivingEntityView {

    /**
     * Animations map for the CryingOnion enemy
     */
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final Texture texture = Texture.CRYING_ONION;
            final int w = EntityType.CRYING_ONION.getWidth();
            final int h = EntityType.CRYING_ONION.getHeight();
            put(EnemyState.IDLE, new Animation(texture, 5, 20, new Pair<>(0, 0), w, h));
            put(EnemyState.ANGERED, new Animation(texture, 8, 10, new Pair<>(0, h * 3), w, h));
            put(EnemyState.RUNNING, new Animation(texture, 9, 10, new Pair<>(0, h), w, h));
            put(EnemyState.ANGERED_RUNNING, new Animation(texture, 9, 5, new Pair<>(0, h * 2), w, h));
            put(EnemyState.DEAD, new Animation(texture, 9, (int)Math.ceil(SimpleEnemy.DEATH_DURATION / 9d), new Pair<>(0, h * 4), w, h));
        }
    };

    private final CryingOnion cryingOnion;

    /**
     * Constructor for this class
     *
     * @param cryingOnion The CryingOnion of this view
     */
    public CryingOnionView(final CryingOnion cryingOnion) {
        this.cryingOnion = cryingOnion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.cryingOnion.getCurrentState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingEntity getObject() {
        return this.cryingOnion;
    }
}
