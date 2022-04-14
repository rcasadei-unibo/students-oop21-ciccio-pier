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
     * @param facingRight Flag regarding the direction of this projectile
     */
    public SpikesView(final Spikes spikes, final boolean facingRight) {
        super(spikes, Texture.SPIKES, facingRight);
    }
}
