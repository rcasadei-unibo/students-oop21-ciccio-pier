package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

/**
 * Represents the enemy CryingOnion, a walking onion whom attack consists into crying
 * whenever the player gets too near and then running into him.
 */
public class CryingOnion extends SimplePathEnemy {
    private static final int SCORE_VALUE = 50;
    private static final double IDLE_DURATION = 2 * GameLoop.TPS;
    private static final double MOVEMENT_SPEED = (0.8d * Block.SIZE) / GameLoop.TPS;
    private static final int MAX_RIGHT_OFFSET = 3 * Block.SIZE;
    private static final int ATTACK_RANGE = 5 * Block.SIZE;
    private static final int LOCAL_TICK_COUNT_DELIMITER = 3000;
    private static final int JUMP_FORCE = 9;
    private static final double RUNNING_SPEED = (10d * Block.SIZE) / GameLoop.TPS;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;

    private final EnemyView view;
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
        this.setStatus(EnemyStatuses.CRYING_ONION_WALKING_CRYING);
        this.angered = false;
        this.localTicks = 0;
        this.attacking = false;
        this.jumped = 0;
        this.suicidal = false;
        this.view = new EnemyView(this, Texture.CRYING_ONION);
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
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getDyingStatus() {
        return EnemyStatuses.CRYING_ONION_DYING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getIdleStatus() {
        return EnemyStatuses.CRYING_ONION_IDLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getWalkingStatus() {
        return EnemyStatuses.CRYING_ONION_WALKING_NORMAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealValue() {
        if (this.suicidal){
            return 0;
        }
        return HEALTH_VALUE;
    }

    @Override
    public int getStaminaValue() {
        if (this.suicidal){
            return 0;
        }
        return STAMINA_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScoreValue() {
        if (this.suicidal){
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
        this.setStatus(EnemyStatuses.CRYING_ONION_CRYING);
        this.updateLocalTicks();
        if (this.localTicks >= EnemyStatuses.CRYING_ONION_CRYING.getDurationTicks()) {
            this.localTicks = 0;
            this.angered = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean jump() {
        this.jumped++;
        return super.jump();
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
        this.setStatus(EnemyStatuses.CRYING_ONION_WALKING_CRYING);
        if (this.jumped < 2){
            this.jump();
            return;
        }
        this.running();
    }

    /**
     * Method used to set the CryingOnion running, detecting if it hit a block.
     * In that case it dies
     */
    private void running(){
        this.getVel().setX(this.isFacingRight() ? RUNNING_SPEED : -RUNNING_SPEED);
        if( (this.isFacingRight() && this.rightCollision() >= 0) || (!this.isFacingRight() && this.leftCollision() <= 0) ){
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
        if (this.isAttacking()){
            this.attacking = true;
            this.getVel().setX(0);
        }
        if (attacking){
            this.attacking();
            this.move();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
    }
}
