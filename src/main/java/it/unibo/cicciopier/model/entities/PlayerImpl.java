package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;

public class PlayerImpl extends SimpleLivingEntity implements Player {
    private static final int SPEED = 5;
    private static final int MAX_TIME = 35;
    private static final int JUMP_FORCE = 15;

    //TO DEFINE STAMINA AMOUNT
    private final int maxStamina = 100;
    private final int attackDamage;
    private int stamina;
    private int score;
    private int coin;
    private int time;
    private boolean isReady;

    //TESTING RENDER
    private boolean test = true;

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
        //TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        //update the time
        this.time++;
        if (this.time >= PlayerImpl.MAX_TIME) {
            //is ready to jump
            this.isReady = true;
        }
        //TODO
        this.move();

        //TESTING RENDER
        if (this.test){
            Entity e = this.getWorld().getEntityFactory().createEntity(EntityType.SHOOTING_PEA);
            e.setPos(new Vector2d(this.getPos().getX()+32,this.getPos().getY()));
            this.getWorld().addEntity(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void jump() {
        if (this.isReady && this.isGround()) {
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
