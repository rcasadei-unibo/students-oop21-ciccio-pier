package it.unibo.cicciopier.model.entities.enemies;

public enum EnemyStatuses {

    /**
     * Represents the entity standing still
     */
    SHOOTING_PEA_IDLE(5, 1, 0),
    NINJA_POTATO_IDLE(5,3,0),
    /**
     * Represents the entity walking
     */
    SHOOTING_PEA_WALKING(13, 2, 1),
    /**
     * Represents the entity shooting at the player
     */
    SHOOTING_PEA_SHOOTING(9, 2, 2),
    /**
     * Represents the entity dying
     */
    SHOOTING_PEA_DYING(11, 2, 3);

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


    public int getFrames() {
        return this.frames;
    }


    public int getDuration() {
        return this.durationSeconds;
    }


    public int getRow() {
        return this.row;
    }

}
