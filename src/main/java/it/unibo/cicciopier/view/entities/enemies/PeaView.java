package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.Pea;
import it.unibo.cicciopier.view.Texture;

/**
 * Class representing the view of a Pea projectile
 */
public class PeaView extends SimpleProjectileView {

    /**
     * Constructor for this class
     *
     * @param pea The Pea of this view
     */
    public PeaView(final Pea pea) {
        super(pea, Texture.PEA);
    }
}
