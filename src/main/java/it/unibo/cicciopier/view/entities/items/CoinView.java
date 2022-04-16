package it.unibo.cicciopier.view.entities.items;

import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.items.Coin;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleEntityView;

/**
 * Simple class to render a coin
 */
public class CoinView extends SimpleEntityView {
    public static final Animation ANIMATION = new Animation(Texture.COIN, 9, 5, new Pair<>(0, 0), 20, 20);
    private final Coin coin;

    /**
     * Constructor for this class, create a instance of a coin view
     * @param coin the coin to render
     */
    public CoinView(final Coin coin) {
        this.coin = coin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getObject() {
        return this.coin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATION;
    }
}
