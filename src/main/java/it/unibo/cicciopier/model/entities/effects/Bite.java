package it.unibo.cicciopier.model.entities.effects;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.effects.BiteView;

/**
 * Simple class for visualizing player attack
 */
public class Bite extends SimpleEntity {
    private static final int DURATION = 12;
    private BiteView biteView;
    private long start;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Bite(final World world) {
        super(EntityType.BITE, world);
        this.start = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        AudioController.getInstance().playSound(Sound.BITE);
        this.biteView = new BiteView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.biteView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(long ticks) {
        if (this.start == -1) {
            this.start = ticks;
            this.load();
        }
        if (ticks - this.start >= DURATION) {
            this.remove();
        }
    }
}
