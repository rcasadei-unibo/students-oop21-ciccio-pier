package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Explosion;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.LoadAnimation;

import java.awt.*;

/**
 * Class to render the explosion
 */
public class ExplosionView implements GameObjectView {

    private static final int ANIMATION_SPEED = 6;

    private final Explosion explosion;
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
    }


    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;
        if (aniTik >= ExplosionView.ANIMATION_SPEED) {
            aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= LoadAnimation.EXPLOSION_NUM_SPRITES) {
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
        if (this.currentIndex >= LoadAnimation.EXPLOSION_NUM_SPRITES) {
            return;
        }
        g.drawImage(
                LoadAnimation.getLoadAnimation().getExplosionSprite(this.currentIndex),
                this.explosion.getPos().getX(),
                this.explosion.getPos().getY(),
                null
        );
    }
}
