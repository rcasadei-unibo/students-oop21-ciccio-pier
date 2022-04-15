package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.PlayerView;

public class PlayerImpl extends SimpleLivingEntity implements Player {
    private static final int SPEED = 7;
    private static final int ATTACK_RANGE = 5 * Block.SIZE;
    private static final int ATTACK_COOLDOWN = 1 * GameLoop.TPS;
    private static final int MAX_STAMINA = 100;
    private static final int ATTACK_DURATION = 18;
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
        return SPEED + speedModifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoin() {
        return coin;
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
        return MAX_STAMINA;
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
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getJumpForce() {
        return super.getJumpForce() + this.jumpModifier;
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
            this.getWorld().getEntitiesInRange(this.getPos(), ATTACK_RANGE).stream()
                    .filter(t -> t instanceof LivingEntity)
                    .map(LivingEntity.class::cast).sorted((o1, o2) -> {
                if (Math.abs(getPos().getX() - o1.getPos().getX()) < Math.abs(getPos().getX() - o2.getPos().getX())) {
                    return 1;
                }
                return -1;
            }).filter(t -> this.isFacingRight() ? t.getPos().getX() > this.getPos().getX() : t.getPos().getX() < this.getPos().getX())
                    .findFirst().ifPresent(t -> t.damage(this.getType().getAttackDamage()));
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean jump() {
        final boolean jumped = super.jump();
        if (jumped) {
            this.decreaseStamina(5);
            AudioController.getInstance().playSound(Sound.JUMP);
        }
        return jumped;
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
        if (!isInvulnerable) {
            super.damage(amount);
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
