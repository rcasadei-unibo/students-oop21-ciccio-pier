package it.unibo.cicciopier.model.entities.base;

/**
 * Represents a type of Entity with its details
 */
public enum EntityType {
    /**
     * Represents the Player
     */
    PLAYER(32, 64, 100, 100),
    /**
     * Represents the Missile that the enemy will launch
     */
    MISSILE(20, 50, 0, 50),
    /**
     * Represents the laser that the Boss will shoot
     */
    LASER(0, 0, 0, 20),
    /**
     * Represents the projectile that will be launched by the boss
     */
    CANNON_BALL(16, 16, 0, 30),
    /**
     * Represents a coin
     */
    COIN(32, 32, 0, 0),
    /**
     * Represents a fried chicken, an unhealthy food
     */
    CHICKEN(32,32,0,0),
    /**
     * Represents an explosion
     */
    EXPLOSION(1, 1, 0, 0),
    /**
     * Represents a ShootingPea
     */
    SHOOTING_PEA(32,64,100,50);

    private final int width;
    private final int height;
    private final int maxHp;
    private final int attackDamage;

    /**
     * Entity's types constructor
     *
     * @param width        The Entity's width
     * @param height       The Entity's height
     * @param maxHp        The Entity's total hp
     * @param attackDamage The Entity's attack damage
     */
    EntityType(final int width, final int height, final int maxHp, final int attackDamage) {
        this.width = width;
        this.height = height;
        this.maxHp = maxHp;
        this.attackDamage = attackDamage;
    }

    /**
     * Returns the Entity width
     *
     * @return Entity's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the Entity height
     *
     * @return Entity's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the Entity total hp
     *
     * @return Entity's maximum hp
     */
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * Returns the Entity attack damage
     *
     * @return Entity's damage
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }
}
