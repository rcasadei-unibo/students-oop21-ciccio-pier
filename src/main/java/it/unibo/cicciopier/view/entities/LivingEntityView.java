package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

/**
 * Contains methods to animate an living entity
 */
public interface LivingEntityView extends EntityView {
    /**
     * Get the living entity
     *
     * @return an living entity
     */
    @Override
    LivingEntity getObject();
}
