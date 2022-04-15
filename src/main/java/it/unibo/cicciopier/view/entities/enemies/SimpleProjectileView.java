package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.SimpleProjectile;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Abstract class that for a generic Projectile render
 */
abstract class SimpleProjectileView implements GameObjectView {
    private final SimpleProjectile projectile;
    private final Texture texture;
    private final int width;
    private final int height;

    /**
     * Constructor for a generic Projectile render
     *
     * @param projectile The projectile to be rendered
     * @param texture    The projectile's texture
     */
    protected SimpleProjectileView(final SimpleProjectile projectile, final Texture texture) {
        this.projectile = projectile;
        this.texture = texture;
        this.width = this.projectile.getWidth();
        this.height = this.projectile.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        int colLeft = 0;
        int colRight = this.width;
        if (this.projectile.getVel().getX() < 0) {
            colLeft = this.width;
            colRight = 0;
        }
        g.drawImage(this.texture.getTexture(),
                this.projectile.getPos().getX(),
                this.projectile.getPos().getY(),
                this.projectile.getPos().getX() + this.width,
                this.projectile.getPos().getY() + this.height,
                colLeft, 0, colRight, this.height, null);
    }
}
