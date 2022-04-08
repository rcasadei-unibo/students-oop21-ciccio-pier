package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.items.Coin;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.LoadAnimation;

import java.awt.*;

/**
 * Create a class for rendering a coin
 */
public class CoinView implements GameObjectView {
    private static final int ANIMATION_SPEED = 5;

    private final Coin coin;
    private int aniTik;
    private int currentIndex;

    /**
     * Constructor for this class, create an instance of a coin View
     *
     * @param coin what coin to render
     */
    public CoinView(final Coin coin) {
        this.coin = coin;
        this.aniTik = 0;
        this.currentIndex = 0;
    }

    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;
        if (aniTik >= CoinView.ANIMATION_SPEED) {
            aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= LoadAnimation.COIN_NUM_SPRITES) {
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
                LoadAnimation.getLoadAnimation().getCoinSprite(this.currentIndex),
                this.coin.getPos().getX(),
                this.coin.getPos().getY(),
                null
        );
    }
}
