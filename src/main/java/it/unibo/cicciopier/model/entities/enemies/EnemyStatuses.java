package it.unibo.cicciopier.model.entities.enemies;

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
    NINJA_POTATO_HIDDEN(1, 1, 4),
    /**
     * Represents the entity standing still
     */
    SHOOTING_PEA_IDLE(5, 1, 0),
    NINJA_POTATO_IDLE(5, 3, 0),
    /**
     * Represents the entity walking
     */
    SHOOTING_PEA_WALKING(13, 2, 1),
    /**
     * Represents the entity shooting at the player
     */
    SHOOTING_PEA_SHOOTING(9, 2, 2),
    /**
     * Represents the entity swinging at the player
     * The #1 is the drawing out the sword
     * The #2 is putting the sword away
     */
    NINJA_POTATO_SWING_1(10, 1, 2),
    NINJA_POTATO_SWING_2(10, 1, -2),
    /**
     * Represents the entity dying
     */
    SHOOTING_PEA_DYING(11, 4, 3),
    NINJA_POTATO_DYING(9, 3, 3);

    private final int frames;
    private final int durationSeconds;
    private final int row;

    /**
     * Constructor for the entity's statuses
     *
     * @param frames The number of frames for the status's animation
     */
    EnemyStatuses(final int frames, final int seconds, final int row) {
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
     * @return the duration (in seconds)
     */
    public int getDuration() {
        return this.durationSeconds;
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
