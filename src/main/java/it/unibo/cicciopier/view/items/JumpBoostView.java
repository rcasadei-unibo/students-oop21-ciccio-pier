package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.items.JumpBoost;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Create a class for rendering a jump boost
 */
public class JumpBoostView implements GameObjectView {
    private static final int NUM_SPRITES = 9;
    private static final int ANIMATION_SPEED = 5;

    private final JumpBoost jumpBoost;
    private BufferedImage[] jumpBoostAnimation;
    private int aniTik;
    private int currentIndex;


    /**
     * Constructor for this class, create an instance of a jump boost View
     *
     * @param jumpBoost what jumpBoost to render
     */
    public JumpBoostView(final JumpBoost jumpBoost) {
        this.jumpBoost = jumpBoost;
        this.aniTik = 0;
        this.currentIndex = 0;
    }

    /**
     * Load all the sprite in an array
     */
    private void loadAnimation() {
        this.jumpBoostAnimation = new BufferedImage[JumpBoostView.NUM_SPRITES];
        for (int i = 0; i < this.jumpBoostAnimation.length; i++) {
            this.jumpBoostAnimation[i] = Texture.COIN.getTexture().getSubimage(
                    32 * i,
                    0,
                    32,
                    32
            );
        }
    }

    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;
        if (aniTik >= JumpBoostView.ANIMATION_SPEED) {
            aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= this.jumpBoostAnimation.length) {
                this.currentIndex = 0;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        //this.updateAnimation();
        g.drawImage(
                Texture.CHICKEN.getTexture(),
                this.jumpBoost.getPos().getX(),
                this.jumpBoost.getPos().getY(),
                null
        );
    }
}
