package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.boss.Broccoli;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Simple class to render the boss Broccoli
 */
public class BroccoliView implements GameObjectView {
    private static final int BOSS_WIDTH = 252;
    private static final int BOSS_HEIGHT = 384;
    private static final int HEALTH_BAR_WIDTH = 160;
    private static final int HEALTH_BAR_HEIGHT = 20;
    private static final int ANIMATION_SPEED = 6;
    private static final int IDLE_AND_RUN_FRAME = 8;
    private static final int METEOR_FRAME = 3;
    private static final int LASER_FRAME = 4;
    private static final int MISSILE_FRAME = 4;
    private static final int MAX_STATE_NUM = 4;
    private static final int MAX_FRAME = 8;
    private static final int NUM_OF_SPRITES = 19;

    private final Broccoli broccoli;
    private BufferedImage[][] ani;

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
        this.loadAni();
    }

    /**
     * Load the sprites for the boss animation
     */
    private void loadAni() {
        this.ani = new BufferedImage[MAX_STATE_NUM][MAX_FRAME];
        int counter = 0;
        int j = 0;

        for (int i = 0; i < NUM_OF_SPRITES; i++) {
            if (i == IDLE_AND_RUN_FRAME ||
                    i == IDLE_AND_RUN_FRAME + METEOR_FRAME ||
                    i == IDLE_AND_RUN_FRAME + METEOR_FRAME + LASER_FRAME) {
                counter++;
                j = 0;
            }
            this.ani[counter][j] = Texture.BROCCOLI.getTexture().getSubimage(
                    i * BOSS_WIDTH,
                    0,
                    BOSS_WIDTH,
                    BOSS_HEIGHT);
            j++;
        }
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
                this.updateStateIndex(IDLE_AND_RUN_FRAME);
                break;
            case METEOR_SHOWER:
                this.currentIndexState = 1;
                this.updateStateIndex(METEOR_FRAME);
                break;
            case LASER:
                this.currentIndexState = 2;
                this.updateStateIndex(LASER_FRAME);
                break;
            case MISSILE_LAUNCHER:
                this.currentIndexState = 3;
                this.updateStateIndex(MISSILE_FRAME);
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        this.updateAnimation();
        g.drawImage(this.ani[this.currentIndexState][this.currentIndex],
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
