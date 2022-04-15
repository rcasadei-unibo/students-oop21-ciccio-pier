package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Missile;
import it.unibo.cicciopier.model.settings.Screen;
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
        g2d.rotate(Math.PI / 2 + this.missile.getVel().getAngle(),
                Screen.scale(this.missile.getPos().getX()),
                Screen.scale(this.missile.getPos().getY())
        );
        g2d.drawImage(Texture.MISSILE.getTexture(),
                Screen.scale(this.missile.getPos().getX() - 15),
                Screen.scale(this.missile.getPos().getY() - 5),
                Screen.scale(Texture.MISSILE.getTexture().getWidth()),
                Screen.scale(Texture.MISSILE.getTexture().getHeight()),
                null
        );
        g2d.setTransform(oldXForm);
    }
}
