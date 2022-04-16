package it.unibo.cicciopier.view.entities.enemies.boss;

import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.enemies.boss.Laser;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleEntityView;

import java.awt.*;

/**
 * Simple class to render a laser
 */
public class LaserView extends SimpleEntityView {
    public static final Animation ANIMATION = new Animation(Texture.FIRE, 65, 2, new Pair<>(0, 0),
            100, 100);
    private final Laser laser;

    /**
     * Constructor for this class, create an instance of laser view
     *
     * @param laser what laser to render
     */
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
        g2d.drawLine(Screen.scale(this.laser.getStartLine().getX()),
                Screen.scale(this.laser.getStartLine().getY()),
                Screen.scale(this.laser.getEndLine().getX()),
                Screen.scale(this.laser.getEndLine().getY())
        );
        g2d.setStroke(defaultStroke);
        super.render(g);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getObject() {
        return this.laser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animation getAnimation() {
        return ANIMATION;
    }
}
