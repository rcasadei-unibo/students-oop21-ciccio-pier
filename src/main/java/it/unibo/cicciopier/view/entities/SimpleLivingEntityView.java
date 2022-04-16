package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.Animation;

import java.awt.*;

/**
 * Simple class to render a living entity
 */
public abstract class SimpleLivingEntityView extends SimpleEntityView implements LivingEntityView {

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        if (this.getObject().isRemoved()) {
            return;
        }
        //if the entity state changed, reset the counter
        if (this.getObject().getOldState() != this.getObject().getCurrentState()) {
            this.resetAnimationTicks();
        }
        final Animation animation = getAnimation();
        if (this.getObject().isFacingRight()) {
            //looking right
            g.drawImage(animation.getSprite(this.getAnimationTicks() / animation.getSpeed()),
                    Screen.scale(this.getObject().getPos().getX() + this.getTextureOffSet().getX()),
                    Screen.scale(this.getObject().getPos().getY() + this.getTextureOffSet().getY()),
                    Screen.scale(animation.getWidth()),
                    Screen.scale(animation.getHeight()),
                    null
            );
        } else {
            //looking left
            g.drawImage(animation.getSprite(this.getAnimationTicks() / animation.getSpeed()),
                    Screen.scale(this.getObject().getPos().getX() + this.getObject().getWidth() - this.getTextureOffSet().getX()),
                    Screen.scale(this.getObject().getPos().getY() + this.getTextureOffSet().getY()),
                    Screen.scale(-animation.getWidth()),
                    Screen.scale(animation.getHeight()),
                    null
            );
        }
        this.renderBounds(g);
        this.increaseAnimationTicks();
    }
}
