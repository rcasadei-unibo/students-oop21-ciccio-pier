package it.unibo.cicciopier.model.entities.enemies;

/**
 * Represents the entity's status and their respective frames
 */
public enum ShootingPeaStatuses {

    /**
     * Represents the entity standing still
     */
    IDLE(5, 1, 0),
    /**
     * Represents the entity walking
     */
    WALKING(13, 2, 1),
    /**
     * Represents the entity shooting at the player
     */
    SHOOTING(10, 3, 2);

    private final int frames;
    private final int durationSeconds;
    private final int row;

    /**
     * Constructor for the entity's statuses
     *
     * @param frames The number of frames for the status's animation
     */
    ShootingPeaStatuses(final int frames, final int seconds, final int row) {
        this.frames = frames;
        this.durationSeconds = seconds;
        this.row = row;
    }

    /**
     * Method to get the number of frames for a specific animation
     *
     * @return The number of frames
     */
    public int getFrames() {
        return this.frames;
    }

    /**
     * Method to get the duration of the animation
     *
     * @return The duration, in seconds
     */
    public int getDuration() {
        return this.durationSeconds;
    }

    /**
     * Method to get the row of the animation
     *
     * @return The row of the animation
     */
    public int getRow() {
        return this.row;
    }

}
