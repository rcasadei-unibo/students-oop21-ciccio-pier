package it.unibo.cicciopier.model.entities.enemies;

/**
 * Represents an enemy whom behaviour is defined on a path it patrols
 */
public interface PathEnemy extends Enemy {
    /**
     * Retrieves the path offset
     *
     * @return The offset of the max path to the right
     */
    int getMaxRightOffset();

    /**
     * Retrieves the attack range of the Enemy
     *
     * @return The Enemy's attack range
     */
    int getAttackRange();

    /**
     * Retrieves the movement speed of the Enemy
     *
     * @return The Enemy's movement speed
     */
    double getMovementSpeed();

    /**
     * Retrieves the idle duration of the Enemy
     *
     * @return The Enemy's idle duration
     */
    double getIdleDuration();

    /**
     * Retrieves the left path extreme
     *
     * @return The left path extreme
     */
    int getLeftPathfurthest();

    /**
     * Retrieves the right path extreme
     *
     * @return The right path extreme
     */
    int getRightPathfurthest();

    /**
     * Retrieves the idle status of the Enemy
     *
     * @return The Enemy's idle status
     */
    EnemyStatuses getIdleStatus();

    /**
     * Retrieves the walking status of the Enemy
     *
     * @return The Enemy's walking status
     */
    EnemyStatuses getWalkingStatus();
}
