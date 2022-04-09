package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

import java.util.Optional;

/**
 * Represents the enemy MindPineapple, a floating pineapple whom attack consists into manifesting
 * a short wall of spikes.
 */
public class MindPineapple extends SimplePathEnemy {
    private static final int SCORE_VALUE = 50;
    private static final double IDLE_DURATION = 4 * GameLoop.TPS;
    private static final double MOVEMENT_SPEED = (2d * Block.SIZE) / GameLoop.TPS;
    private static final int MAX_RIGHT_OFFSET = 3 * Block.SIZE;
    private static final int ATTACK_COOLDOWN = 2 * GameLoop.TPS;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;
    private final int ATTACK_RANGE = this.getWorld().getPlayer().getAttackRange() + Block.SIZE;


    private final EnemyView view;
    private int localTicks;
    private boolean angered;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public MindPineapple(final World world) {
        super(EntityType.MIND_PINEAPPLE, world);
        this.setStatus(EnemyStatuses.MIND_PINEAPPLE_IDLE);
        this.localTicks = 0;
        this.angered = false;
        this.view = new EnemyView(this, Texture.MIND_PINEAPPLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScoreValue() {
        return SCORE_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealValue() {
        return HEALTH_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStaminaValue() {
        return STAMINA_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTextureSpecular() {
        return false;
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
    public EnemyStatuses getIdleStatus() {
        return EnemyStatuses.MIND_PINEAPPLE_IDLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getWalkingStatus() {
        return EnemyStatuses.MIND_PINEAPPLE_IDLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxRightOffset() {
        return MAX_RIGHT_OFFSET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getDyingStatus() {
        return EnemyStatuses.MIND_PINEAPPLE_DYING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

    /**
     * Utility method called to set the MindPineapple angry
     */
    private void angered() {
        this.localTicks++;
        this.setStatus(EnemyStatuses.MIND_PINEAPPLE_ANGERED);
        if (this.localTicks >= EnemyStatuses.MIND_PINEAPPLE_ANGERED.getDurationTicks()) {
            this.angered = true;
            this.localTicks = 0;
        }
    }

    /**
     * Utility method called when the MindPineapple manifests some spikes, attacking the Player.
     * The spikes are intended to slow down the pace of the player, therefore they do not behave
     * like usual projectiles, being slightly taller and significantly slower
     *
     * @param dir The direction of the spikes
     */
    private void manifestSpikes(final int dir) {
        Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(EntityType.SPIKES);
        if (opt.isPresent()) {
            SimpleProjectile e = ((SimpleProjectile) opt.get());
            e.setDir(dir);
            e.setPos(this.getWorld().getPlayer().getPos().addVector(new Vector2d(
                    -dir * this.getType().getWidth(), 0)));
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
            this.setStatus(EnemyStatuses.MIND_PINEAPPLE_TELEKINESIS);
            if (this.getShootingCooldownTicks() == 0) {
                this.manifestSpikes(this.isFacingRight() ? 1 : -1);
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
        this.setStatus(EnemyStatuses.MIND_PINEAPPLE_IDLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
    }
}
