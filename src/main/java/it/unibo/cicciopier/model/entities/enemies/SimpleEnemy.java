package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.utility.Vector2d;

import java.util.Optional;

/**
 * Abstract class for a generic basic Enemy
 */
public abstract class SimpleEnemy extends SimpleLivingEntity implements Enemy {
    private static final int HIT_COOLDOWN = 2 * GameLoop.TPS;
    public static final int DEATH_DURATION = 2 * GameLoop.TPS;
    private final int attackDamage;
    private int shootingCooldownTicks;
    private int hitTicks;
    private int deathTicks;
    private boolean attacking;

    /**
     * Constructor for this class.
     * Initializes all the common fields for enemies
     *
     * @param type  The Entity's type
     * @param world The game's world
     */
    protected SimpleEnemy(final EntityType type, final World world) {
        super(type, world);
        this.attackDamage = this.getType().getAttackDamage();
        this.deathTicks = 0;
        this.shootingCooldownTicks = 0;
        this.hitTicks = 0;
        this.attacking = false;
        this.setFacingRight(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attackPlayer() {
        this.getWorld().getPlayer().damage(this.attackDamage);
    }

    /**
     * Method used to set if the enemy is attacking
     *
     * @param bool True, if the enemy is currently attacking
     */
    public void setAttacking(final boolean bool) {
        this.attacking = bool;
    }

    /**
     * Checks if the enemy is currently attacking or not
     *
     * @return True, if the enemy is attacking
     */
    public boolean isAttacking() {
        return this.attacking;
    }

    /**
     * Method used to start the cooldown after the Enemy has shot
     *
     * @param ticks
     */
    public void setShootingCooldownTicks(final int ticks) {
        this.shootingCooldownTicks = ticks;
    }

    /**
     * Retrieve the shooting cooldown
     *
     * @return The cooldown ticks
     */
    public int getShootingCooldownTicks() {
        return this.shootingCooldownTicks;
    }

    /**
     * Utility method to check if the player is inside the enemy range
     *
     * @param range The enemy range to detect the player
     * @return True if conditions are met
     */
    protected boolean playerInAggroRange(final int range) {
        int pivot = this.getPos().getX();
        int playerPos = this.getWorld().getPlayer().getPos().getX();
        return (playerPos >= pivot - range) && (playerPos <= pivot + range);
    }

    /**
     * Utility method to check if the player is inside the enemy range and on his same height
     *
     * @param range The enemy range to detect the player
     * @return True, if conditions are met
     */
    protected boolean startAggro(final int range) {
        return this.playerInAggroRange(range) &&
                this.getPos().getY() + this.getType().getHeight()
                        == getWorld().getPlayer().getPos().getY() + getWorld().getPlayer().getHeight();
    }

    /**
     * Utility method to check if the enemy is currently facing the player
     *
     * @return True, if the enemy is facing the player
     */
    protected boolean facingPlayer() {
        return (this.getWorld().getPlayer().getPos().getX() < this.getPos().getX() && !this.isFacingRight()) ||
                (this.getWorld().getPlayer().getPos().getX() > this.getPos().getX() && this.isFacingRight());
    }

    /**
     * Method that defines the common collision hit behaviour between a generic enemy and the player
     */
    protected void checkPlayerCollision() {
        if (this.getWorld().getPlayer().checkCollision(this) && this.hitTicks == 0) {
            this.attackPlayer();
            this.hitTicks = HIT_COOLDOWN;
        }
    }

    /**
     * Utility method that keeps track of the hit ticks when needed
     */
    private void updateHitTicks() {
        if (this.hitTicks > 0) {
            this.hitTicks--;
        }
    }

    /**
     * Utility method that keeps track of the shooting cooldown when needed
     */
    protected void updateShootingCooldownTicks() {
        if (this.shootingCooldownTicks > 0) {
            this.shootingCooldownTicks--;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void die() {
        super.die();
        this.getWorld().getPlayer().addScore(this.getScoreValue());
        this.getWorld().getPlayer().heal(this.getHealValue());
        this.getWorld().getPlayer().addStamina(this.getStaminaValue());
    }

    /**
     * Method that defines the common death behaviour for all enemies.
     * It returns a boolean due to being the only thing to do when an Enemy dies,
     * therefore when it returns true, the tick function stops.
     *
     * @return True, if the Enemy is dead.
     */
    private boolean checkDyingBehaviour() {
        if (this.isDead()) {
            this.resetCurrentState(EnemyState.DEAD);
        }
        if (this.getCurrentState() == EnemyState.DEAD) {
            this.deathTicks++;
            if (this.deathTicks == DEATH_DURATION) {
                this.remove();
            }
            return true;
        }
        return false;
    }

    /**
     * Utility method to spawn a generic projectile
     *
     * @param projectileSpeed The travel speed of the projectile
     * @param type            The type of projectile
     */
    protected void shoot(final double projectileSpeed, final EntityType type) {
        Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(type);
        if (opt.isPresent()) {
            SimpleProjectile e = ((SimpleProjectile) opt.get());
            e.setPos(this.getPos().addVector(new Vector2d(0, this.getType().getHeight() / 2d)));
            e.setDirAndSpeed(this.isFacingRight() ? 1 : -1, projectileSpeed);
            this.getWorld().addEntity(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
        if (this.checkDyingBehaviour()) {
            return;
        }
        this.updateHitTicks();
        this.checkPlayerCollision();
        this.updateShootingCooldownTicks();
    }
}
