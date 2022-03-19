package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.GameView;
import it.unibo.cicciopier.view.entities.enemies.ShootingPeaView;

public class ShootingPea extends SimpleEnemy {

    private final GameObjectView view;
    private Statuses status;

    /**
     * Represents the entity's status and their respective frames
     */
    public static enum Statuses {
        /**
         * Represents the entity standing still
         */
        IDLE(5),
        /**
         * Represents the entity shooting at the player
         */
        SHOOTING(10),
        /**
         * Represents the entity walking
         */
        WALKING(10);

        private final int frames;

        /**
         * Constructor for the entity's statuses
         *
         * @param frames The number of frames for the status's animation
         */
        Statuses(final int frames) {
            this.frames = frames;
        }

        /**
         * Method to get the number of frames for a specific animation
         *
         * @return The number of frames
         */
        public int getFrames() {
            return this.frames;
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
