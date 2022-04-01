package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.LaserView;

import java.awt.geom.Line2D;

/**
 * Create a simple laser attack
 */
public class Laser extends SimpleMovingEntity {
    private static final int MAX_TIME = 100; //1 seconds
    private static final int MAX_DISTANCE = 3000;
    private static final int MAX_SPEED =7;

    private int time;
    private final Vector2d endLine;
    private int currentDistance;
    private boolean isMaxDistance;
    private final LaserView laserView;
    private boolean isOnce;

    /**
     * Constructor for this class, create a laser instance
     *
     * @param world {@link World}
     */
    public Laser(final World world) {
        super(EntityType.LASER, world);
        this.setPos(new Vector2d(992, 512));
        this.endLine = new Vector2d(world.getPlayer().getPos().getX(), this.getBounds().getMaxY());
        this.laserView = new LaserView(this);
        this.time = 0;
        this.currentDistance = 0;
        this.isOnce = false;
    }

    /**
     * Find the direction that the laser needs to move and set the endLine variable
     */
    private void seek() {

        // desired Velocity of the laser
        final Vector2d desire = this.endLine.directionVector(this.getWorld().getPlayer().getPos().clone());

        // fix this with the boss coordinate not player
        //final int bossY = this.getPos().getY() + this.getWorld().getPlayer().getHeight();

        //set the y coordinate to 0 so the laser can only move in the X direction
        int xVel = desire.getX();
        desire.setY(0);
        if (xVel != 0) {
            desire.setMagnitude(Laser.MAX_SPEED);
        } else {
            xVel = Math.random() >= 0.5 ? Laser.MAX_SPEED : -Laser.MAX_SPEED;
            desire.setX(xVel);
        }
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

    public void laserCheckCollision() {
        final int x = (int) (Math.floor(this.endLine.getX() + this.getVel().getX()) / Block.SIZE);
        final int y = (int) (Math.floor(this.endLine.getY() + this.getVel().getY()) / Block.SIZE);
        Block block = getWorld().getBlock(x, y-1);
        Line2D line2D = new Line2D.Double(
                this.getPos().getDoubleX(),
                this.getPos().getDoubleY(),
                this.endLine.getDoubleX(),
                this.endLine.getDoubleY()
        );
        if(block.getType() != BlockType.AIR){
            if (line2D.intersects(block.getBounds())) {
                setVel(new Vector2d(0, 0));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        if (!this.isOnce) {
            this.time++;
            if (time >= Laser.MAX_TIME) {
                this.seek();
                this.isOnce = true;
            }
        } else {
            this.laserCheckCollision();
            this.endLine.add(this.getVel());
            this.currentDistance += Laser.MAX_SPEED;
        }
        //if reached the max distance needed to move, remove the laser
        if (this.currentDistance >= Laser.MAX_DISTANCE) {
            this.remove();
        }
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
        return this.laserView;
    }
}


