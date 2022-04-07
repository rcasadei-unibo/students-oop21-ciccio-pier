package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.SimpleProjectile;
import it.unibo.cicciopier.view.Texture;

/**
 * Simple implementation for the generic projectile view
 */
public class ProjectileView extends SimpleProjectileView {

    /**
     * Constructor for a generic Projectile render
     *
     * @param projectile The projectile to be rendered
     * @param texture    The projectile's texture
     */
    public ProjectileView(final SimpleProjectile projectile, final Texture texture) {
        super(projectile, texture);
    }
}
