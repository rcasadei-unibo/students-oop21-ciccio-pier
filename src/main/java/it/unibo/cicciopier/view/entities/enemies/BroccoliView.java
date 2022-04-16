package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.enemies.BossState;
import it.unibo.cicciopier.model.entities.enemies.boss.Broccoli;
import it.unibo.cicciopier.model.settings.Screen;
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
    public static final Map<EntityState, Animation> ANIMATIONS = new HashMap<>() {
        {
            final int w = 252;
            final int h = 384;
            final Animation idle = new Animation(Texture.BROCCOLI, 8, 6, new Pair<>(0, 0), w, h);
            put(BossState.IDLE, idle);
            put(BossState.SEEK, idle);
            put(BossState.MISSILE_LAUNCHER, new Animation(Texture.BROCCOLI, 4, 6, new Pair<>(0, h), w, h));
            put(BossState.METEOR_SHOWER, new Animation(Texture.BROCCOLI, 3, 6, new Pair<>(0, h * 2), w, h));
            put(BossState.LASER, new Animation(Texture.BROCCOLI, 4, 6, new Pair<>(0, h * 3), w, h));
            put(BossState.DEAD, idle);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingEntity getObject() {
        return this.broccoli;
    }

    /**
     * {@inheritDoc}
     */
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
        g.drawImage(Texture.ENTITY_HEALTH_BAR_DECORATION.getTexture(),
                Screen.scale(this.broccoli.getPos().getX() - 30),
                Screen.scale(this.broccoli.getPos().getY() - 60),
                Screen.scale(Texture.ENTITY_HEALTH_BAR_DECORATION.getTexture().getWidth()),
                Screen.scale(Texture.ENTITY_HEALTH_BAR_DECORATION.getTexture().getHeight()),
                null
        );
        final int currentHealth = (this.broccoli.getHp() * Texture.ENTITY_HEALTH_BAR.getTexture().getWidth()) /
                this.broccoli.getMaxHp();
        if (currentHealth > 0) {
            g.drawImage(Texture.ENTITY_HEALTH_BAR.getTexture()
                            .getSubimage(0, 0, currentHealth, Texture.ENTITY_HEALTH_BAR.getTexture().getHeight()),
                    Screen.scale(this.broccoli.getPos().getX() - 30),
                    Screen.scale(this.broccoli.getPos().getY() - 60),
                    Screen.scale(currentHealth),
                    Screen.scale(Texture.ENTITY_HEALTH_BAR.getTexture().getHeight()),
                    null
            );
        }
    }
}
