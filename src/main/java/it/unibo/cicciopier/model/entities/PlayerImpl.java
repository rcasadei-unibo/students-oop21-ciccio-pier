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
    private final int maxStamina = 100;
    private int jumpModifier = 0;
    private final int attackDamage;
    private int attackCooldownTicks;
    private int stamina;
    private int score;
    private int coin;
    private final PlayerView playerView;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public PlayerImpl(final World world) {
        super(EntityType.PLAYER, world);
        this.stamina = maxStamina;
        this.attackDamage = this.getType().getAttackDamage();
        this.score = 0;
        this.coin = 0;
        this.attackCooldownTicks = ATTACK_COOLDOWN;
        this.playerView = new PlayerView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSpeed() {
        return SPEED;
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
        return this.maxStamina;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStamina(final int amount) {
        this.stamina += amount;
        if (this.stamina > this.maxStamina) {
            this.stamina = this.maxStamina;
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
                    .map(LivingEntity.class::cast).sorted(new Comparator<LivingEntity>() {
                        @Override
                        public int compare(LivingEntity o1, LivingEntity o2) {
                            if (Math.abs(getPos().getX() - o1.getPos().getX()) < Math.abs(getPos().getX() - o2.getPos().getX())) {
                                return 1;
                            }
                            return -1;
                        }
                    }).findFirst().ifPresent(t -> t.damage(this.attackDamage));
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
}
