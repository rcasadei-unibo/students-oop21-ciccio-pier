package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.boss.Broccoli;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.LoadAnimation;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Simple class to render the boss Broccoli
 */
public class BroccoliView implements GameObjectView {
    private static final int HEALTH_BAR_WIDTH = 160;
    private static final int HEALTH_BAR_HEIGHT = 20;
    private static final int ANIMATION_SPEED = 6;

    private final Broccoli broccoli;
    private int aniTik;
    private int currentIndex;
    private int currentIndexState;

    /**
     * Constructor for this class, create a instance of BroccoliView
     *
     * @param broccoli what broccoli to render
     */
    public BroccoliView(final Broccoli broccoli) {
        this.broccoli = broccoli;
    }

    /**
     * Update the index of the boss state
     *
     * @param currentIndexState what state to update
     */
    private void updateStateIndex(final int currentIndexState) {
        if (aniTik >= BroccoliView.ANIMATION_SPEED) {
            this.aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= currentIndexState) {
                this.currentIndex = 0;
            }
        }
    }

    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;

        switch (this.broccoli.getCurrentState()) {
            case IDLE:
            case SEEK:
            case DEATH:
                this.currentIndexState = 0;
                this.updateStateIndex(LoadAnimation.BOSS_IDLE_AND_RUN_FRAME);
                break;
            case METEOR_SHOWER:
                this.currentIndexState = 1;
                this.updateStateIndex(LoadAnimation.METEOR_FRAME);
                break;
            case LASER:
                this.currentIndexState = 2;
                this.updateStateIndex(LoadAnimation.LASER_FRAME);
                break;
            case MISSILE_LAUNCHER:
                this.currentIndexState = 3;
                this.updateStateIndex(LoadAnimation.MISSILE_FRAME);
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        this.updateAnimation();
        g.drawImage(LoadAnimation.getLoadAnimation().getBossSprite(this.currentIndexState, this.currentIndex),
                this.broccoli.getPos().getX() - 75,
                this.broccoli.getPos().getY() - 40,
                null
        );
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
