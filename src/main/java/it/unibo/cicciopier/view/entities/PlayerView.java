package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.PlayerImpl;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple class to render the player
 */
public class PlayerView extends SimpleLivingEntityView {
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final int w = 52;
            final int h = 64;
            put(EntityState.IDLE, new Animation(Texture.PLAYER, 3, 6, new Pair<>(w * 6, 0), w, h));
            put(EntityState.RUNNING, new Animation(Texture.PLAYER, 7, 6, new Pair<>(w * 9, 0), w, h));
            put(EntityState.JUMPING, new Animation(Texture.PLAYER, 1, 6, new Pair<>(w * 2, 0), w, h));
            put(EntityState.ATTACKING, new Animation(Texture.PLAYER, 3, 6, new Pair<>(w * 3, 0), w, h));
            put(EntityState.DAMAGED, new Animation(Texture.PLAYER, 1, 6, new Pair<>(w, 0), w, h));
            put(EntityState.DEAD, new Animation(Texture.PLAYER, 1, 6, new Pair<>(0, 0), w, h));
        }
    };

    private final PlayerImpl player;

    /**
     * Constructor for this class, create an instance to render the player
     *
     * @param player what player to render
     */
    public PlayerView(final PlayerImpl player) {
        this.player = player;
    }

    @Override
    public LivingEntity getObject() {
        return this.player;
    }

    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.player.getCurrentState());
    }

}
