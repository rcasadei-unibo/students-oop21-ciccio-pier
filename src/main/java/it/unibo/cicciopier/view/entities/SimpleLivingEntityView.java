package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.view.Animation;

import java.awt.*;

public abstract class SimpleLivingEntityView extends SimpleEntityView implements LivingEntityView {

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        //if the entity state changed, reset the counter
        if (this.getObject().getOldState() != this.getObject().getCurrentState()) {
            this.animationTicks = 0;
        }
        final Animation animation = getAnimation();
        if (this.getObject().isFacingRight()) {
            //looking right
            g.drawImage(animation.getSprite(this.animationTicks / animation.getSpeed()),
                    this.getObject().getPos().getX() + this.getTextureOffSet().getX(),
                    this.getObject().getPos().getY() + this.getTextureOffSet().getY(),
                    null);
        } else {
            //looking left
            g.drawImage(animation.getSprite(this.animationTicks / animation.getSpeed()),
                    this.getObject().getPos().getX() + this.getObject().getWidth() - this.getTextureOffSet().getX(),
                    this.getObject().getPos().getY() + this.getTextureOffSet().getY(),
                    -animation.getWidth(),
                    animation.getHeight(),
                    null);
        }
        this.renderBounds(g);
        this.animationTicks++;
    }
}
