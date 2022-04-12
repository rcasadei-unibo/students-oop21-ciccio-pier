package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.enemies.BossState;
import it.unibo.cicciopier.model.entities.enemies.boss.Broccoli;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleEntityView;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple class to render the boss Broccoli
 */
public class BroccoliView extends SimpleEntityView {
    private static final int HEALTH_BAR_WIDTH = 160;
    private static final int HEALTH_BAR_HEIGHT = 20;
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final int w = 252;
            final int h = 384;
            put(BossState.IDLE, new Animation(Texture.BROCCOLI, 8, 6, new Pair<>(0, 0), w, h));
            put(BossState.SEEK, new Animation(Texture.BROCCOLI, 8, 6, new Pair<>(0, 0), w, h));
            put(BossState.MISSILE_LAUNCHER, new Animation(Texture.BROCCOLI, 4, 6, new Pair<>(w * 15, 0), w, h));
            put(BossState.LASER, new Animation(Texture.BROCCOLI, 4, 6, new Pair<>(w * 11, 0), w, h));
            put(BossState.METEOR_SHOWER, new Animation(Texture.BROCCOLI, 3, 6, new Pair<>(w * 8, 0), w, h));
            put(BossState.DEAD, new Animation(Texture.BROCCOLI, 8, 6, new Pair<>(0, 0), w, h));
        }
    };

    private final Broccoli broccoli;

    /**
     * Constructor for this class, create a instance of BroccoliView
     *
     * @param broccoli what broccoli to render
     */
    public BroccoliView(final Broccoli broccoli) {
        this.broccoli = broccoli;
        this.setTextureOffSet(new Pair<>(-75, -40));
    }

    @Override
    public LivingEntity getObject() {
        return this.broccoli;
    }

    @Override
    public Animation getAnimation() {
        return ANIMATIONS.get(this.broccoli.getCurrentState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        super.render(g);
        //draw boss healthBar
        g.drawImage(
                Texture.ENTITY_HEALTH_BAR_DECORATION.getTexture(),
                this.broccoli.getPos().getX() - 30,
                this.broccoli.getPos().getY() - 60,
                null
        );
        final int currentHealth = (this.broccoli.getHp() * HEALTH_BAR_WIDTH) / this.broccoli.getMaxHp();
        if (currentHealth > 0) {
            g.drawImage(
                    Texture.ENTITY_HEALTH_BAR.getTexture().getSubimage(0, 0, currentHealth, HEALTH_BAR_HEIGHT),
                    this.broccoli.getPos().getX() - 30,
                    this.broccoli.getPos().getY() - 60,
                    null
            );
        }
    }
}
