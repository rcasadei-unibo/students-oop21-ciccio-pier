package it.unibo.cicciopier.model.entities.base;

/**
 * Represents a type of Entity with its details
 */
public enum EntityType {

    PLAYER(32, 64, 100, 100);

    private final int width;
    private final int height;
    private final int maxHp;
    private final int attackDamage;

    EntityType(final int width, final int height, final int maxHp, final int attackDamage) {
        this.width = width;
        this.height = height;
        this.maxHp = maxHp;
        this.attackDamage = attackDamage;
    }

    /**
     * Returns the Entity width
     * @return Entity's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the Entity height
     * @return Entity's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the Entity total hp
     * @return Entity's maximum hp
     */
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * Returns the Entity attack damage
     * @return Entity's damage
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }
}
