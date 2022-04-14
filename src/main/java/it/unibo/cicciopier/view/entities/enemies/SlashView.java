package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.Slash;
import it.unibo.cicciopier.view.Texture;

/**
 * Class representing the view of a Slash projectile
 */
public class SlashView extends SimpleProjectileView {

    /**
     * Constructor for this class
     *
     * @param slash       The Slash of this view
     * @param facingRight Flag regarding the direction of this projectile
     */
    public SlashView(final Slash slash, final boolean facingRight) {
        super(slash, Texture.SLASH, facingRight);
    }
}
