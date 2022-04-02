package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.PlayerView;

public class PlayerImpl extends SimpleLivingEntity implements Player {
    private static final int SPEED = 7;
    private final int maxStamina = 100;
    private final int attackDamage;
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
    public void attackNearest() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
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
}
