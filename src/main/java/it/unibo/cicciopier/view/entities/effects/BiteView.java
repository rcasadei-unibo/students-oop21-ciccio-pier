package it.unibo.cicciopier.view.entities.effects;

import it.unibo.cicciopier.model.entities.effects.Bite;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.SimpleEntityView;

public class BiteView extends SimpleEntityView {
    public static final Animation ANIMATION = new Animation(Texture.BITE, 3, 4,
            new Pair<>(0, 0), 128, 128);
    private final Bite bite;

    /**
     * Constructor for this class, create a instance of a bite view
     *
     * @param bite what bite to render
     */
    public BiteView(final Bite bite) {
        this.bite = bite;
    }

    @Override
    public Entity getObject() {
        return this.bite;
    }

    @Override
    public Animation getAnimation() {
        return BiteView.ANIMATION;
    }
}
