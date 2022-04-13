package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.items.SimpleItem;

/**
 * Class to create a simple chicken item for the player
 */
public class Chicken extends SimpleItem {
    private static final int SCORE = 15;
    private static final int STAMINA = 10;
    private static final int DAMAGE = 4;

    /**
     * Constructor for this class
     *
     * @param world the game's world
     */
    public Chicken(final World world) {
        super(EntityType.CHICKEN, world, Texture.CHICKEN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPickup(final long ticks) {
        this.remove();
        this.getWorld().getPlayer().addScore(SCORE);
        this.getWorld().getPlayer().addStamina(STAMINA);
        this.getWorld().getPlayer().damage(DAMAGE);
    }
}
