package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.items.CoinView;

/**
 * Create a coin object
 */
public class Coin extends SimpleEntity {
    private final ItemEnum coinItem;
    private CoinView coinView;
    private boolean collides;
    private int counter;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Coin(final World world) {
        super(EntityType.COIN, world);
        this.coinItem = ItemEnum.COIN;
        this.coinView = new CoinView(this);
        collides = false;
        this.counter = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        if (!this.collides && this.checkCollision(this.getWorld().getPlayer())) {
            Sound.COIN.playSound(0);
            this.collides = true;
            //remove the coin
            //add score
            this.coinView = null;
            this.getWorld().getPlayer().addCoin();
            this.getWorld().getPlayer().addScore(this.coinItem.getScore());
        }

        if (collides) {
            this.counter++;
            if(counter >= 2){
                if (!Sound.COIN.isPlaying()) {
                    Sound.COIN.close();
                    this.remove();
                }
            }
        }

        /*if(this.tick >= 60){
            Sound.COIN.close();
            System.out.println("ho rimosso l audio");
            this.remove();
        }*/

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.coinView;
    }

}
