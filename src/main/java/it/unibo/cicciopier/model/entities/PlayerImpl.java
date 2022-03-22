package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;

public class PlayerImpl extends SimpleLivingEntity implements Player {

    //TO DEFINE STAMINA AMOUNT
    private final int maxStamina = 100;
    private final int attackDamage;
    private int stamina;
    private int score;
    private int coin;

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
        this.setVel(new Vector2d(0, 0));
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
        //TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void jump() {
        //TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return null;
    }
}
