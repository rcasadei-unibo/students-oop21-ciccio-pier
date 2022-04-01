package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Explosion;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class to render the explosion
 */
public class ExplosionView implements GameObjectView {

    private static final int EXPLOSION_SIZE = 64;
    private static final int NUM_SPRITES = 8;
    private static final int ANIMATION_SPEED = 6;

    private final Explosion explosion;
    private BufferedImage[] explosionAni;
    private int aniTik;
    private int currentIndex;

    /**
     * Constructor for this class, create a instance of a explosion view
     *
     * @param explosion what explosion to render
     */
    public ExplosionView(final Explosion explosion) {
        this.explosion = explosion;
        this.aniTik = 0;
        this.currentIndex = 0;
        this.loadAnimation();
    }

    /**
     * Load all the sprite in a array
     */
    private void loadAnimation() {
        this.explosionAni = new BufferedImage[ExplosionView.NUM_SPRITES];
        for (int i = 0; i < this.explosionAni.length; i++) {
            this.explosionAni[i] = Texture.EXPLOSION.getTexture().getSubimage(
                    ExplosionView.EXPLOSION_SIZE * i,
                    0,
                    ExplosionView.EXPLOSION_SIZE,
                    ExplosionView.EXPLOSION_SIZE
            );
        }
    }

    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;
        if (aniTik >= ExplosionView.ANIMATION_SPEED) {
            aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= this.explosionAni.length) {
                this.explosion.finished();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        this.updateAnimation();
        //check if we arrived at the end of the array
        if(this.currentIndex >= this.explosionAni.length){
            return;
        }
        g.drawImage(
                this.explosionAni[this.currentIndex],
                this.explosion.getPos().getX(),
                this.explosion.getPos().getY(),
                null
        );
    }
}
