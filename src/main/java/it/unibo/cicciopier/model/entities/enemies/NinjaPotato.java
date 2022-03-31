package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

public class NinjaPotato extends SimpleEnemy {
    private final EnemyView view;
    private int ticks;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public NinjaPotato(final World world) {
        super(EntityType.NINJA_POTATO, world);
        this.setStatus(EnemyStatuses.NINJA_POTATO_SWING_1);
        this.setSpecular(false);
        this.view = new EnemyView(this, Texture.NINJA_POTATO);
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
        this.ticks++;
        if (this.ticks == 100 && !this.isDead()) {
            this.setStatus(this.getStatus() == EnemyStatuses.NINJA_POTATO_SWING_1 ? EnemyStatuses.NINJA_POTATO_SWING_2 : EnemyStatuses.NINJA_POTATO_SWING_1);
            this.ticks = 0;
        }

        if (this.getWorld().getPlayer().checkCollision(this)) {
            this.die();
            this.ticks = 0;
            this.setStatus(EnemyStatuses.NINJA_POTATO_DYING);
        }
        if (this.getStatus() == EnemyStatuses.NINJA_POTATO_DYING) {
            if (this.ticks == EnemyStatuses.NINJA_POTATO_DYING.getDuration() * 100) {
                this.remove();
            }
        }
    }

    @Override
    public void jump() {

    }
}
