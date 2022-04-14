package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.settings.DeveloperMode;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;

import java.awt.*;

/**
 * Simple class to render an entity
 */
public abstract class SimpleEntityView implements EntityView {
    private Pair<Integer> textureOffSet;
    private int animationTicks;

    /**
     * Constructor for this class
     */
    public SimpleEntityView() {
        this.textureOffSet = new Pair<>(0, 0);
        this.animationTicks = 0;
    }

    /**
     * Get the current animation tick
     *
     * @return tick value
     */
    public int getAnimationTicks() {
        return this.animationTicks;
    }

    /**
     * Reset the animation tick to 0
     */
    public void resetAnimationTicks() {
        this.animationTicks = 0;
    }

    /**
     * Update the animation tick by one
     */
    public void increaseAnimationTicks() {
        this.animationTicks++;
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
        g.drawImage(animation.getSprite(this.getAnimationTicks() / animation.getSpeed()),
                this.getObject().getPos().getX() + this.getTextureOffSet().getX(),
                this.getObject().getPos().getY() + this.getTextureOffSet().getY(),
                null);
        this.renderBounds(g);
        this.increaseAnimationTicks();
    }

    /**
     * Render the bounds
     *
     * @param g graphic context
     */
    public void renderBounds(final Graphics g) {
        if (DeveloperMode.isActive()) {
            g.setColor(Color.BLACK);
            g.drawRect(this.getObject().getPos().getX(),
                    this.getObject().getPos().getY(),
                    this.getObject().getWidth() - 1,
                    this.getObject().getHeight() - 1);
        }
    }
}
