package it.unibo.cicciopier.model.entities.items;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Score;
import it.unibo.cicciopier.model.entities.Stamina;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.items.SimpleItem;

import java.util.Random;

/**
 * Class to create a simple potato chips item for the player
 */
public class Potatoes extends SimpleItem {
    private final Random random;

    /**
     * Constructor for this class
     *
     * @param world the game's world
     */
    public Potatoes(final World world) {
        super(EntityType.POTATOES, world, Texture.POTATOES);
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPickup(final long ticks) {
        this.remove();
        AudioController.getInstance().playSound(Sound.FOOD_PICKUP);
        this.getWorld().getPlayer().addScore(Score.FOOD);
        this.getWorld().getPlayer().addStamina(this.random.
                nextInt(Stamina.MAX_JUNK_FOOD - Stamina.MIN_JUNK_FOOD) + Stamina.MIN_JUNK_FOOD);
        this.getWorld().getPlayer().damage(EntityType.POTATOES.getAttackDamage());
    }
}
