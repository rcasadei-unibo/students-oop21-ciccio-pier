package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

/**
 * Represents the enemy RollingPeach, a walking peach whom attack consists into rolling
 * towards the player.
 */
public class RollingPeach extends SimpleEnemy {
    private final EnemyView view;
    private int ticks;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public RollingPeach(final World world) {
        super(EntityType.ROLLING_PEACH, world);
        this.setStatus(EnemyStatuses.ROLLING_PEACH_IDLE);
        this.setSpecular(true);
        this.view = new EnemyView(this, Texture.ROLLING_PEACH);
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
        super.tick();
        //this.ticks++;
        if (getWorld().getPlayer().checkCollision(this)) {
            this.die();
            this.setStatus(EnemyStatuses.ROLLING_PEACH_DYING);
        }

        if (this.getStatus() == EnemyStatuses.ROLLING_PEACH_DYING) {
            this.ticks++;
            if (this.ticks >= EnemyStatuses.ROLLING_PEACH_DYING.getDurationTicks()) {
                this.remove();
                this.ticks = 0;
            }
        }

    }
}
