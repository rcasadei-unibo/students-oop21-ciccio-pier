package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.view.GameObjectView;

public class Slash extends SimpleProjectile {

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Slash(final World world) {
        super(EntityType.SLASH,world,Projectiles.SLASH);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
