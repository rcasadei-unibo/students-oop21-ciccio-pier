package it.unibo.cicciopier.model.entities.items;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Score;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.items.CoinView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create a coin object
 */
public class Coin extends SimpleEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(Coin.class);
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
            LOGGER.info("Coin picked up");
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
