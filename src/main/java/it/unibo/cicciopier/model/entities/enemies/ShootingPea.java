package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

/**
 * Represents the enemy ShootingPea, a walking pea whom attack consists into shooting
 * peas bursting from the pod's bottom.
 */
public class ShootingPea extends SimplePathEnemy {
    private static final int SCORE_VALUE = 50;
    private static final int MAX_RIGHT_OFFSET = 3 * Block.SIZE;
    private static final int ATTACK_RANGE = 7 * Block.SIZE;
    private static final int IDLE_DURATION = 2 * GameLoop.TPS;
    private static final int ATTACK_COOLDOWN = 2 * GameLoop.TPS;
    private static final double MOVEMENT_SPEED = (0.7 * Block.SIZE) / GameLoop.TPS;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;

    private final EnemyView view;
    private boolean shot;
    private int attackDurationTicks;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public ShootingPea(final World world) {
        super(EntityType.SHOOTING_PEA, world);
        this.setStatus(EnemyStatuses.SHOOTING_PEA_IDLE);
        this.attackDurationTicks = 0;
        this.shot = false;
        this.view = new EnemyView(this, Texture.SHOOTING_PEA);
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
    public boolean isTextureSpecular() {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getIdleStatus() {
        return EnemyStatuses.SHOOTING_PEA_IDLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getWalkingStatus() {
        return EnemyStatuses.SHOOTING_PEA_WALKING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getDyingStatus() {
        return EnemyStatuses.SHOOTING_PEA_DYING;
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
    public int getScoreValue() {
        return SCORE_VALUE;
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
     * {@inheritDoc}
     */
    @Override
    protected void attacking() {
        this.getVel().setX(0);
        if (this.getShootingCooldownTicks() == 0) {
            this.shot = false;
            this.setStatus(EnemyStatuses.SHOOTING_PEA_SHOOTING);
        }
        if (this.getStatus() == EnemyStatuses.SHOOTING_PEA_SHOOTING) {
            this.attackDurationTicks++;
        }
        if (this.attackDurationTicks >= EnemyStatuses.SHOOTING_PEA_SHOOTING.getDurationTicks()) {
            this.setShootingCooldownTicks(ATTACK_COOLDOWN);
            this.attackDurationTicks = 0;
            this.setStatus(EnemyStatuses.SHOOTING_PEA_IDLE);
        } else if (this.attackDurationTicks >= EnemyStatuses.SHOOTING_PEA_SHOOTING.getDurationTicks() / 2
                && !this.shot) {
            this.shoot(this.isFacingRight() ? 1 : -1, EntityType.PEA);
            this.shot = true;
            this.setShootingCooldownTicks((int) Math.ceil(EnemyStatuses.SHOOTING_PEA_SHOOTING.getDurationTicks()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void notAttacking() {
        this.attackDurationTicks = 0;
        this.setStatus(EnemyStatuses.SHOOTING_PEA_WALKING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
    }
}
