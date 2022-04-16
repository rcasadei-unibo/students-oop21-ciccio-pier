package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.Collision;
import it.unibo.cicciopier.model.entities.base.EntityType;

/**
 * Class that abstracts an Enemy whom movement behaviour consists of patrolling a path
 */
public abstract class SimplePathEnemy extends SimpleEnemy implements PathEnemy {
    private int leftPathfurthest;
    private int rightPathfurthest;
    private int currentDest;
    private int idleTicks;

    /**
     * Constructor for this class.
     * Initializes all the common fields for enemies with a movement path behaviour
     *
     * @param type  The Entity's type
     * @param world The game's world
     */
    protected SimplePathEnemy(final EntityType type, final World world) {
        super(type, world);
    }

    /**
     * Method used to set up the path and assign the X-axis extremes of the path
     * This can't be done in the constructor due to having the initial position assigned
     * after being created, therefore this method gets called once the first tick
     * and takes the initial position as the left extreme of the path
     *
     * @param maxRightOffset The offset for the right extreme of this path
     */
    protected void initializePath(final int maxRightOffset) {
        this.leftPathfurthest = this.getPos().getX();
        this.rightPathfurthest = this.leftPathfurthest + maxRightOffset;
        this.currentDest = this.leftPathfurthest;
    }

    /**
     * Utility method used to check if the Enemy is in the conditions to attack the player.
     * The conditions to begin attacking are for the player to be within range and for the
     * Enemy to be facing the player.
     * The conditions to stop attacking are either the player moved out of range or the Enemy is not
     * facing him anymore
     */
    protected void checkAttackConditions() {
        if (this.startAggro(this.getAttackRange()) && this.facingPlayer()) {
            this.setAttacking(true);
        }
        if (!this.playerInAggroRange(this.getAttackRange()) || !this.facingPlayer()) {
            this.setAttacking(false);
        }
    }

    /**
     * Method called every tick if the Enemy is not dead, before moving.
     * Based on the Enemy attacking or not, two different methods get called.
     * These two methods are left empty to be implemented in each individual Enemy.
     * If the Enemy is attacking, this method returns true so that the tick does not continue
     * and the Enemy does not follow its default moving behaviour
     *
     * @return True, if the Enemy is currently attacking
     */
    protected boolean attackBehaviour() {
        this.checkAttackConditions();
        if (this.isAttacking()) {
            this.attacking();
            return true;
        } else {
            this.notAttacking();
            return false;
        }
    }

    /**
     * Method called when then Enemy is attacking.
     * It is left empty to be overridden
     */
    protected void attacking() {
    }

    /**
     * Method called when then Enemy is not attacking.
     * It is left empty to be overridden
     */
    protected void notAttacking() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCollision(Collision collision) {
        super.onCollision(collision);
        if (collision == Collision.COLLIDING_RIGHT) {
            this.rightPathfurthest = this.getPos().getX();
            this.currentDest = rightPathfurthest;
        }
        if (collision == Collision.COLLIDING_LEFT) {
            this.leftPathfurthest = this.getPos().getX();
            this.currentDest = leftPathfurthest;
        }
    }

    /**
     * Method that defines the common movement behaviour for all path enemies.
     *
     * @param movementSpeed The speed of the movement
     * @param idleDuration  THe duration of the idle at each extreme
     */
    private void pathMovementBehaviour(final double movementSpeed, final double idleDuration) {
        if (this.getPos().getX() == this.currentDest
                && this.idleTicks < idleDuration) {
            this.resetCurrentState(EntityState.IDLE);
            this.getVel().setX(0);
            this.idleTicks++;
        } else if (this.getPos().getX() == this.currentDest) {
            this.currentDest = this.currentDest == this.leftPathfurthest ? this.rightPathfurthest : this.leftPathfurthest;
            this.idleTicks = 0;
            this.resetCurrentState(EnemyState.RUNNING);
        } else {
            this.getVel().setX(this.currentDest == this.leftPathfurthest ? -movementSpeed : movementSpeed);
            this.resetCurrentState(EntityState.RUNNING);
        }
        this.move();
    }

    @Override
    public void load() {
        this.initializePath(this.getMaxRightOffset());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
        if (this.isDead()) {
            return;
        }
        if (this.attackBehaviour()) {
            return;
        }
        this.pathMovementBehaviour(this.getMovementSpeed(), this.getIdleDuration());
    }
}
