package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Laser;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.LoadAnimation;

import java.awt.*;

public class LaserView implements GameObjectView {
    private static final int ANIMATION_SPEED = 2;

    private final Laser laser;

    private int aniTik;
    private int currentIndex;

    public LaserView(final Laser laser) {
        this.laser = laser;
    }

    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;
        if (aniTik >= LaserView.ANIMATION_SPEED) {
            aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= LoadAnimation.FIRE_NUM_SPRITES) {
                this.currentIndex = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        final Stroke defaultStroke = g2d.getStroke();

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4F));
        g2d.drawLine(this.laser.getStartLine().getX(),
                this.laser.getStartLine().getY(),
                this.laser.getEndLine().getX(),
                this.laser.getEndLine().getY());
        g2d.setStroke(defaultStroke);

        this.updateAnimation();
        //check if we arrived at the end of the array
        g.drawImage(LoadAnimation.getLoadAnimation().getFireSprite(this.currentIndex),
                this.laser.getEndLine().getX() - 50,
                this.laser.getEndLine().getY() - 70,
                null
        );
    }
}
