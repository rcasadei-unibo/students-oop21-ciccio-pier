package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.LivingEntity;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.PlayerView;

import java.util.Comparator;

public class PlayerImpl extends SimpleLivingEntity implements Player {
    private static final int SPEED = 7;
    private static final int ATTACK_RANGE = 5 * Block.SIZE;
    private static final int ATTACK_COOLDOWN = 1 * GameLoop.TPS;
    private static final int MAX_STAMINA = 100;
    private final PlayerView playerView;
    private int attackCooldownTicks;
    private int stamina;
    private int speedModifier;
    private int jumpModifier;
    private int score;
    private int coin;
    private boolean won;

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
        if (this.attackCooldownTicks == ATTACK_COOLDOWN) {
            this.getWorld().getEntitiesInRange(this.getPos(), ATTACK_RANGE).stream().filter(t -> t instanceof LivingEntity)
                    .map(LivingEntity.class::cast).sorted( (o1, o2) -> {
                        if (Math.abs(getPos().getX() - o1.getPos().getX()) < Math.abs(getPos().getX() - o2.getPos().getX())) {
                            return 1;
                        }
                        return -1;
                    }).findFirst().ifPresent(t -> t.damage(this.getType().getAttackDamage()));
            this.attackCooldownTicks = 0;
        }
    }

    /**
     * Called every tick: if player is waiting for the attack cooldown,
     * it gets updated
     */
    private void updateAttackCooldown() {
        if (this.attackCooldownTicks < ATTACK_COOLDOWN) {
            this.attackCooldownTicks++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
        this.updateAttackCooldown();
        this.move();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean jump() {
        final boolean jumped = super.jump();
        if (jumped) {
            this.decreaseStamina(5);
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
    public void setJumpModifier(final int modifier) {
        this.jumpModifier += modifier;
    }

    @Override
    public void setSpeedModifier(final int modifier) {
        this.jumpModifier += modifier;
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
