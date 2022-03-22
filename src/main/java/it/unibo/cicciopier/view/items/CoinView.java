package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.items.Coin;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Create a class for rendering a coin
 */
public class CoinView implements GameObjectView {
    private static final int COIN_SIZE = 20;
    private static final int NUM_SPRITES = 9;
    private static final int ANIMATION_SPEED = 5;

    private final Coin coin;
    private BufferedImage[] coinAnimation;
    private int aniTik;
    private int currentIndex;


    /**
     * Constructor for this class, create a instance of a coin View
     *
     * @param coin what coin to render
     */
    public CoinView(final Coin coin) {
        this.coin = coin;
        this.aniTik = 0;
        this.currentIndex = 0;
        this.loadAnimation();
    }

    /**
     * Load all the sprite in a array
     */
    private void loadAnimation() {
        this.coinAnimation = new BufferedImage[CoinView.NUM_SPRITES];
        for (int i = 0; i < this.coinAnimation.length; i++) {
            this.coinAnimation[i] = Texture.COIN.getTexture().getSubimage(
                    CoinView.COIN_SIZE * i,
                    0,
                    CoinView.COIN_SIZE,
                    CoinView.COIN_SIZE
            );
        }
    }

    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;
        if (aniTik >= CoinView.ANIMATION_SPEED) {
            aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= this.coinAnimation.length) {
                this.currentIndex = 0;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        this.updateAnimation();
        g.drawImage(
                this.coinAnimation[this.currentIndex],
                this.coin.getPos().getX(),
                this.coin.getPos().getY(),
                null
        );
    }
}
