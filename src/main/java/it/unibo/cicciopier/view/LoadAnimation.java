package it.unibo.cicciopier.view;

import it.unibo.cicciopier.model.entities.base.EntityType;

import java.awt.image.BufferedImage;

/**
 * Singleton class to load all the animation for the game
 */
public class LoadAnimation {
    //boss
    public static final int BOSS_IDLE_AND_RUN_FRAME = 8;
    public static final int METEOR_FRAME = 3;
    public static final int LASER_FRAME = 4;
    public static final int MISSILE_FRAME = 4;
    //coin
    public static final int COIN_NUM_SPRITES = 9;
    //explosion
    public static final int EXPLOSION_NUM_SPRITES = 8;
    //fire
    public static final int FIRE_NUM_SPRITES = 65;

    private static LoadAnimation loadAnimation = null;
    private static final int FIRE_SIZE = 100;
    private static final int BOSS_WIDTH = 252;
    private static final int BOSS_HEIGHT = 384;
    private static final int BOSS_MAX_FRAME = 8;
    private static final int BOSS_NUM_OF_SPRITES = 19;
    private static final int BOSS_MAX_STATE_NUM = 4;
    private static final int EXPLOSION_SIZE = 64;
    private static final int FIRE_ROW = 8;
    private static final int FIRE_COLUMN = 9;

    private final BufferedImage[] coinAnim;
    private final BufferedImage[] explosionAnim;
    private final BufferedImage[][] bossAnim;
    private final BufferedImage[] fireAni;


    /**
     * Constructor for this class, create an instance of loadAnimation and load all the relative animations
     */
    private LoadAnimation() {
        coinAnim = new BufferedImage[LoadAnimation.COIN_NUM_SPRITES];
        bossAnim = new BufferedImage[BOSS_MAX_STATE_NUM][BOSS_MAX_FRAME];
        explosionAnim = new BufferedImage[LoadAnimation.EXPLOSION_NUM_SPRITES];
        fireAni = new BufferedImage[LoadAnimation.FIRE_NUM_SPRITES];
    }

    /**
     * Load all the animation needed for the game
     */
    public void loadAllAnimation() {
        this.loadCoinAnimation();
        this.loadBossAnimation();
        this.loadExplosionAnimation();
        this.loadFireAnimation();
    }

    /**
     * Get the one and only instance of LoadAnimation
     *
     * @return loadAnimation
     */
    public static LoadAnimation getLoadAnimation() {
        if (loadAnimation == null) {
            loadAnimation = new LoadAnimation();
        }
        return loadAnimation;
    }

    /**
     * Load all the coin sprites
     */
    private void loadCoinAnimation() {
        for (int i = 0; i < coinAnim.length; i++) {
            this.coinAnim[i] = Texture.COIN.getTexture().getSubimage(
                    EntityType.COIN.getWidth() * i,
                    0,
                    EntityType.COIN.getWidth(),
                    EntityType.COIN.getHeight()
            );
        }
    }

    /**
     * Load the sprites for the boss animation
     */
    private void loadBossAnimation() {
        int counter = 0;
        int j = 0;

        for (int i = 0; i < BOSS_NUM_OF_SPRITES; i++) {
            if (i == LoadAnimation.BOSS_IDLE_AND_RUN_FRAME ||
                    i == LoadAnimation.BOSS_IDLE_AND_RUN_FRAME + LoadAnimation.METEOR_FRAME ||
                    i == LoadAnimation.BOSS_IDLE_AND_RUN_FRAME + LoadAnimation.METEOR_FRAME + LoadAnimation.LASER_FRAME) {
                counter++;
                j = 0;
            }
            this.bossAnim[counter][j] = Texture.BROCCOLI.getTexture().getSubimage(
                    i * BOSS_WIDTH,
                    0,
                    BOSS_WIDTH,
                    BOSS_HEIGHT);
            j++;
        }
    }

    /**
     * Load all the sprite of the explosion
     */
    private void loadExplosionAnimation() {
        for (int i = 0; i < explosionAnim.length; i++) {
            this.explosionAnim[i] = Texture.EXPLOSION.getTexture().getSubimage(
                    EXPLOSION_SIZE * i,
                    0,
                    EXPLOSION_SIZE,
                    EXPLOSION_SIZE
            );
        }
    }

    /**
     * Load all the sprite of the fire
     */
    private void loadFireAnimation() {
        int count = 0;
        for (int i = 0; i < FIRE_ROW; i++) {
            for (int j = 0; j < FIRE_COLUMN; j++) {
                if (i == 7 && j > 1) {
                    return;
                }
                this.fireAni[count++] = Texture.FIRE.getTexture().getSubimage(
                        LoadAnimation.FIRE_SIZE * j,
                        LoadAnimation.FIRE_SIZE * i,
                        LoadAnimation.FIRE_SIZE,
                        LoadAnimation.FIRE_SIZE
                );
            }
        }
    }

    /**
     * Get the coin sprite at specific position
     *
     * @param i from what position to get the coin sprite
     * @return a coin sprite
     */
    public BufferedImage getCoinSprite(final int i) {
        return this.coinAnim[i];
    }

    /**
     * Get the Boss sprite at specific position
     *
     * @param i row
     * @param j column
     * @return a boss sprite
     */
    public BufferedImage getBossSprite(final int i, final int j) {
        return this.bossAnim[i][j];
    }

    /**
     * Get the explosion sprite at specific position
     *
     * @param i from what position
     * @return an explosion sprite
     */
    public BufferedImage getExplosionSprite(final int i) {
        return this.explosionAnim[i];
    }

    /**
     * Get the fire sprite at specific position
     *
     * @param i from what position
     * @return an fire sprite
     */
    public BufferedImage getFireSprite(final int i) {
        return this.fireAni[i];
    }

}
