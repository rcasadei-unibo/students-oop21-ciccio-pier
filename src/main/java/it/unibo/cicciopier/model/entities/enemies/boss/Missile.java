package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.MissileView;

import java.util.Random;

/**
 * Create a simple missile that chase the player
 */
public class Missile extends SimpleMovingEntity {
    private static final int MAX_TIME = 2_000_000_000; //2 seconds
    private static final int MIN_DISTANCE = 50;
    private static final int MAX_DISTANCE = 250;
    private static final int MAX_ANGLE = 46; //in degree
    private static final int MAX_SPEED = 30;
    private static final double MAX_STEERING = 0.4d;

    private final MissileView missileView;
    private final Vector2d accel;
    private final int maxTravelDistance;
    private int initialDistance;
    private final long initialTime;
    private boolean isOnce;

    /**
     * Constructor for this class, create a Missile instance
     *
     * @param world {@link World}
     */
    public Missile(final World world) {
        super(EntityType.MISSILE, world);
        this.setVel(new Vector2d(0, -12));
        this.accel = new Vector2d();
        this.maxTravelDistance = new Random().
                nextInt(Missile.MAX_DISTANCE - Missile.MIN_DISTANCE) + Missile.MIN_DISTANCE;
        this.initialTime = System.nanoTime();
        //rotate by a random number
        this.getVel().rotateInDegree(this.randAngleInRange());
        this.isOnce = false;
        this.missileView = new MissileView(this);
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
        //player pos with a offset
        final Vector2d playerPos = this.getWorld().getPlayer().getPos().clone().
                addVector(new Vector2d(100, 0));
        // desired Velocity of the missile
        final Vector2d desire = this.getPos().directionVector(playerPos);
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
        //do this only one time
        if (!this.isOnce) {
            final long currentTime = System.nanoTime();
            final long deltaTime = currentTime - this.initialTime;

            //if 2 seconds is not passed then don't do anything
            if (deltaTime < Missile.MAX_TIME) {
                return;
            } else {
                this.initialDistance = this.getPos().getY();
                this.isOnce = true;
            }
        }
        //seek the player if i reached the max distance
        if (this.isMaxDistance()) {
            this.seek();
        }
        this.getVel().add(this.accel);
        this.getVel().setLimiter(Missile.MAX_SPEED);
        this.getPos().add(this.getVel());
        //reset the accel vector
        this.accel.set(0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.missileView;
    }
}
