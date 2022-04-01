package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.enemies.boss.Laser;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LaserView implements GameObjectView {
    private static final int FIRE_SIZE = 100;
    private static final int NUM_SPRITES = 65;
    private static final int ANIMATION_SPEED = 2;

    private final Laser laser;

    private BufferedImage[] fireAni;
    private int aniTik;
    private int currentIndex;

    public LaserView(final Laser laser) {
        this.laser = laser;
        this.loadAnimation();
    }

    /**
     * Load all the sprite in a array
     */
    private void loadAnimation() {
        this.fireAni = new BufferedImage[LaserView.NUM_SPRITES];
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 7 && j > 1) {
                    return;
                }
                this.fireAni[count++] = Texture.FIRE.getTexture().getSubimage(
                        LaserView.FIRE_SIZE * j,
                        LaserView.FIRE_SIZE * i,
                        LaserView.FIRE_SIZE,
                        LaserView.FIRE_SIZE
                );
            }

        }
    }

    /**
     * Update the current sprite to render
     */
    private void updateAnimation() {
        this.aniTik++;
        if (aniTik >= LaserView.ANIMATION_SPEED) {
            aniTik = 0;
            this.currentIndex++;
            if (currentIndex >= this.fireAni.length) {
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
        g.drawImage(
                this.fireAni[this.currentIndex],
                this.laser.getEndLine().getX() - 50,
                this.laser.getEndLine().getY() - 70,
                null
        );
    }
}
