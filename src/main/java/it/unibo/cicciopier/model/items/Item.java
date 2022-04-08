package it.unibo.cicciopier.model.items;

public enum Item {
    /**
     * Represents a coin
     */
    COIN(10, 0, 0, 0),
    /**
     * Represents an unhealthy food
     */
    CHICKEN(15, 10, 4, 0),
    /**
     * Represents a jumping power-up
     */
    JUMP_BOOST(0, 0, 0, 6);

    private final int score;
    private final int stamina;
    private final int health;
    private final int boost;

    /**
     * Create a instance of an item
     *
     * @param score   the item score to add to the player
     * @param stamina the item stamina to add to the player
     * @param health  the item health to add to the player
     */
    Item(final int score, final int stamina, final int health, final int boost) {
        this.score = score;
        this.stamina = stamina;
        this.health = health;
        this.boost = boost;
    }

    /**
     * Get how much to add to the player score
     *
     * @return score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Get how much to add to the player stamina
     *
     * @return stamina
     */
    public int getStamina() {
        return this.stamina;
    }

    /**
     * Get how much to add to the player health
     *
     * @return health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Get the boost modifier of an item
     *
     * @return boost
     */
    public int getBoost() {
        return this.boost;
    }

}
