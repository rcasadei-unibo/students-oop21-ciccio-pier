package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.GameObjectView;

/**
 * Contains methods to animate an entity
 */
public interface EntityView extends GameObjectView {
    /**
     * Get the entity
     *
     * @return an entity
     */
    Entity getObject();

    /**
     * Get the current animation
     *
     * @return an animation
     */
    Animation getAnimation();

    /**
     * Get the offset to render the texture
     *
     * @return offset values
     */
    Pair<Integer> getTextureOffSet();

    /**
     * Set the offset to render the texture
     *
     * @param textureOffSet new values
     */
    void setTextureOffSet(final Pair<Integer> textureOffSet);
}
