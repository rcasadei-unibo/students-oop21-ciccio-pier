package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

/**
 * Represents the enemy MindPineapple, a floating pineapple whom attack consists into manifesting
 * a short wall of spikes.
 */
public class MindPineapple extends SimpleEnemy {
    private final EnemyView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public MindPineapple(final World world) {
        super(EntityType.MIND_PINEAPPLE, world);
        this.setStatus(EnemyStatuses.MIND_PINEAPPLE_TELEKINESIS);
        this.setSpecular(false);
        this.view = new EnemyView(this, Texture.MIND_PINEAPPLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {

    }

}
