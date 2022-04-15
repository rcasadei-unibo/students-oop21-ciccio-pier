package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.blocks.base.ShapelessBlock;
import it.unibo.cicciopier.utility.Vector2d;

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
     * n <= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int upCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();
        //check if the player collides with the beginning of the map
        if (entityHitBox.getY() <= 0) {
            return -this.getPos().getY();
        }
        final int startX = (int) (entityHitBox.getX() / Block.SIZE);
        final int endX = (int) ((entityHitBox.getMaxX() - 1) / Block.SIZE);
        final int y = (int) (entityHitBox.getY() / Block.SIZE);
        for (int i = startX; i <= endX; i++) {
            Block block = this.getWorld().getBlock(i, y);
            //check if they collide
            if (block.isSolid() && entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getBounds().getMaxY() - this.getPos().getY());
            } else if (block.getType() != BlockType.AIR && !block.isSolid()) {
                ShapelessBlock shapeless = (ShapelessBlock) block;
                shapeless.onCollision(this);
            }
        }
        return 1;
    }

    /**
     * Check collision on the right hand side of the entity with blocks
     *
     * @return -1 if doesn't collide,
     * n >= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int rightCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();
        final int worldEndX = this.getWorld().getWidth() * Block.SIZE;
        //check if the player collide with the end of the map
        if (entityHitBox.getMaxX() >= worldEndX) {
            return worldEndX - (this.getPos().getX() + this.getWidth());
        }
        final int startY = (int) (entityHitBox.getY() / Block.SIZE);
        final int endY = (int) ((entityHitBox.getMaxY() - 1) / Block.SIZE);
        final int x = (int) ((entityHitBox.getMaxX() - 1) / Block.SIZE);
        for (int i = startY; i <= endY; i++) {
            Block block = this.getWorld().getBlock(x, i);
            //check if they collide
            if (block.isSolid() && entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getPos().getX() - this.getBounds().getMaxX());
            } else if (block.getType() != BlockType.AIR && !block.isSolid()) {
                ShapelessBlock shapeless = (ShapelessBlock) block;
                shapeless.onCollision(this);
            }
        }
        return -1;
    }

    /**
     * Check collision on the left hand side of the entity with blocks
     *
     * @return 1 if doesn't collide,
     * n <= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int leftCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();
        //check if the player collides with the beginning of the map
        if (entityHitBox.getX() <= 0) {
            return -this.getPos().getX();
        }
        final int startY = (int) (entityHitBox.getY() / Block.SIZE);
        final int endY = (int) ((entityHitBox.getMaxY() - 1) / Block.SIZE);
        final int x = (int) (entityHitBox.getX() / Block.SIZE);
        for (int i = startY; i <= endY; i++) {
            Block block = this.getWorld().getBlock(x, i);
            //check if they collide
            if (block.isSolid() && entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getBounds().getMaxX() - this.getPos().getX());
            } else if (block.getType() != BlockType.AIR && !block.isSolid()) {
                ShapelessBlock shapeless = (ShapelessBlock) block;
                shapeless.onCollision(this);
            }
        }
        return 1;
    }

    /**
     * Check collision on the bottom of the entity with blocks
     *
     * @return -1 if doesn't collide, -2 if collides with the bottom border
     * n >= 0  if collides and return how much distance is left from the block or mapEnd
     */
    protected int bottomCollision() {
        //create rectangle with offset of velocity
        Rectangle entityHitBox  = this.rectangleOffset();
        final int worldEndX = this.getWorld().getWidth() * Block.SIZE;
        //check if the player collide with the end of the map
        if (entityHitBox.getMaxX() >= worldEndX) {
            this.getVel().setX(worldEndX - (this.getPos().getX() + this.getWidth()));
        }
        entityHitBox  = this.rectangleOffset();
        final int worldEndY = this.getWorld().getHeight() * Block.SIZE;
        //check if the player collide with the end of the map
        if (entityHitBox.getMaxY() >= worldEndY) {
            return -2;
        }
        final int startX = (int) (entityHitBox.getX() / Block.SIZE);
        final int endX = (int) ((entityHitBox.getMaxX() - 1) / Block.SIZE);
        final int y = (int) ((entityHitBox.getMaxY() - 1) / Block.SIZE);
        for (int i = startX; i <= endX; i++) {
            Block block = this.getWorld().getBlock(i, y);
            //check if they collide
            if (block.isSolid() && entityHitBox.intersects(block.getBounds())) {
                return (int) (block.getPos().getY() - this.getBounds().getMaxY());
            } else if (block.getType() != BlockType.AIR && !block.isSolid()) {
                ShapelessBlock shapeless = (ShapelessBlock) block;
                shapeless.onCollision(this);
            }
        }
        return -1;
    }
}
