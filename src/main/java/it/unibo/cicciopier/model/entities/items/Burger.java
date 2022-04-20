package it.unibo.cicciopier.model.entities.items;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Score;
import it.unibo.cicciopier.model.entities.Stamina;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.items.SimpleItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Class to create a simple burger item for the player
 */
public class Burger extends SimpleItem {
    private static final Logger LOGGER = LoggerFactory.getLogger(Burger.class);
    private final Random random;

    /**
     * Constructor for this class
     *
     * @param world the game's world
     */
    public Burger(final World world) {
        super(EntityType.BURGER, world, Texture.BURGER);
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
        this.getWorld().getPlayer().damage(EntityType.BURGER.getAttackDamage());
        LOGGER.info("burger picked up");
    }
}
