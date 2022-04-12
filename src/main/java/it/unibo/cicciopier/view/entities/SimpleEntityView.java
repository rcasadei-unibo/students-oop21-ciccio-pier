package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;

import java.awt.*;

public abstract class SimpleEntityView implements EntityView {
    private Pair<Integer> textureOffSet;
    protected int animationTicks;

    public SimpleEntityView() {
        this.textureOffSet = new Pair<>(0, 0);
        this.animationTicks = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer> getTextureOffSet() {
        return this.textureOffSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextureOffSet(final Pair<Integer> textureOffSet) {
        this.textureOffSet = textureOffSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        final Animation animation = getAnimation();
        g.drawImage(animation.getSprite(this.animationTicks / animation.getSpeed()),
                this.getObject().getPos().getX() + this.getTextureOffSet().getX(),
                this.getObject().getPos().getY() + this.getTextureOffSet().getY(),
                null);
        this.renderBounds(g);
        this.animationTicks++;
    }

    public void renderBounds(final Graphics g) {
        // TODO render the bounds if debug is active
        g.setColor(Color.BLACK);
        g.drawRect(this.getObject().getPos().getX(),
                this.getObject().getPos().getY(),
                this.getObject().getWidth() - 1,
                this.getObject().getHeight() - 1);
    }
}
