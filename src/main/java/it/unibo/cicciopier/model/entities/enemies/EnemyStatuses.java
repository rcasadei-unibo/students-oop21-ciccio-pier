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
    MIND_PINEAPPLE_ANGERED(5, 0.4, 2),
    /**
     * Represents the entity starting to cry
     */
    CRYING_ONION_CRYING(8, 1, 3),
    /**
     * Represents the entity standing still
     */
    SHOOTING_PEA_IDLE(5, 0.5, 0),
    NINJA_POTATO_IDLE(5, 0.3, 0),
    ROLLING_PEACH_IDLE(6, 1.5, 0),
    CRYING_ONION_IDLE(5, 0.8, 0),
    MIND_PINEAPPLE_IDLE(5, 1, 0),
    /**
     * Represents the entity walking
     */
    SHOOTING_PEA_WALKING(13, 0.5, 1),
    ROLLING_PEACH_WALKING(6, 2, 1),
    CRYING_ONION_WALKING_NORMAL(9, 1, 1),
    CRYING_ONION_WALKING_CRYING(9, 0.4, 2),
    /**
     * Represents the entity switching between hidden and swing
     */
    NINJA_POTATO_JUMPING_OUT(6, 0.1, 1),
    NINJA_POTATO_JUMPING_IN(6, 0.2, -1),
    /**
     * Represents the entity shooting at the player
     */
    SHOOTING_PEA_SHOOTING(9, 1, 2),
    /**
     * Represents the entity rolling towards the player
     */
    ROLLING_PEACH_ROLLING(4, 2, 3),
    /**
     * Represents the entity mind-attacking the player
     */
    MIND_PINEAPPLE_TELEKINESIS(5,2,1),
    /**
     * Represents the entity swinging at the player
     * The #1 is the drawing out the sword
     * The #2 is putting the sword away
     */
    NINJA_POTATO_SWING_1(10, 0.2, 2),
    NINJA_POTATO_SWING_2(10, 0.5, -2),
    /**
     * Represents the entity dying
     */
    SHOOTING_PEA_DYING(11, 2, 3),
    NINJA_POTATO_DYING(9, 1.5, 3),
    ROLLING_PEACH_DYING(9, 1.5, 4),
    CRYING_ONION_DYING(8, 2, 4),
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
