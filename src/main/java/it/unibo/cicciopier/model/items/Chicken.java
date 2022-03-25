package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.items.ChickenView;

/**
 * Class to create a simple chicken item for the player
 */
public class Chicken extends SimpleMovingEntity {
    private final Item chickenItem;
    private final ChickenView chickenView;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Chicken(World world) {
        super(EntityType.CHICKEN, world);
        this.chickenItem = Item.CHICKEN;
        this.chickenView = new ChickenView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.chickenView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        //check if item collides with the player
        if (this.checkCollision(this.getWorld().getPlayer())) {
            //remove the coin
            this.remove();
            this.getWorld().getPlayer().addScore(this.chickenItem.getScore());
            this.getWorld().getPlayer().addStamina(this.chickenItem.getStamina());
            this.getWorld().getPlayer().damage(this.chickenItem.getHealth());
        }
    }
}
