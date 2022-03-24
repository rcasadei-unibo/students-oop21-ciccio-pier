package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.GameObject;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

public abstract class SimpleMovingEntity extends SimpleEntity implements MovingEntity {

    private Vector2d vel;

    /**
     * Constructor for this class
     *
     * @param type  The Entity's type
     * @param world The game's world
     */
    protected SimpleMovingEntity(final EntityType type, final World world) {
        super(type, world);
        this.vel = new Vector2d(0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d getVel() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVel(final Vector2d vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkCollision(final GameObject object) {
        return this.getBounds().intersects(object.getBounds());
    }

    /**
     * Create a rectangle with the position of the entity plus his velocity
     *
     * @return a rectangle with offset of the velocity
     */
    protected Rectangle rectangleOffset() {
        //get the entity pos with the offset of the velocity
        final Vector2d entityOffset = this.getPos().addVector(this.getVel());

        return new Rectangle(
                entityOffset.getX(),
                entityOffset.getY(),
                this.getWidth(),
                this.getHeight()
        );
    }

    /**
     * Check collision up of the entity with blocks
     *
     * @return 1 if doesn't collide,
     * 2 if collides with the beginning of the map in y direction,
     * <p>
     * n <= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int upCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();

        //check if the player collides with the beginning of the map
        if (entityHitBox.getY() < 0) {
            return -this.getPos().getY();
        } else if (entityHitBox.getY() == 0) {
            return 2;
        }
        //get the position of the entity in block position
        final int upperLeftX = (int) (Math.floor(entityHitBox.getX()) / Block.SIZE);
        final int upperLeftY = (int) (Math.floor(entityHitBox.getY()) / Block.SIZE);
        Block block = this.getWorld().getBlock(upperLeftX, upperLeftY);

        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getBounds().getMaxY() - this.getPos().getY());
            }
        }
        final int upperRightX = (int) (Math.floor(entityHitBox.getMaxX()) / Block.SIZE);
        final int upperRightY = (int) (Math.floor(entityHitBox.getY()) / Block.SIZE);
        block = this.getWorld().getBlock(upperRightX, upperRightY);

        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getBounds().getMaxY() - this.getPos().getY());
            }
        }
        final int middleBlocks = upperRightX - (upperLeftX + 2);

        //collision check with the blocks in the middle
        for (int i = 1; i <= middleBlocks; i++) {
            block = this.getWorld().getBlock(upperLeftX + i, upperLeftY);

            if (block.getType() != BlockType.AIR) {
                //check if they collide
                if (entityHitBox.intersects(block.getBounds())) {
                    return (int) (block.getBounds().getMaxY() - this.getPos().getY());
                }
            }
        }
        return 1;
    }

    /**
     * Check collision on the right hand side of the entity with blocks
     *
     * @return -1 if doesn't collide,
     * -2 if collides with the beginning of the map in x direction,
     * n >= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int rightCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();

        final int worldEndX = this.getWorld().getWidth() * Block.SIZE;
        //check if the player collide with the end of the map
        if (entityHitBox.getMaxX() >= worldEndX) {
            return worldEndX - (this.getPos().getX() + this.getWidth());
        } else if (entityHitBox.getMaxX() == worldEndX) {
            return -2;
        }
        final int upperRightX = (int) (Math.floor(entityHitBox.getMaxX()) / Block.SIZE);
        final int upperRightY = (int) (Math.floor(entityHitBox.getY()) / Block.SIZE);
        Block block = this.getWorld().getBlock(upperRightX, upperRightY);

        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getPos().getX() - this.getBounds().getMaxX());
            }
        }
        final int lowerRightX = (int) (Math.floor(entityHitBox.getMaxX()) / Block.SIZE);
        //get the block 1 block higher
        final int lowerRightY = (int) (Math.floor(entityHitBox.getMaxY()) / Block.SIZE) - 1;
        block = this.getWorld().getBlock(lowerRightX, lowerRightY);

        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getPos().getX() - this.getBounds().getMaxX());
            }
        }
        final int middleBlocks = lowerRightY - (upperRightY + 2);

        //collision check with the blocks in the middle
        for (int i = 1; i <= middleBlocks; i++) {
            block = this.getWorld().getBlock(upperRightX, upperRightY + i);
            if (block.getType() != BlockType.AIR) {
                //check if they collide
                if (entityHitBox.intersects(block.getBounds())) {
                    return (int) (block.getPos().getX() - this.getBounds().getMaxX());
                }
            }
        }
        return -1;
    }

    /**
     * Check collision on the left hand side of the entity with blocks
     *
     * @return 1 if doesn't collide,
     * 2 if collides with the beginning of the map,
     * n <= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int leftCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();

        //check if the player collides with the beginning of the map
        if (entityHitBox.getX() < 0) {
            return -this.getPos().getX();
        } else if (entityHitBox.getX() == 0) {
            return 2;
        }
        //get the position of the entity in block position
        final int upperLeftX = (int) (Math.floor(entityHitBox.getX()) / Block.SIZE);
        final int upperLeftY = (int) (Math.floor(entityHitBox.getY()) / Block.SIZE);
        Block block = this.getWorld().getBlock(upperLeftX, upperLeftY);

        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getBounds().getMaxX() - this.getPos().getX());
            }
        }
        final int lowerLeftX = (int) (Math.floor(entityHitBox.getX()) / Block.SIZE);
        //get the block 1 block higher
        final int lowerLeftY = (int) (Math.floor(entityHitBox.getMaxY()) / Block.SIZE) - 1;
        block = this.getWorld().getBlock(lowerLeftX, lowerLeftY);

        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getBounds().getMaxX() - this.getPos().getX());
            }
        }
        final int middleBlocks = lowerLeftY - (upperLeftY + 1);

        //collision check with the blocks in the middle
        for (int i = 1; i <= middleBlocks; i++) {
            block = this.getWorld().getBlock(upperLeftX, upperLeftY + i);
            if (block.getType() != BlockType.AIR) {
                //check if they collide
                if (entityHitBox.intersects(block.getBounds())) {
                    return (int) (block.getBounds().getMaxX() - this.getPos().getX());
                }
            }
        }
        return 1;
    }

    /**
     * Check collision on the bottom of the entity with blocks
     *
     * @return -1 if doesn't collide,
     * n >= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int bottomCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();

        final int worldEndY = this.getWorld().getHeight() * Block.SIZE;
        //check if the player collide with the end of the map
        if (entityHitBox.getMaxY() >= worldEndY) {
            //remove the entity
            this.remove();
            return -1;
        }
        //get the position of the entity in block position
        final int lowerLeftX = (int) (Math.floor(entityHitBox.getX()) / Block.SIZE);
        final int lowerLeftY = (int) (Math.floor(entityHitBox.getMaxY()) / Block.SIZE);

        Block block = this.getWorld().getBlock(lowerLeftX, lowerLeftY);
        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getPos().getY() - this.getBounds().getMaxY());
            }
        }
        final int lowerRightX = (int) (Math.floor(entityHitBox.getMaxX()) / Block.SIZE);
        final int lowerRightY = (int) (Math.floor(entityHitBox.getMaxY()) / Block.SIZE);
        block = this.getWorld().getBlock(lowerRightX, lowerRightY);

        if (block.getType() != BlockType.AIR) {
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getPos().getY() - this.getBounds().getMaxY());
            }
        }
        final int middleBlocks = (lowerRightX) - (lowerLeftX + 2);

        for (int i = 1; i <= middleBlocks; i++) {

            block = this.getWorld().getBlock(lowerLeftX + i, lowerLeftY);
            if (block.getType() != BlockType.AIR) {
                //check if they collide
                if (entityHitBox.intersects(block.getBounds())) {
                    return (int) (block.getPos().getY() - this.getBounds().getMaxY());
                }
            }
        }
        return -1;
    }
}
