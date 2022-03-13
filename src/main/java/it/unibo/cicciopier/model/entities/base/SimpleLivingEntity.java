package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.World;

public abstract class SimpleLivingEntity extends SimpleMovingEntity implements LivingEntity{

    private boolean jumping;
    private final int maxHp;
    private int hp;
    private boolean dead;

    /**
     * Constructor for this class
     * @param type The Entity's type
     * @param world The game's world
     */
    public SimpleLivingEntity(EntityType type, World world) {
        super(type, world);
        this.jumping = false;
        this.maxHp = this.getType().getMaxHp();
        this.hp = this.maxHp;
        this.dead = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHp() {
        return this.hp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void damage(int amount) {
        this.hp -= amount;
        if (this.hp <= 0 ) {
            this.hp = 0;
            this.dead = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void heal(int amount) {
        this.hp += amount;
        if (this.hp > this.maxHp){
            this.hp = this.maxHp;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isJumping() {
        return this.jumping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return this.dead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void die() {
        this.dead = true;
    }
}
