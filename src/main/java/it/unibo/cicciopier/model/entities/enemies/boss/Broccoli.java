package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.model.entities.enemies.BossState;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.BroccoliView;

import java.util.Optional;

/**
 * Simple class to spawn the boss
 */
public class Broccoli extends SimpleLivingEntity implements Boss {
    private static final int MAX_RANGE = 40;
    private static final int MAX_SPEED = 5;
    private static final int NUM_OF_ATTACKS = 3;
    private static final int MAX_NUM_METEORS = 4;
    private static final int IDLE_DURATION = GameLoop.TPS;
    private static final int MISSILE_DURATION = 4 * GameLoop.TPS;
    private static final int METEOR_DURATION = 5 * GameLoop.TPS;
    private static final int LASER_DURATION = 5 * GameLoop.TPS;
    private static final int SEEK_WAITING = 3 * GameLoop.TPS;

    private final BroccoliView broccoliView;
    private long start;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Broccoli(final World world) {
        super(EntityType.BROCCOLI, world);
        this.start = -1;
        this.broccoliView = new BroccoliView(this);
    }

    /**
     * Reset the timer for the next state, and change the current state to seek state
     */
    private void resetTimerAndSeek(final long ticks, final int waitTime) {
        if (ticks - this.start >= waitTime) {
            this.start = ticks;
            this.resetCurrentState(BossState.SEEK);
        }
    }

    /**
     * Boss idle state, do nothing
     */
    private void idle(final long ticks) {
        this.getVel().setX(0);
        this.resetTimerAndSeek(ticks, IDLE_DURATION);
    }

    /**
     * Boss Seek state, continue to chase until player is in range
     */
    private void seek(final long ticks) {
        //wait before seeking the player
        if (ticks - this.start <= SEEK_WAITING) {
            return;
        }
        Vector2d desire = getPos().directionVector(getWorld().getPlayer().getPos());
        desire.setY(0);
        double distance = 0;
        Vector2d BossPos;
        Vector2d playerPos;

        if (desire.getX() < 0) {    //player is on the left of the boss
            BossPos = new Vector2d(getPos().getDoubleX(), 0);
            playerPos = new Vector2d(getWorld().getPlayer().getBounds().getMaxX(), 0);
            distance = BossPos.euclidDistance(playerPos);
        } else if (desire.getX() > 0) { //player is on the right of the boss
            BossPos = new Vector2d(getBounds().getMaxX(), 0);
            playerPos = new Vector2d(getWorld().getPlayer().getPos().getDoubleX(), 0);
            distance = BossPos.euclidDistance(playerPos);
        } else if (desire.getX() == 0) {    //player is on top of the boss
            this.setCurrentState(BossState.MISSILE_LAUNCHER);
            return;
        }
        //if the distance between the player and the boss is in range then attack the player
        if (distance <= MAX_RANGE) {
            this.resetCurrentState(BossState.IDLE);
            this.start = ticks;
            //choose a random attack
            final int randNum = (int) (Math.random() * NUM_OF_ATTACKS);
            switch (randNum) {
                case 0:
                    this.setCurrentState(BossState.METEOR_SHOWER);
                    break;
                case 1:
                    this.setCurrentState(BossState.LASER);
                    break;
                default:
                    this.setCurrentState(BossState.MISSILE_LAUNCHER);
            }
            return;
        }
        //max speed that the boss can move
        desire.setMagnitude(Broccoli.MAX_SPEED);
        this.getVel().setX(desire.getX());
    }

    /**
     * Boss missile attack state, launch some missile to the player
     */
    private void missileLauncher(final long ticks) {
        this.getVel().setX(0);
        //every second launch a missile
        if ((ticks - this.start) % GameLoop.TPS == 0) {
            Optional<Entity> opt = getWorld().getEntityFactory().createEntity(EntityType.MISSILE);
            if (opt.isPresent()) {
                Entity e = opt.get();
                e.setPos(getPos().clone());
                getWorld().addEntity(e);
            }
        }
        //max wait time for missile launch state
        this.resetTimerAndSeek(ticks, MISSILE_DURATION);
    }

    /**
     * Boss meteor shower state, make meteor fall from the sky
     */
    private void meteorShower(final long ticks) {
        getVel().setX(0);
        if ((ticks - this.start) % 50 == 0) {
            for (int i = 0; i < MAX_NUM_METEORS; i++) {
                //meteor starting x position
                int meteorX = getWorld().getPlayer().getPos().getX();
                //find a random offset
                final int offSet = (int) (Math.random() * 500);

                //flip a coin, add the offset to the meteor x position
                meteorX += Math.random() >= 0.5 ? offSet : -offSet;
                //check if the X position is out of the bounds of the map
                if (meteorX < 0) {
                    meteorX = 0;
                }
                //create a meteor
                Optional<Entity> opt = getWorld().getEntityFactory().createEntity(EntityType.METEOR);

                if (opt.isPresent()) {
                    Entity e = opt.get();
                    //check if the X position is out of the bounds of the map
                    if (meteorX + e.getWidth() >= Block.SIZE * getWorld().getWidth()) {
                        meteorX = Block.SIZE * (getWorld().getWidth() - this.getWidth() / Block.SIZE);
                    }
                    //random y offset
                    e.setPos(new Vector2d(meteorX, -Math.random() * 150 + 40));
                    getWorld().addEntity(e);
                }
            }
        }
        this.resetTimerAndSeek(ticks, METEOR_DURATION);
    }

    /**
     * Boss laser attack state, launch a laser from his eyes
     */
    private void laserAttack(final long ticks) {
        getVel().setX(0);
        //create only one time
        if (ticks - this.start == 1) {
            final int startingOffset = 5;
            //create a laser
            Optional<Entity> opt = getWorld().getEntityFactory().createEntity(EntityType.LASER);
            if (opt.isPresent()) {
                final Laser e = (Laser) opt.get();
                e.setStartLine(this.getPos().clone().addVector(new Vector2d(50, 170)));
                if (this.isFacingRight()) {
                    e.getEndLine().set(this.getPos().getX() + this.getWidth() + startingOffset, this.getBounds().getMaxY());
                } else {
                    e.getEndLine().set(this.getPos().getX() - startingOffset, this.getBounds().getMaxY());
                }
                getWorld().addEntity(e);
            }
        }
        this.resetTimerAndSeek(ticks, LASER_DURATION);
    }

    /**
     * Boss death state
     */
    private void death() {
        getVel().setX(0);
        this.getWorld().getEntities().stream().filter(Laser.class::isInstance).forEach(Entity::remove);
        this.remove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.broccoliView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
        if (this.start == -1) {
            this.start = ticks;
        }
        if (BossState.SEEK == this.getCurrentState()) {
            this.seek(ticks);
        } else if (BossState.LASER == this.getCurrentState()) {
            this.laserAttack(ticks);
        } else if (BossState.METEOR_SHOWER == this.getCurrentState()) {
            this.meteorShower(ticks);
        } else if (BossState.MISSILE_LAUNCHER == this.getCurrentState()) {
            this.missileLauncher(ticks);
        } else if (EntityState.DEAD == this.getCurrentState()) {
            this.death();
        } else {
            this.idle(ticks);
        }
        this.move();
    }
}
