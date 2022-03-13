package it.unibo.cicciopier.model.entities;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;

public class PlayerImpl extends SimpleLivingEntity implements Player{

    //TO DEFINE STAMINA AMOUNT
    private final int maxStamina = 100;
    private final int attackDamage;
    private int stamina;

    public PlayerImpl(final EntityType type, final World world) {
        super(type, world);
        this.stamina = maxStamina;
        this.attackDamage = this.getType().getAttackDamage();
    }

    @Override
    public int getStamina() {
        return this.stamina;
    }

    @Override
    public int getMaxStamina() {
        return this.maxStamina;
    }

    @Override
    public void addStamina(final int amount) {
        this.stamina += amount;
        if (this.stamina > this.maxStamina){
            this.stamina = this.maxStamina;
        }
    }

    @Override
    public void decreaseStamina(final int amount) {
        this.stamina -= amount;
        if (this.stamina < 0){
            this.stamina = 0;
        }
    }

    @Override
    public void attackNearest() {
        //TODO
    }

    @Override
    public void tick() {
        //TODO
    }

    @Override
    public void jump() {
        //TODO
    }
}
