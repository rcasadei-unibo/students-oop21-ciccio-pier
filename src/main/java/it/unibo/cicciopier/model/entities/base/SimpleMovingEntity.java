package it.unibo.cicciopier.model.entities.base;

import it.unibo.cicciopier.model.GameObject;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.utility.Vector2d;

import java.awt.*;

public abstract class SimpleMovingEntity extends SimpleEntity implements MovingEntity {
    private Vector2d vel;
    private final int blocksInWidth;
    private final int blocksInHeight;

    /**
     * Constructor for this class
     *
     * @param type  The Entity's type
     * @param world The game's world
     */
    protected SimpleMovingEntity(final EntityType type, final World world) {
        super(type, world);
        this.vel = new Vector2d(0, 0);
        //how many blocks to check under the player and up of the player
        //plus one because what if the entity is in the middle
        this.blocksInWidth = (int) Math.ceil(this.getType().getWidth() / (double) Block.SIZE) + 1;
        //how many bocks to check on the side of the player
        this.blocksInHeight = (int) Math.ceil(this.getType().getHeight() / (double) Block.SIZE);
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
    private Rectangle rectangleOffset() {
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
     * Check collision on the right hand side of the entity with blocks
     *
     * @return true if collides else false
     */
    public boolean rightCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();
        //get the position of the entity in block position
        final int posXInMatrix = (int) (Math.floor(this.rectangleOffset().getX() + this.getWidth()) / Block.SIZE);
        final int posYInMatrix = (int) (Math.floor(this.rectangleOffset().getY()) / Block.SIZE);

        //check all the block on the right hand side of the entity
        for (int i = 0; i < this.blocksInHeight; i++) {
            Block block = this.getWorld().getBlock(posXInMatrix, posYInMatrix + i);

            if (block.getType() == BlockType.AIR) {
                //PlayerImpl.LOGGER.info("entro qua dentro");
                continue;
            }
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check collision on the left hand side of the entity with blocks
     *
     * @return true if collides else false
     */
    public boolean leftCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();
        //get the position of the entity in block position
        final int posXInMatrix = (int) (Math.floor(this.rectangleOffset().getX()) / Block.SIZE);
        final int posYInMatrix = (int) (Math.floor(this.rectangleOffset().getY()) / Block.SIZE);

        //check all the block on the right hand side of the entity
        for (int i = 0; i < this.blocksInHeight; i++) {
            final Block block = this.getWorld().getBlock(posXInMatrix, posYInMatrix + i);

            if (block.getType() == BlockType.AIR) {
                continue;
            }
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check collision on the bottom of the entity with blocks
     *
     * @return true if collides else false
     */
    public boolean bottomCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();
        //get the position of the entity in block position
        final int posXInMatrix = (int) (Math.floor(this.rectangleOffset().getX()) / Block.SIZE);
        final int posYInMatrix = (int) (Math.floor(this.rectangleOffset().getY() + this.getHeight()) / Block.SIZE);

        //check all the block on the right hand side of the entity
        for (int i = 0; i < this.blocksInWidth; i++) {
            final Block block = this.getWorld().getBlock(posXInMatrix + i, posYInMatrix);

            if (block.getType() == BlockType.AIR) {
                continue;
            }
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check collision up of entity with blocks
     *
     * @return true if collides else false
     */
    public boolean upCollision() {
        //create rectangle with offset of velocity
        final Rectangle entityHitBox = this.rectangleOffset();
        //get the position of the entity in block position
        final int posXInMatrix = (int) (Math.floor(this.rectangleOffset().getX()) / Block.SIZE);
        final int posYInMatrix = (int) (Math.floor(this.rectangleOffset().getY()) / Block.SIZE);

        //check all the block on the right hand side of the entity
        for (int i = 0; i < this.blocksInWidth; i++) {
            final Block block = this.getWorld().getBlock(posXInMatrix + i, posYInMatrix);

            if (block.getType() == BlockType.AIR) {
                continue;
            }
            //check if they collide
            if (entityHitBox.intersects(block.getBounds())) {
                return true;
            }
        }
        return false;
    }
}
