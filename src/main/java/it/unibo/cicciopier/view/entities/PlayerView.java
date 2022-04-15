package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.PlayerImpl;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
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
            put(EntityState.IDLE, new Animation(Texture.PLAYER, 3, 6, new Pair<>(0, 0), w, h));
            put(EntityState.RUNNING, new Animation(Texture.PLAYER, 7, 6, new Pair<>(0, h), w, h));
            put(EntityState.JUMPING, new Animation(Texture.PLAYER, 1, 6, new Pair<>(0, h * 2), w, h));
            put(EntityState.ATTACKING, new Animation(Texture.PLAYER, 4, 5, new Pair<>(0, h * 3), w, h));
            put(EntityState.DEAD, new Animation(Texture.PLAYER, 1, 6, new Pair<>(0, h * 4), w, h));
        }
    };
    public static final Animation BLOOD_ANIMATION = new Animation(Texture.BLOOD_PARTICLE, 5, 5,
            new Pair<>(0, 0), 44, 40);
    private static final int BLOOD_PARTICLE_DURATION = 25;
    private int bloodAnimationTicks;
    private final PlayerImpl player;


    /**
     * Constructor for this class, create an instance to render the player
     *
     * @param player what player to render
     */
    public PlayerView(final PlayerImpl player) {
        this.player = player;
        this.bloodAnimationTicks = -1;
    }

    @Override
    public LivingEntity getObject() {
        return this.player;
    }

    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.player.getCurrentState());
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (this.player.hasTakenDamage() && this.bloodAnimationTicks == -1) {
            this.bloodAnimationTicks = 0;
        }
        if (this.bloodAnimationTicks != -1) {
            g.drawImage(
                    BLOOD_ANIMATION.getSprite(bloodAnimationTicks / BLOOD_ANIMATION.getSpeed()),
                    this.player.getPos().getX() + this.player.getWidth() / 2 - BLOOD_ANIMATION.getWidth() / 2,
                    this.player.getPos().getY() + this.player.getHeight() / 4,
                    null);
            this.bloodAnimationTicks++;
            if (this.bloodAnimationTicks >= BLOOD_PARTICLE_DURATION) {
                this.bloodAnimationTicks = -1;
            }
        }
    }
}
