package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Stamina;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.items.SimpleItem;

import java.util.Random;

/**
 * Class to create a simple potato chips item for the player
 */
public class Potato extends SimpleItem {
    private static final int SCORE = 20;
    private final Random random;

    /**
     * Constructor for this class
     *
     * @param world the game's world
     */
    public Potato(World world) {
        super(EntityType.POTATOES, world, Texture.POTATOES);
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPickup(final long ticks) {
        this.remove();
        this.getWorld().getPlayer().addScore(SCORE);
        this.getWorld().getPlayer().addStamina(this.random.
                nextInt(Stamina.MAX_JUNK_FOOD - Stamina.MIN_JUNK_FOOD) + Stamina.MIN_JUNK_FOOD);
        this.getWorld().getPlayer().damage(EntityType.POTATOES.getAttackDamage());
    }
}
