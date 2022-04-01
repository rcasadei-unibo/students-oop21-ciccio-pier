package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

public class NinjaPotato extends SimpleEnemy {
    private final EnemyView view;
    private static final int ATTACK_RANGE = 32 * 6;
    private int ticks;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public NinjaPotato(final World world) {
        super(EntityType.NINJA_POTATO, world);
        this.setStatus(EnemyStatuses.NINJA_POTATO_HIDDEN);
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

        if (this.getWorld().getPlayer().checkCollision(this)) {
            this.die();
            this.ticks = 0;
            this.setStatus(EnemyStatuses.NINJA_POTATO_DYING);
            return;
        }

        if (this.getStatus() == EnemyStatuses.NINJA_POTATO_DYING) {
            if (this.ticks >= EnemyStatuses.NINJA_POTATO_DYING.getDuration() * 100) {
                this.remove();
            }
            return;
        }

        if (this.getStatus() == EnemyStatuses.NINJA_POTATO_SWING_1 || this.getStatus() == EnemyStatuses.NINJA_POTATO_SWING_2) {
            if (!this.checkPlayerInRange(ATTACK_RANGE)) {
                this.ticks = 0;
                this.setStatus(EnemyStatuses.NINJA_POTATO_IDLE);
            } else if (this.getStatus() == EnemyStatuses.NINJA_POTATO_SWING_1 && this.ticks >= EnemyStatuses.NINJA_POTATO_SWING_1.getDuration() * 100) {
                this.setStatus(EnemyStatuses.NINJA_POTATO_SWING_2);
                this.ticks = 0;
            } else if (this.getStatus() == EnemyStatuses.NINJA_POTATO_SWING_2 && this.ticks >= EnemyStatuses.NINJA_POTATO_SWING_2.getDuration() * 100) {
                this.setStatus(EnemyStatuses.NINJA_POTATO_SWING_1);
                this.ticks = 0;
            }
            return;
        }

        if (this.getStatus() == EnemyStatuses.NINJA_POTATO_HIDDEN && this.checkPlayerInRange(ATTACK_RANGE)) {
            this.setStatus(EnemyStatuses.NINJA_POTATO_JUMPING_OUT);
            this.ticks = 0;
            return;
        }

        if (this.getStatus() == EnemyStatuses.NINJA_POTATO_JUMPING_OUT) {
            if (this.ticks >= EnemyStatuses.NINJA_POTATO_JUMPING_OUT.getDuration() * 100) {
                this.ticks = 0;
                if (this.checkPlayerInRange(ATTACK_RANGE)) {
                    this.setStatus(EnemyStatuses.NINJA_POTATO_SWING_1);
                } else {
                    this.setStatus(EnemyStatuses.NINJA_POTATO_IDLE);
                }
            }
            return;
        }

        if (this.getStatus() == EnemyStatuses.NINJA_POTATO_IDLE) {
            if (this.ticks >= EnemyStatuses.NINJA_POTATO_IDLE.getDuration() * 100) {
                this.setStatus(EnemyStatuses.NINJA_POTATO_JUMPING_IN);
                this.ticks = 0;
            }
            return;
        }

        if (this.getStatus() == EnemyStatuses.NINJA_POTATO_JUMPING_IN) {
            if (this.ticks >= EnemyStatuses.NINJA_POTATO_JUMPING_IN.getDuration() * 100) {
                this.setStatus(EnemyStatuses.NINJA_POTATO_HIDDEN);
                this.ticks = 0;
            }
        }
    }

    /**
     * Does nothing, this entity does not jump
     */
    @Override
    public void jump() {

    }
}
