package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;

/**
 * Create a simple laser attack
 */
public class Laser extends SimpleMovingEntity {
    private static final int MAX_TIME = 2_000_000_000; //2 seconds
    private static final int MAX_DISTANCE = 100;
    private static final int MAX_SPEED = 6;

    private Vector2d endLine;
    private final long initialTime;
    private int currentDistance;
    private boolean isMaxDistance;

    /**
     * Constructor for this class, create a laser instance
     *
     * @param world {@link World}
     */
    public Laser(final World world) {
        super(EntityType.LASER, world);
        this.initialTime = System.nanoTime();
        this.seek();
    }

    /**
     * Find the direction that the laser needs to move and set the endLine variable
     */
    private void seek() {
        // desired Velocity of the laser
        final Vector2d desire = this.endLine.directionVector(this.getWorld().getPlayer().getPos());
        // fix this with the boss coordinate not player
        final int bossY = this.getPos().getY() + this.getWorld().getPlayer().getType().getHeight();

        this.endLine = new Vector2d(desire.getX(), bossY);
        //set the y coordinate to 0 so the laser can only move in the X direction
        desire.set(desire.getX(), 0);
        desire.setMagnitude(Laser.MAX_SPEED);
        this.setVel(desire);
    }

    /**
     * Check if the max distance is reached
     *
     * @return true if reached else false
     */
    public boolean isMaxDistance() {
        return this.isMaxDistance;
    }

    /**
     * Check if the max distance that the laser can travel has been reached
     */
    private void isMaxDistanceReached() {
        if (this.currentDistance >= Laser.MAX_DISTANCE) {
            this.isMaxDistance = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        final long currentTime = System.nanoTime();
        final long deltaTime = currentTime - this.initialTime;

        //if 2 seconds is not passed then don't do anything
        if (deltaTime < Laser.MAX_TIME) {
            return;
        }
        //if i reached the distance i have to travel stop the update and return
        if (this.isMaxDistance) {
            return;
        } else {
            this.isMaxDistanceReached();
        }
        this.endLine.add(this.getVel());
        //update the current Distance
        this.currentDistance += this.getVel().getX();
    }

    /**
     * Get the position of where the line will start
     *
     * @return line starting position
     */
    public Vector2d getStartLine() {
        return this.getPos();
    }

    /**
     * Get the position of where the line will end
     *
     * @return line ending position
     */
    public Vector2d getEndLine() {
        return this.endLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return null;
    }
}


