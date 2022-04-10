package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.items.JumpBoost;
import it.unibo.cicciopier.model.items.SpeedBoost;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Create a class for rendering a jump boost
 */
public class SpeedBoostView implements GameObjectView {

    private final SpeedBoost speedBoost;


    /**
     * Constructor for this class, create an instance of a jump boost View
     *
     * @param speedBoost what jumpBoost to render
     */
    public SpeedBoostView(final SpeedBoost speedBoost) {
        this.speedBoost = speedBoost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        g.drawImage(
                Texture.SPEED_BOOST.getTexture(),
                this.speedBoost.getPos().getX(),
                this.speedBoost.getPos().getY(),
                null
        );
    }
}
