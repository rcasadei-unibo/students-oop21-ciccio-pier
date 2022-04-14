package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.SlashView;

/**
 * Class representing the Slash projectile
 */
public class Slash extends SimpleProjectile {

    private SlashView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Slash(final World world) {
        super(EntityType.SLASH, world, NinjaPotato.PROJECTILE_DURATION_TICKS);
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
    protected void createView() {
        this.view = new SlashView(this, this.getDir() == 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
    }
}
