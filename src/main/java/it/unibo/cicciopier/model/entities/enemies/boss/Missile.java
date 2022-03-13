package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;

/**
 * Create a simple missile that chase the player
 */
public class Missile extends SimpleMovingEntity {
    private final Vector2d accel;

    private static final int MAX_DISTANCE = 201;
    private static final int MAX_ANGLE = 46; //in degree
    private static final int MAX_SPEED = 5;
    private static final double MAX_STEERING = 0.3d;
    private static final int MAX_TIME = 2_000_000; //2 seconds

    private final int maxTravelDistance;
    private final int initialDistance;
    private final long initialTime;

    /**
     * Constructor for this class, create a Missile instance
     *
     * @param world {@link World}
     */
    public Missile(final World world) {
        super(EntityType.MISSILE, world);
        this.setVel(new Vector2d(0, -10));
        this.accel = new Vector2d();
        this.maxTravelDistance = (int) (Math.random() * Missile.MAX_DISTANCE);
        this.initialDistance = this.getPos().getY();
        this.initialTime = System.nanoTime();
        //rotate by a random number
        this.getVel().rotateInDegree(this.randAngleInRange());
    }

    /**
     * Generate a random angle in range from -MAX_ANGLE to MAX_ANGLE
     *
     * @return angle in degree
     */
    private double randAngleInRange() {
        final double randNum = 0.5d;
        final double randAngleInDegree = Math.random() * Missile.MAX_ANGLE;

        if (Math.random() >= randNum) {
            return randAngleInDegree;
        } else {
            return -randAngleInDegree;
        }
    }

    /**
     * Check if the missile has travel the maxTravelDistance
     *
     * @return true if max distance reached else false
     */
    private boolean isMaxDistance() {
        return Math.abs(this.getPos().getY() - this.initialDistance) >= this.maxTravelDistance;
    }

    /**
     * Apply any type of force to the missile like wind force, gravity etc...
     *
     * @param force to apply to the missile
     */
    private void applyForce(final Vector2d force) {
        this.accel.add(force);
    }

    /**
     * Make the missile seek the player
     */
    private void seek() {
        // desired Velocity of the missile
        final Vector2d desire = this.getPos().directionVector(this.getWorld().getPlayer().getPos());
        desire.setMagnitude(Missile.MAX_SPEED);
        // steering force = desire - velocity
        final Vector2d steering = desire.subVector(this.getVel());
        steering.setLimiter(Missile.MAX_STEERING);
        this.applyForce(steering);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        final long currentTime = System.nanoTime();
        final long deltaTime = currentTime - this.initialTime;

        //if 2 seconds is not passed then don't do anything
        if (deltaTime < Missile.MAX_TIME) {
            return;
        }
        this.getVel().add(this.accel);
        this.getVel().setLimiter(Missile.MAX_SPEED);
        //seek the player if i reached the max distance
        if (isMaxDistance()) {
            this.seek();
        }
        this.getPos().add(this.getVel());
        //reset the accel vector
        this.accel.set(0, 0);
    }
}
