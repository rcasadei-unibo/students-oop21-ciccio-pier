package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.MindPineappleView;

import java.util.Optional;

/**
 * Represents the enemy MindPineapple, a floating pineapple whom attack consists into manifesting
 * a short wall of spikes.
 */
public class MindPineapple extends SimplePathEnemy {
    private final int ATTACK_RANGE = this.getWorld().getPlayer().getAttackRange() + Block.SIZE;
    private static final double IDLE_DURATION = 2 * GameLoop.TPS;
    private static final int ATTACK_COOLDOWN = 2 * GameLoop.TPS;
    private static final double MOVEMENT_SPEED = 0.7;
    private static final int MAX_RIGHT_OFFSET = 3 * Block.SIZE;
    public static final double PROJECTILE_SPEED = 2d * Block.SIZE / GameLoop.TPS;
    public static final int ATTACK_DURATION_TICKS = 60;
    public static final int ANGERED_TICKS = 50;

    private final MindPineappleView view;
    private int localTicks;
    private boolean angered;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public MindPineapple(final World world) {
        super(EntityType.MIND_PINEAPPLE, world);
        this.localTicks = 0;
        this.angered = false;
        this.view = new MindPineappleView(this);
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
    public double getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getIdleDuration() {
        return IDLE_DURATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackRange() {
        return ATTACK_RANGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxRightOffset() {
        return MAX_RIGHT_OFFSET;
    }

    /**
     * Utility method called to set the MindPineapple angry
     */
    private void angered() {
        this.localTicks++;
        this.resetCurrentState(EnemyState.ANGERED);
        if (this.localTicks == ANGERED_TICKS) { 
            this.angered = true;
            this.localTicks = 0;
        }
    }

    /**
     * Utility method called when the MindPineapple manifests some spikes, attacking the Player.
     * The spikes are intended to slow down the pace of the player, therefore they do not behave
     * like usual projectiles, being slightly taller and significantly slower
     */
    private void manifestSpikes() {
        Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(EntityType.SPIKES);
        if (opt.isPresent()) {
            int dir = this.isFacingRight() ? 1 : -1;
            SimpleProjectile e = ((SimpleProjectile) opt.get());
            e.getVel().setX(dir * PROJECTILE_SPEED);
            e.setPos(this.getWorld().getPlayer().getPos().addVector(new Vector2d(-dir * this.getType().getWidth(), 0)));
            this.getWorld().addEntity(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void attacking() {
        this.getVel().setX(0);
        if (!angered) {
            this.angered();
        } else {
            this.resetCurrentState(EnemyState.ATTACKING);
            if (this.getShootingCooldownTicks() == 0) {
                AudioController.getInstance().playSound(Sound.SPIKES);
                this.manifestSpikes();
                this.setShootingCooldownTicks(ATTACK_COOLDOWN);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void notAttacking() {
        this.angered = false;
        this.localTicks = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
    }
}
