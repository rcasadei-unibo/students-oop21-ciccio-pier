package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.entities.Coin;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Create a class for rendering a coin
 */
public class CoinView implements GameObjectView {
    private final Coin coin;
    private final BufferedImage img;

    /**
     * Constructor for this class, create a instance of a coin View
     *
     * @param coin what coin to render
     */
    public CoinView(final Coin coin) {
        this.coin = coin;
        this.img = Texture.COIN.getTexture();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        g.drawImage(this.img, this.coin.getPos().getX(), this.coin.getPos().getY(), null);
    }
}
