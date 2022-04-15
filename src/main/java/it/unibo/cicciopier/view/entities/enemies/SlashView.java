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
     */
    public SlashView(final Slash slash) {
        super(slash, Texture.SLASH);
    }
}
