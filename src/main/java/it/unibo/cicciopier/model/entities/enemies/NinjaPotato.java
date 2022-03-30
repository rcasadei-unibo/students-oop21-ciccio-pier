package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

public class NinjaPotato extends SimpleEnemy{

    private final EnemyView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public NinjaPotato(final World world) {
        super(EntityType.NINJA_POTATO, world);
        this.setStatus(EnemyStatuses.NINJA_POTATO_IDLE);
        this.setSpecular(false);
        this.view = new EnemyView(this);
    }

    @Override
    public GameObjectView getView() {
        return this.view;
    }

    @Override
    public void tick() {

    }

    @Override
    public void jump() {

    }
}
