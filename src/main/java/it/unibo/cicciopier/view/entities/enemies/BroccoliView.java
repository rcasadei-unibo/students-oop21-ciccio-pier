package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.boss.Broccoli;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

public class BroccoliView implements GameObjectView {
    private static final int HEALTH_BAR_WIDTH = 160;
    private static final int HEALTH_BAR_HEIGHT = 20;
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
        //draw boss healthBar
        g.drawImage(
                Texture.ENTITY_HEALTH_BAR_DECORATION.getTexture(),
                this.broccoli.getPos().getX(),
                this.broccoli.getPos().getY() - 30,
                null
        );
        final int currentHealth = (this.broccoli.getHp() * HEALTH_BAR_WIDTH) / this.broccoli.getMaxHp();
        if (currentHealth > 0) {
            g.drawImage(
                    Texture.ENTITY_HEALTH_BAR.getTexture().getSubimage(0, 0, currentHealth, HEALTH_BAR_HEIGHT),
                    this.broccoli.getPos().getX(),
                    this.broccoli.getPos().getY() - 30,
                    null
            );
        }
    }
}
