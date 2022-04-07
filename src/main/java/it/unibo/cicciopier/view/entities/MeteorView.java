package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Meteor;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Simple class to render a meteor
 */
public class MeteorView implements GameObjectView {
    private static final double ANGULAR_VELOCITY = (Math.PI * 0.8) / 180;
    private static final double MAX_ANGLE = Math.PI * 2;
    private static final int METEOR_SIZE = 64;
    private final Meteor meteor;
    private double currentAngle;

    /**
     * Create a instance of a meteor renderer
     *
     * @param meteor what meteor to render
     */
    public MeteorView(final Meteor meteor) {
        this.meteor = meteor;
        this.currentAngle = 0;
    }

    /**
     * Rotate the meteor by a certain velocity
     */
    private void rotateMeteor() {
        this.currentAngle += ANGULAR_VELOCITY;
        if (this.currentAngle >= MAX_ANGLE) {
            this.currentAngle = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        this.rotateMeteor();
        final Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldXForm = g2d.getTransform();
        g2d.rotate(
                this.currentAngle,
                this.meteor.getPos().getX() + this.meteor.getWidth() / 2d,
                this.meteor.getPos().getY() + this.meteor.getHeight() / 2d
        );
        g2d.drawImage(
                Texture.METEOR.getTexture().getSubimage(0, 0, METEOR_SIZE, METEOR_SIZE),
                this.meteor.getPos().getX(),
                this.meteor.getPos().getY(),
                null
        );
        g2d.setTransform(oldXForm);
    }
}
