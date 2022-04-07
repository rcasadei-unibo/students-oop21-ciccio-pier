package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.SimpleProjectile;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

abstract class SimpleProjectileView implements GameObjectView {
    private final SimpleProjectile entity;
    private final Texture texture;
    private final int width;
    private final int height;

    protected SimpleProjectileView(final SimpleProjectile projectile, final Texture texture) {
        this.entity = projectile;
        this.texture = texture;
        this.width = this.entity.getWidth();
        this.height = this.entity.getHeight();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                0, 0, this.width, this.height, null);
    }
}
