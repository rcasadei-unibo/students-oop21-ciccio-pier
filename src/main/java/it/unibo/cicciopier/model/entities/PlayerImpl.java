package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.view.GameObjectView;

public class PlayerImpl extends SimpleLivingEntity implements Player {
    private static final int SPEED = 7;
    private static final int MAX_TIME = 35;
    private static final int JUMP_FORCE = 15;
    private final int maxStamina = 100;
    private final int attackDamage;
    private int stamina;
    private int score;
    private int coin;
    private int time;
    private boolean isReady;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     * @param type  The entity's type
     */
    public PlayerImpl(final EntityType type, final World world) {
        super(type, world);
        this.stamina = maxStamina;
        this.attackDamage = this.getType().getAttackDamage();
        this.score = 0;
        this.coin = 0;
        this.isReady = true;
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
        //TODO?
        //update the time
        this.time++;
        if (this.time >= PlayerImpl.MAX_TIME) {
            //is ready to jump
            this.isReady = true;
        }
        this.move();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void jump() {
        if (this.isReady && this.isGround()) {
            AudioController.getAudioController().playSound(Sound.JUMP);
            this.getVel().setY(-PlayerImpl.JUMP_FORCE);
            this.isReady = false;
            this.time = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return null;
    }
}
