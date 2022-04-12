package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.base.LivingEntity;

public interface LivingEntityView extends EntityView {
    @Override
    LivingEntity getObject();
}
