package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
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
    private static final int MAX_DISTANCE = 400;
    private static final int MAX_SPEED = 2;

    private Vector2d startLine;
    private int currentDistance;
    private final LaserView laserView;
    private boolean isOnce;

    /**
     * Constructor for this class, create a laser instance
     *
     * @param world {@link World}
     */
    public Laser(final World world) {
        super(EntityType.LASER, world);
        this.currentDistance = 0;
        this.isOnce = false;
        //this.startLine = new Vector2d(0, 0);
        this.laserView = new LaserView(this);
    }

    /**
     * Find the direction that the laser needs to move and set the endLine variable
     */
    private void seek() {
        // desired Velocity of the laser
        final Vector2d desire = this.getPos().directionVector(this.getWorld().getPlayer().getPos().clone());

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
     * Check if the laser collides with the player or a block or the map
     */
    public void laserCheckCollision() {
        final int endLineOffsetX = getPos().getX() + this.getVel().getX();
        final int x = (int) (Math.floor(endLineOffsetX) / Block.SIZE);
        final int y = (int) (Math.floor(this.getPos().getY() + this.getVel().getY()) / Block.SIZE);

        //if its collides with the beginning or the end of the world
        if (endLineOffsetX <= 0 || endLineOffsetX >= Block.SIZE * this.getWorld().getWidth()) {
            setVel(new Vector2d(0, 0));
            return;
        }
        Block block = getWorld().getBlock(x, y - 1);
        Line2D line2D = new Line2D.Double(
                this.getPos().getDoubleX(),
                this.getPos().getDoubleY(),
                this.getPos().getDoubleX(),
                this.getPos().getDoubleY()
        );
        //damage the player if the line intersects with the player
        if (line2D.intersects(this.getWorld().getPlayer().getBounds())) {
            this.getWorld().getPlayer().damage(this.getType().getAttackDamage());
        }
        if (block.getType().isSolid()) {
            if (line2D.intersects(block.getBounds())) {
                this.setVel(new Vector2d(0, 0));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        if (!this.isOnce) {
            this.seek();
            this.isOnce = true;
        } else {
            this.laserCheckCollision();
            this.getPos().add(this.getVel());
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
        return this.startLine;
    }

    /**
     * Set the position of where the line will start
     */
    public void setStartLine(final Vector2d start) {
        this.startLine = start;
    }

    /**
     * Get the position of where the line will end
     *
     * @return line ending position
     */
    public Vector2d getEndLine() {
        return this.getPos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.laserView;
    }
}


