package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.items.Chicken;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class to render a specific chicken
 */
public class ChickenView implements GameObjectView {
    private final Chicken chicken;
    private final BufferedImage img;

    /**
     * Constructor for this class, crate a instance of a chicken view
     *
     * @param chicken what chicken to render
     */
    public ChickenView(final Chicken chicken) {
        this.chicken = chicken;
        this.img = Texture.CHICKEN.getTexture();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(this.img, this.chicken.getPos().getX(), this.chicken.getPos().getY(), null);
    }
}
