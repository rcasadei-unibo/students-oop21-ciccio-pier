package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.boss.Broccoli;
import it.unibo.cicciopier.view.GameObjectView;

import java.awt.*;

public class BroccoliView implements GameObjectView {
    private final Broccoli broccoli;

    public BroccoliView(final Broccoli broccoli) {
        this.broccoli = broccoli;
    }

    @Override
    public void render(Graphics g) {
        g.drawRect(
                this.broccoli.getPos().getX(),
                this.broccoli.getPos().getY(),
                this.broccoli.getWidth(),
                this.broccoli.getHeight()
        );
    }
}
