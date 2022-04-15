package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.Spikes;
import it.unibo.cicciopier.view.Texture;

/**
 * Class representing the view of a Spikes projectile
 */
public class SpikesView extends SimpleProjectileView {

    /**
     * Constructor for this class
     *
     * @param spikes      The Spikes of this view
     */
    public SpikesView(final Spikes spikes) {
        super(spikes, Texture.SPIKES);
    }
}
