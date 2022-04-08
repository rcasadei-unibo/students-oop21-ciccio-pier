package it.unibo.cicciopier.model.items;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.items.JumpBoostView;
import it.unibo.cicciopier.view.items.SpeedBoostView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create a coin object
 */
public class SpeedBoost extends SimpleMovingEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeedBoost.class);
    private final Item speedBoost;
    private SpeedBoostView speedBoostView;
    private boolean isActive;
    private int duration = 10 * GameLoop.TPS;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public SpeedBoost(final World world) {
        super(EntityType.SPEED_BOOST, world);
        this.speedBoost = Item.SPEED_BOOST;
        this.speedBoostView = new SpeedBoostView(this);
        this.isActive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {

        if (this.checkCollision(this.getWorld().getPlayer()) && !this.isActive) {
            AudioController.getAudioController().playSound(Sound.ITEM);
            //remove the boost view
            this.speedBoostView = null;
            //activate the boost
            this.isActive = true;
            this.getWorld().getPlayer().setSpeedModifier(speedBoost.getBoost());
            LOGGER.info("Speed Boost Activated");

        }
        if(this.isActive){
            this.duration--;
            LOGGER.info("duration remaining in tick:" + duration);
        }
        if (duration == 0){
            this.getWorld().getPlayer().setSpeedModifier(-speedBoost.getBoost());
            LOGGER.info("End of the speed boost");
            this.remove();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.speedBoostView;
    }

}
