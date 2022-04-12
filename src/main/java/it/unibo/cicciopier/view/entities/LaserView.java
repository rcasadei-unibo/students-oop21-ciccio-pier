package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.enemies.boss.Laser;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

public class LaserView extends SimpleEntityView {
    public static final Animation ANIMATION = new Animation(Texture.FIRE, 65, 2, new Pair<>(0, 0), 100, 100);
    private final Laser laser;

    public LaserView(final Laser laser) {
        this.laser = laser;
        this.setTextureOffSet(new Pair<>(-50, -70));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        final Stroke defaultStroke = g2d.getStroke();
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4F));
        g2d.drawLine(this.laser.getStartLine().getX(),
                this.laser.getStartLine().getY(),
                this.laser.getEndLine().getX(),
                this.laser.getEndLine().getY());
        g2d.setStroke(defaultStroke);
        super.render(g);
    }

    @Override
    public Entity getObject() {
        return this.laser;
    }

    @Override
    public Animation getAnimation() {
        return ANIMATION;
    }
}
