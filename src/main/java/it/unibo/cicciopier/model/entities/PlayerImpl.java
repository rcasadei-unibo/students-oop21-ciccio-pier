package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.model.entities.enemies.EnemyState;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.PlayerView;

import java.util.Optional;

public class PlayerImpl extends SimpleLivingEntity implements Player {
    private static final int ATTACK_RANGE = 3 * Block.SIZE;
    private static final int ATTACK_COOLDOWN = GameLoop.TPS;
    private static final int ATTACK_DURATION = 20;
    private static final int SPEED = 4;
    private final PlayerView playerView;
    private int attackCooldownTicks;
    private int stamina;
    private int speedModifier;
    private int jumpModifier;
    private boolean isInvulnerable;
    private int score;
    private int coin;
    private boolean won;
    private int attackTimer;
    private int takenDamage;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public PlayerImpl(final World world) {
        super(EntityType.PLAYER, world);
        this.playerView = new PlayerView(this);
        this.attackCooldownTicks = ATTACK_COOLDOWN;
        this.stamina = this.getMaxStamina();
        this.speedModifier = 0;
        this.jumpModifier = 0;
        this.score = 0;
        this.coin = 0;
        this.attackTimer = 0;
        this.won = false;
        this.takenDamage = 0;
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
    public int getSpeed() {
        if (this.getStamina() <= Stamina.FATIGUE) {
            return (int) ((SPEED + speedModifier) * Stamina.FATIGUE_SPEED_DEBUFF);
        }
        return SPEED + speedModifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoin() {
        return this.coin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore(final int score) {
        this.score += score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCoin() {
        this.coin++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStamina() {
        return this.stamina;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxStamina() {
        return Stamina.MAX_PLAYER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStamina(final int amount) {
        this.stamina += amount;
        if (this.stamina > this.getMaxStamina()) {
            this.stamina = this.getMaxStamina();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseStamina(final int amount) {
        this.stamina -= amount;
        if (this.stamina < 0) {
            this.stamina = 0;
            this.damage(Stamina.HEALTH_DECREASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getJumpForce() {
        if (this.getStamina() <= Stamina.FATIGUE) {
            return (int) ((super.getJumpForce() + this.jumpModifier) * Stamina.FATIGUE_JUMP_DEBUFF);
        }
        return super.getJumpForce() + this.jumpModifier;
    }

    /**
     * Method to check if there are blocks in front of the player
     *
     * @param enemyX The x-value of the Enemy position
     * @return True, if there are blocks in the way
     */
    private boolean blockControl(final int enemyX) {
        int startX = (int) (this.getPos().getX() / Block.SIZE);
        int endX = (int) (enemyX / Block.SIZE);
        int y = this.getPos().getY() / Block.SIZE;
        if (this.isFacingRight()) {
            for (int i = startX; i <= endX; i++) {
                Block block = this.getWorld().getBlock(i, y);
                if (block.isSolid()) {
                    return true;
                }
            }
        } else {
            for (int i = startX; i >= endX; i--) {
                Block block = this.getWorld().getBlock(i, y);
                if (block.isSolid()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to check if the player can see part of the Enemy
     *
     * @param enemy        The Enemy to consider
     * @param playerCenter The central position of the player
     * @return True if the player can actually see the Enemy
     */
    private boolean canSeeEnemy(final LivingEntity enemy, final Vector2d playerCenter) {
        return playerCenter.getY() >= enemy.getPos().getY() && playerCenter.getY() <= (enemy.getPos().getY() + enemy.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attackNearest() {
        //if the player is not in the ground, then it cannot attack
        if (this.getCurrentState() == EntityState.JUMPING) {
            return;
        }
        if (this.attackCooldownTicks == ATTACK_COOLDOWN) {
            this.setCurrentState(EntityState.ATTACKING);
            final Vector2d playerCenter = this.getPos().addVector(new Vector2d(this.getWidth() / 2d, this.getHeight() / 2d));
            this.getWorld().getEntitiesInRange(playerCenter, ATTACK_RANGE).stream()
                    .filter(t -> t instanceof LivingEntity)
                    .map(LivingEntity.class::cast)
                    .filter(t -> {
                        Vector2d enemyCenter = t.getPos().addVector(new Vector2d(t.getWidth() / 2d, t.getHeight() / 2d));
                        if (t.isDead() || t.getCurrentState() == EnemyState.HIDDEN) {
                            return false;
                        }
                        if (this.blockControl(enemyCenter.getX())) {
                            return false;
                        }
                        if (!this.canSeeEnemy(t, playerCenter)) {
                            return false;
                        }
                        if (this.isFacingRight()) {
                            return enemyCenter.getX() > playerCenter.getX();
                        }
                        return enemyCenter.getX() < playerCenter.getX();
                    })
                    .sorted((t1, t2) -> {
                        if (Math.abs(playerCenter.getX() - t1.getPos().getX()) < Math.abs(playerCenter.getX() - t2.getPos().getX())) {
                            return -1;
                        } else if (Math.abs(playerCenter.getX() - t1.getPos().getX()) > Math.abs(playerCenter.getX() - t2.getPos().getX())) {
                            return 1;
                        }
                        return 0;
                    })
                    .findFirst()
                    .ifPresent(t -> {
                        t.damage(this.getType().getAttackDamage());
                        Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(EntityType.BITE);
                        if (opt.isPresent()) {
                            Entity e = opt.get();
                            e.setPos(t.getPos().addVector(new Vector2d(-e.getWidth() / 2d + t.getWidth() / 2d, -e.getHeight() / 2d + t.getHeight() / 2d)));
                            this.getWorld().addEntity(e);
                        }
                    });
            this.attackCooldownTicks = 0;
        }
    }

    /**
     * Utility method called every tick used to update the player's attack cooldown when needed
     */
    private void updateAttackCooldown() {
        if (this.attackCooldownTicks < ATTACK_COOLDOWN) {
            this.attackCooldownTicks++;
        }
        //update the time
        if (this.getCurrentState() == EntityState.ATTACKING) {
            this.attackTimer++;
        }
        if (this.attackTimer >= ATTACK_DURATION) {
            this.resetCurrentState(EntityState.IDLE);
        }
        if (this.getOldState() == EntityState.ATTACKING && this.getCurrentState() != EntityState.ATTACKING) {
            this.attackTimer = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentState(final EntityState state) {
        if (this.getCurrentState() == EntityState.IDLE ||
                state == EntityState.DEAD ||
                this.getCurrentState() == EntityState.RUNNING) {
            this.resetCurrentState(state);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
        this.updateAttackCooldown();
        this.move();
        //update entity state
        if (this.getVel().getX() != 0) {
            this.setCurrentState(EntityState.RUNNING);
        } else {
            this.setCurrentState(EntityState.IDLE);
        }
        if (this.takenDamage > 0) {
            this.takenDamage++;
        }
        if (this.takenDamage >= 3) {
            this.takenDamage = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean jump() {
        final boolean jumped = super.jump();
        if (jumped) {
            this.decreaseStamina(Stamina.JUMP_DECREASE);
            AudioController.getInstance().playSound(Sound.JUMP);
        }
        return jumped;
    }

    /**
     * Get if the player is taking damage
     *
     * @return true if is taking damage else false
     */
    public boolean hasTakenDamage() {
        return this.takenDamage > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.playerView;
    }

    @Override
    public void damage(int amount) {
        if (!this.isInvulnerable) {
            super.damage(amount);
            this.takenDamage = 1;
            AudioController.getInstance().playSound(Sound.DAMAGE);
        }
    }

    @Override
    public void setJumpModifier(final int modifier) {
        this.jumpModifier += modifier;
    }

    @Override
    public void setSpeedModifier(final int modifier) {
        this.speedModifier += modifier;
    }

    @Override
    public void setInvulnerability(final boolean active) {
        this.isInvulnerable = active;
    }

    @Override
    public boolean hasWon() {
        return won;
    }

    @Override
    public void win() {
        this.won = true;
    }
}
