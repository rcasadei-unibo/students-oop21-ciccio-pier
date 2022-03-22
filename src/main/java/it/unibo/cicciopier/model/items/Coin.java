package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.items.CoinView;

/**
 * Create a coin object
 */
public class Coin extends SimpleMovingEntity {
    private static final int POINT = 10;
    private final CoinView coinView;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Coin(final World world) {
        super(EntityType.COIN, world);
        this.coinView = new CoinView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        if (this.checkCollision(this.getWorld().getPlayer())) {
            //remove the coin
            this.remove();
            //add score
            this.getWorld().getPlayer().addCoin();
            this.getWorld().getPlayer().addScore(1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.coinView;
    }

}
