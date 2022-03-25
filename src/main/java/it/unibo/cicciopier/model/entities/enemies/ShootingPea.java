package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.GameView;
import it.unibo.cicciopier.view.entities.enemies.ShootingPeaView;

public class ShootingPea extends SimpleEnemy {

    public static final int MAX_RIGHT_OFFSET = 10;
    private final GameObjectView view;
    //Can't be final due to later initialization of the pos
    private int leftPathOffset;
    private boolean pathInitialized;
    private Statuses status;
    //testing
    private int tmp = 0;

    /**
     * Represents the entity's status and their respective frames
     */
    public static enum Statuses {
        /**
         * Represents the entity standing still
         */
        IDLE(5,1),
        /**
         * Represents the entity shooting at the player
         */
        SHOOTING(10,3),
        /**
         * Represents the entity walking
         */
        WALKING(10,2);

        private final int frames;
        private final int durationSeconds;

        /**
         * Constructor for the entity's statuses
         *
         * @param frames The number of frames for the status's animation
         */
        Statuses(final int frames, final int seconds) {
            this.frames = frames;
            this.durationSeconds = seconds;
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
        public int getDuration(){
            return this.durationSeconds;
        }
    }

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public ShootingPea(final World world) {
        super(EntityType.SHOOTING_PEA, world);
        this.view = new ShootingPeaView(this);
        this.status = Statuses.IDLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        //TODO : per contatto, fai danno con metodo ereditato. Spawn proiettile, individua inoltre un modo per aggrare?
        if (!this.pathInitialized){
            this.leftPathOffset = this.getPos().getX();
            this.pathInitialized = true;
        }
        //testing
        this.tmp++;
        if (this.tmp == 200){
                this.status = Statuses.WALKING;
                this.tmp = 0;
        }
        System.out.println(this.status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void jump() {

    }

    /**
     * Method used to retrieve the entity's status
     *
     * @return The status
     */
    public Statuses getStatus() {
        return this.status;
    }
}
