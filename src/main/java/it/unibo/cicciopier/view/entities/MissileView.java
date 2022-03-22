package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Missile;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * class for creating a missile viewer
 */
public class MissileView implements GameObjectView {
    private final Missile missile;
    private final BufferedImage img;

    /**
     * Constructor for this class, create a instance of a missile View
     * @param missile what missile to render
     */
    public MissileView(final Missile missile)  {
        this.missile = missile;
        this.img = Texture.MISSILE.getTexture();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {

        final Graphics2D g2d = (Graphics2D) g;
        //
        AffineTransform oldXForm = g2d.getTransform();
        g2d.rotate(
                Math.PI/2 + this.missile.getVel().getAngle(),
                this.missile.getPos().getX(),
                this.missile.getPos().getY()
        );
        g2d.drawImage(
                img,
                this.missile.getPos().getX(),
                this.missile.getPos().getY(),
                null
        );
        g2d.setTransform(oldXForm);
    }
}
