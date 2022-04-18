package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.SimpleProjectile;
import it.unibo.cicciopier.model.settings.DeveloperMode;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Abstract class that for a generic Projectile render
 */
abstract class SimpleProjectileView implements GameObjectView {
    private final SimpleProjectile projectile;
    private final Texture texture;

    /**
     * Constructor for a generic Projectile render
     *
     * @param projectile The projectile to be rendered
     * @param texture    The projectile's texture
     */
    protected SimpleProjectileView(final SimpleProjectile projectile, final Texture texture) {
        this.projectile = projectile;
        this.texture = texture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        int colLeft = 0;
        int colRight = this.projectile.getWidth();
        if (this.projectile.getVel().getX() < 0) {
            colLeft = this.projectile.getWidth();
            colRight = 0;
        }
        g.drawImage(this.texture.getTexture(),
                Screen.scale(this.projectile.getPos().getX()),
                Screen.scale(this.projectile.getPos().getY()),
                Screen.scale(this.projectile.getPos().getX() + this.projectile.getWidth()),
                Screen.scale(this.projectile.getPos().getY() + this.projectile.getHeight()),
                colLeft, 0, colRight, this.projectile.getHeight(), null);
        this.renderBounds(g);
    }

    /**
     * Render the bounds
     *
     * @param g graphic context
     */
    public void renderBounds(final Graphics g) {
        if (DeveloperMode.isActive()) {
            g.setColor(Color.BLACK);
            g.drawRect(Screen.scale(this.projectile.getPos().getX()),
                    Screen.scale(this.projectile.getPos().getY()),
                    Screen.scale(this.projectile.getWidth() - 1),
                    Screen.scale(this.projectile.getHeight() - 1)
            );
        }
    }
}
