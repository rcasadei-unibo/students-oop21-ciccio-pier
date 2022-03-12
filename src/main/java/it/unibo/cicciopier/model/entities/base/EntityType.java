package it.unibo.cicciopier.model.entities.base;

/**
 * An {@link Enum} representing an Entity with its details
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
     * @return An {@link Integer} representing the Entity width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return An {@link Integer} representing the Entity height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return An {@link Integer} representing the Entity maximum amount of HP
     */
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * @return An {@link Integer} representing amount of damage the Entity deals
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }
}
