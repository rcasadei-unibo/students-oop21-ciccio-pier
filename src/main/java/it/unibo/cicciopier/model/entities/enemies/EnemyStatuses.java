package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;

/**
 * Enum representing all enemies' s statuses with their respective view information.
 * Each status has its own frames, duration and number indicating the row where it is stored in the texture
 * NOTE: some animations for space optimization are complete only when played normally and in reverse in order,
 * hence some animation's row numbers are negative, meaning they have to be played this way
 */
public enum EnemyStatuses {

    /**
     * Represents the entity staying hidden
     */
    NINJA_POTATO_HIDDEN(5, 2, 4),
    /**
     * Represents the entity when entering angered state
     */
    ROLLING_PEACH_ANGERED(1, 1, 2),
    /**
     * Represents the entity when entering angered state
     */
    MIND_PINEAPPLE_ANGERED(5, 1, 2),
    /**
     * Represents the entity starting to cry
     */
    CRYING_ONION_CRYING(8, 1, 3),
    /**
     * Represents the entity idling
     */
    SHOOTING_PEA_IDLE(5, 1.5, 0),
    /**
     * Represents the entity idling
     */
    NINJA_POTATO_IDLE(5, 3, 0),
    /**
     * Represents the entity idling
     */
    ROLLING_PEACH_IDLE(6, 2, 0),
    /**
     * Represents the entity idling
     */
    CRYING_ONION_IDLE(5, 0.8, 0),
    /**
     * Represents the entity idling
     */
    MIND_PINEAPPLE_IDLE(5, 1, 0),
    /**
     * Represents the entity walking
     */
    SHOOTING_PEA_WALKING(13, 1, 1),
    /**
     * Represents the entity walking
     */
    ROLLING_PEACH_WALKING(6, 1, 1),
    /**
     * Represents the entity walking
     */
    CRYING_ONION_WALKING_NORMAL(9, 1, 1),
    /**
     * Represents the entity walking
     */
    CRYING_ONION_WALKING_CRYING(9, 0.4, 2),
    /**
     * Represents the entity switching between hidden and idling
     */
    NINJA_POTATO_JUMPING_OUT(6, 0.3, 1),
    /**
     * Represents the entity switching between hidden and idling
     */
    NINJA_POTATO_JUMPING_IN(6, 0.5, -1),
    /**
     * Represents the entity shooting at the player
     */
    SHOOTING_PEA_SHOOTING(9, 1.5, 2),
    /**
     * Represents the entity rolling towards the player
     */
    ROLLING_PEACH_ROLLING(4, 2, 3),
    /**
     * Represents the entity mind-attacking the player
     */
    MIND_PINEAPPLE_TELEKINESIS(5,2,1),
    /**
     * Represents the entity drawing out the sword swinging at the player
     */
    NINJA_POTATO_SWING_1(10, 0.4, 2),
    /**
     * Represents the entity putting the sword away
     */
    NINJA_POTATO_SWING_2(10, 1, -2),
    /**
     * Represents the entity dying
     */
    SHOOTING_PEA_DYING(11, 2, 3),
    /**
     * Represents the entity dying
     */
    NINJA_POTATO_DYING(9, 1.5, 3),
    /**
     * Represents the entity dying
     */
    ROLLING_PEACH_DYING(9, 1.5, 4),
    /**
     * Represents the entity dying
     */
    CRYING_ONION_DYING(8, 2, 4),
    /**
     * Represents the entity dying
     */
    MIND_PINEAPPLE_DYING(8, 2, 3);

    private final int frames;
    private final double durationSeconds;
    private final int row;

    /**
     * Constructor for the entity's statuses
     *
     * @param frames The number of frames for the status's animation
     */
    EnemyStatuses(final int frames, final double seconds, final int row) {
        this.frames = frames;
        this.durationSeconds = seconds;
        this.row = row;
    }

    /**
     * Returns the number of frames of which the animation is made of
     *
     * @return the number of frames
     */
    public int getFrames() {
        return this.frames;
    }

    /**
     * Returns the animation's duration
     *
     * @return the duration in game ticks
     */
    public double getDurationTicks() {
        return this.durationSeconds * GameLoop.TPS;
    }

    /**
     * Returns the number corresponding to the animation's row in its relative texture file
     *
     * @return the animation row
     */
    public int getRow() {
        return this.row;
    }

}
