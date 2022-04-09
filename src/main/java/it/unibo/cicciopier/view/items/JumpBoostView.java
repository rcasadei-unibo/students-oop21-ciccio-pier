package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.items.JumpBoost;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Create a class for rendering a jump boost
 */
public class JumpBoostView implements GameObjectView {

    private final JumpBoost jumpBoost;


    /**
     * Constructor for this class, create an instance of a jump boost View
     *
     * @param jumpBoost what jumpBoost to render
     */
    public JumpBoostView(final JumpBoost jumpBoost) {
        this.jumpBoost = jumpBoost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        //this.updateAnimation();
        g.drawImage(
                Texture.JUMP_BOOST.getTexture(),
                this.jumpBoost.getPos().getX(),
                this.jumpBoost.getPos().getY(),
                null
        );
    }
}
