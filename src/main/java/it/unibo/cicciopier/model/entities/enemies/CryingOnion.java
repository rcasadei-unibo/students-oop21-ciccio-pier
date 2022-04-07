package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

/**
 * Represents the enemy CryingOnion, a walking onion whom attack consists into crying
 * whenever the player gets too near and then running into him.
 */
public class CryingOnion extends SimpleEnemy {
    private final EnemyView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public CryingOnion(final World world) {
        super(EntityType.CRYING_ONION, world);
        this.setStatus(EnemyStatuses.CRYING_ONION_WALKING_CRYING);
        this.setSpecular(false);
        this.view = new EnemyView(this, Texture.CRYING_ONION);
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
