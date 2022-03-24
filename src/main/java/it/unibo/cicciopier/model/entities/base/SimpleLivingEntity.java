package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.utility.Vector2d;

public abstract class SimpleLivingEntity extends SimpleMovingEntity implements LivingEntity {
    private static final int MAX_GRAVITY = 20;

    private final Vector2d gravity;
    private final int maxHp;
    private int hp;
    private boolean jumping;
    private boolean dead;

    /**
     * Constructor for this class
     *
     * @param type  The Entity's type
     * @param world The game's world
     */
    public SimpleLivingEntity(final EntityType type, final World world) {
        super(type, world);
        this.jumping = false;
        this.maxHp = this.getType().getMaxHp();
        this.hp = this.maxHp;
        this.dead = false;
        this.gravity = new Vector2d(0, 1);
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
    public void damage(final int amount) {
        this.hp -= amount;
        if (this.hp <= 0) {
            this.hp = 0;
            this.dead = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void heal(final int amount) {
        this.hp += amount;
        if (this.hp > this.maxHp) {
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

    /**
     * Function that check collisions and moves the entity
     */
    protected void move() {
        if (this.getVel().getX() > 0) {
            //check right collision
            final int rightOffset = this.rightCollision();

            if (rightOffset == 0 || rightOffset == -2) {
                this.getVel().setX(0);
            } else if (rightOffset > 0) {
                this.getVel().setX(rightOffset);
            }
        } else if (this.getVel().getX() < 0) {
            //check left collision
            final int leftOffset = this.leftCollision();

            if (leftOffset == 0 || leftOffset == 2) {
                this.getVel().setX(0);
            } else if (leftOffset < 0) {
                this.getVel().setX(leftOffset);
            }
        }
        if (this.getVel().getY() > 0) {
            //check bottom collision
            final int bottomOffset = this.bottomCollision();

            if (bottomOffset == 0) {
                this.getVel().setY(0);
            } else if (bottomOffset > 0) {
                this.getVel().setY(bottomOffset);
            }
        } else if (this.getVel().getY() < 0) {
            //check up collision
            final int upOffset = this.upCollision();

            if (upOffset == 0 || upOffset == 2) {
                this.getVel().setY(0);
            } else if (upOffset < 0) {
                this.getVel().setY(upOffset);
            }
        }
        this.getPos().add(this.getVel());
        //add gravity to the entity
        if (this.getVel().getDoubleY() < SimpleLivingEntity.MAX_GRAVITY) {
            this.getVel().add(gravity);
        }
    }
}
