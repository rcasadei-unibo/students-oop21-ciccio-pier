package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.SimpleProjectile;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Abstract class to represent the view of any generic simple projectile.
 * With simple, is meant a projectile with no special behaviour other than move in one horizontal
 * direction.
 */
abstract class SimpleProjectileView implements GameObjectView {
    private final SimpleProjectile entity;
    private final Texture texture;
    private boolean specular;
    private final int width;
    private final int height;

    /**
     * Constructor for a generic Projectile render
     *
     * @param projectile The projectile to be rendered
     * @param texture    The projectile's texture
     */
    protected SimpleProjectileView(final SimpleProjectile projectile, final Texture texture) {
        this.entity = projectile;
        this.texture = texture;
        this.width = this.entity.getWidth();
        this.height = this.entity.getHeight();
        this.specular = false;
    }

    /**
     * Method to set if this projectile is to be rendered specular or not
     *
     * @param bool True, if this projectile is to be rendered specular
     */
    public void setSpecularRender(final boolean bool) {
        this.specular = bool;
    }

    /**
     * Method for rendering a texture
     *
     * @param g The graphics to render
     */
    private void rend(final Graphics g) {
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                0, 0, this.width, this.height, null);
    }

    /**
     * Method for rendering a texture specular
     *
     * @param g The graphics to render
     */
    private void specularRend(final Graphics g) {
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                this.width, 0, 0, this.height, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        if (this.specular) {
            this.specularRend(g);
        } else {
            this.rend(g);
        }
    }
}
