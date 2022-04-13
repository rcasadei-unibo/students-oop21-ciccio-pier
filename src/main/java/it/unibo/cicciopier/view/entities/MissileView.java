package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Missile;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Simple class to create a missile view
 */
public class MissileView implements GameObjectView {
    private final Missile missile;

    /**
     * Constructor for this class, create an instance of a missile View
     *
     * @param missile what missile to render
     */
    public MissileView(final Missile missile) {
        this.missile = missile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldXForm = g2d.getTransform();
        g2d.rotate(
                Math.PI / 2 + this.missile.getVel().getAngle(),
                this.missile.getPos().getX(),
                this.missile.getPos().getY()
        );
        g2d.drawImage(
                Texture.MISSILE.getTexture(),
                this.missile.getPos().getX() - 15,
                this.missile.getPos().getY() - 5,
                null
        );
        g2d.setTransform(oldXForm);
    }
}
