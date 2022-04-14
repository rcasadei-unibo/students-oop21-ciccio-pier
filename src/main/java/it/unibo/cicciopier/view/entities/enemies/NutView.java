package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.Nut;
import it.unibo.cicciopier.view.Texture;

/**
 * Class representing the view of a Nut projectile
 */
public class NutView extends SimpleProjectileView {

    /**
     * Constructor for this class
     *
     * @param nut         The Nut of this view
     * @param facingRight Flag regarding the direction of this projectile
     */
    public NutView(final Nut nut, final boolean facingRight) {
        super(nut, Texture.NUT, facingRight);
    }
}
