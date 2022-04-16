package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Score;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.items.CoinView;

/**
 * Create a coin object
 */
public class Coin extends SimpleEntity {
    private final CoinView coinView;

    /**
     * Constructor for this class, create a coin instance
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
    public void tick(final long ticks) {
        if (this.checkCollision(this.getWorld().getPlayer())) {
            AudioController.getInstance().playSound(Sound.COIN);
            //remove the coin
            this.remove();
            this.getWorld().getPlayer().addCoin();
            this.getWorld().getPlayer().addScore(Score.COIN);
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
