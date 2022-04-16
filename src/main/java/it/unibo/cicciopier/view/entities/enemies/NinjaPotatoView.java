package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.enemies.NinjaPotato;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.model.entities.enemies.EnemyState;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleLivingEntityView;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the view aspect of a NinjaPotato enemy
 */
public class NinjaPotatoView extends SimpleLivingEntityView {

    /**
     * Animations map for the NinjaPotato enemy
     */
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final Texture texture = Texture.NINJA_POTATO;
            final int w = EntityType.NINJA_POTATO.getWidth();
            final int h = EntityType.NINJA_POTATO.getHeight();
            put(EnemyState.HIDDEN, new Animation(texture, 5, 25, new Pair<>(0, 0), w, h));
            put(EnemyState.IDLE, new Animation(texture, 5, 25, new Pair<>(0, h), w, h));
            put(EnemyState.JUMPING_OUT, new Animation(texture, 6, (int)Math.ceil(NinjaPotato.JUMP_TICKS / 6d), new Pair<>(0, h * 2), w, h));
            put(EnemyState.JUMPING_IN, new Animation(texture, 6, (int)Math.ceil(NinjaPotato.JUMP_TICKS / 6d), new Pair<>(0, h * 3), w, h));
            put(EnemyState.SLASH_OUT, new Animation(texture, 10, (int)Math.ceil(NinjaPotato.SLASH_OUT_TICK_DURATION / 10d), new Pair<>(0, h * 4), w, h));
            put(EnemyState.SLASH_IN, new Animation(texture, 10, (int)Math.ceil(NinjaPotato.SLASH_IN_TICK_DURATION / 10d), new Pair<>(0, h * 5), w, h));
            put(EnemyState.DEAD, new Animation(texture, 11, (int)Math.ceil(SimpleEnemy.DEATH_DURATION / 11d), new Pair<>(0, h * 6), w, h));
        }
    };

    private final NinjaPotato ninjaPotato;

    /**
     * Constructor for this class
     *
     * @param ninjaPotato The NinjaPotato of this view
     */
    public NinjaPotatoView(final NinjaPotato ninjaPotato) {
        this.ninjaPotato = ninjaPotato;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.ninjaPotato.getCurrentState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingEntity getObject() {
        return this.ninjaPotato;
    }
}
