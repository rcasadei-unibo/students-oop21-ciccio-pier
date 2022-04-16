package it.unibo.cicciopier.model.entities.items;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.Score;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.items.SimpleItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create a speed boost
 */
public final class SpeedBoost extends SimpleItem implements Boost {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeedBoost.class);
    private static final int DURATION = 10 * GameLoop.TPS;
    private static final int BOOST_STRENGTH = 2;
    private boolean active;
    private long startOfBoost;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public SpeedBoost(final World world) {
        super(EntityType.SPEED_BOOST, world, Texture.SPEED_BOOST);
        this.active = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
        if (ticks - this.startOfBoost >= SpeedBoost.DURATION && isActive()) {
            this.getWorld().getPlayer().setSpeedModifier(-SpeedBoost.BOOST_STRENGTH);
            LOGGER.debug("End of the speed boost");
            this.remove();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPickup(final long ticks) {
        if (!this.active) {
            AudioController.getInstance().playSound(Sound.BOOST_PICKUP);
            //activate the boost
            this.active = true;
            this.startOfBoost = ticks;
            this.getWorld().getPlayer().addScore(Score.BOOST);
            this.getWorld().getPlayer().setSpeedModifier(SpeedBoost.BOOST_STRENGTH);
            LOGGER.debug("Speed boost Activated");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        if (this.isActive()) {
            return null;
        }
        return super.getView();
    }
}
