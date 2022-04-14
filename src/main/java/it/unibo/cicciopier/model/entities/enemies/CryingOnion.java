package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.Collision;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.CryingOnionView;

/**
 * Represents the enemy CryingOnion, a walking onion whom attack consists into crying
 * whenever the player gets too near and then running into him.
 */
public class CryingOnion extends SimplePathEnemy {
    private static final int SCORE_VALUE = 50;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;
    private static final int ATTACK_RANGE = 5 * Block.SIZE;
    private static final double IDLE_DURATION = 2 * GameLoop.TPS;
    private static final double MOVEMENT_SPEED = (0.8d * Block.SIZE) / GameLoop.TPS;
    private static final double RUNNING_SPEED = (10d * Block.SIZE) / GameLoop.TPS;
    private static final int MAX_RIGHT_OFFSET = 3 * Block.SIZE;
    private static final int LOCAL_TICK_COUNT_DELIMITER = 3000;
    private static final int JUMP_FORCE = 9;

    private final CryingOnionView view;
    private boolean suicidal;
    private int jumped;
    private int localTicks;
    private boolean angered;
    private boolean attacking;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public CryingOnion(final World world) {
        super(EntityType.CRYING_ONION, world);
        this.angered = false;
        this.localTicks = 0;
        this.attacking = false;
        this.jumped = 0;
        this.suicidal = false;
        this.view = new CryingOnionView(this);
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
    public int getHealValue() {
        if (this.suicidal) {
            return 0;
        }
        return HEALTH_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStaminaValue() {
        if (this.suicidal) {
            return 0;
        }
        return STAMINA_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScoreValue() {
        if (this.suicidal) {
            return 0;
        }
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
     * Utility method used to update the local ticks when needed
     */
    private void updateLocalTicks() {
        if (this.localTicks < LOCAL_TICK_COUNT_DELIMITER) {
            this.localTicks++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getJumpForce() {
        return JUMP_FORCE;
    }

    /**
     * Method used to make the CryingOnion cry before attacking
     */
    private void startCrying() {
        this.resetCurrentState(EnemyState.ANGERED);
        this.updateLocalTicks();
        if (this.localTicks == 80) {
            this.localTicks = 0;
            this.angered = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean jump() {
        if (this.jumped == 2) {
            return true;
        }
        if (super.jump()) {
            this.jumped++;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void attacking() {
        if (!this.angered) {
            this.startCrying();
            return;
        }
        this.resetCurrentState(EnemyState.ANGERED_RUNNING);
        if (!this.jump()) {
            return;
        }
        this.getVel().setX(this.isFacingRight() ? RUNNING_SPEED : -RUNNING_SPEED);
        this.move();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCollision(Collision collision) {
        super.onCollision(collision);
        if ((this.isFacingRight() && collision == Collision.COLLIDING_RIGHT) ||
                (!this.isFacingRight() && collision == Collision.COLLIDING_LEFT)) {
            this.suicidal = true;
            this.die();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean attackBehaviour() {
        this.checkAttackConditions();
        if (this.isAttacking()) {
            this.attacking = true;
            this.getVel().setX(0);
        }
        if (attacking) {
            this.attacking();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
    }
}
