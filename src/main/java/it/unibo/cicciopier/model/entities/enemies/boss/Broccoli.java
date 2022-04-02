package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.BroccoliView;

import java.util.Optional;

/**
 * Simple class to spawn the boss
 */
public class Broccoli extends SimpleLivingEntity {
    static final int MAX_SPEED = 5;
    private final BroccoliView broccoliView;
    private Status status;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Broccoli(World world) {
        super(EntityType.BROCCOLI, world);
        this.status = new Idle();
        this.broccoliView = new BroccoliView(this);
    }

    /**
     * Inner class for Idle
     */
    private class Idle implements Status {
        private int time;

        /**
         * Default Constructor for idle state
         */
        public Idle() {
            this.time = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void tick() {
            this.time++;
            if (this.time >= 300) {
                System.out.println("cambio status in seek");
                status = new Seek();
                return;
            }
            getVel().setX(0);
        }
    }

    /**
     * Inner class for Idle
     */
    private class Seek implements Status {
        private static final int MAX_RANGE = 50;

        /**
         * {@inheritDoc}
         */
        @Override
        public void tick() {
            Vector2d desire = getPos().directionVector(getWorld().getPlayer().getPos());
            desire.setY(0);
            double distance = 0;
            Vector2d BossPos = null;
            Vector2d playerPos = null;
            if (desire.getX() < 0) {
                BossPos = new Vector2d(getPos().getDoubleX(), 0);
                playerPos = new Vector2d(getWorld().getPlayer().getBounds().getMaxX(), 0);
                distance = BossPos.euclidDistance(playerPos);
            } else if (desire.getX() > 0) {
                BossPos = new Vector2d(getBounds().getMaxX(), 0);
                playerPos = new Vector2d(getWorld().getPlayer().getPos().getDoubleX(), 0);
                distance = BossPos.euclidDistance(playerPos);
            } else if (desire.getX() == 0) {
                status = new MissileLauncher();
                return;
            }
            //if the distance between the player and the boss is in range then attack the player
            if (distance <= Seek.MAX_RANGE) {
                status = new MissileLauncher();
                return;
            }
            //max speed that the boss can move
            desire.setMagnitude(Broccoli.MAX_SPEED);
            getVel().setX(desire.getX());
        }
    }

    /**
     * Inner class for Missile launch state
     */
    private class MissileLauncher implements Status {
        private int time;

        /**
         * Default Constructor for missile launcher state
         */
        public MissileLauncher() {
            this.time = 0;
            System.out.println("Missile Launch status ");
        }

        @Override
        public void tick() {
            this.time++;
            getVel().setX(0);
            if (time == 100 || time == 150 || time == 200 || time == 250 || time == 300) {
                Optional<Entity> opt = getWorld().getEntityFactory().createEntity(EntityType.MISSILE);
                if (opt.isPresent()) {
                    Entity e = opt.get();
                    e.setPos(getPos().clone());
                    getWorld().addEntity(e);
                }
            }

            //remove the boss
            if (this.time >= 400) {
                status = new Death();
            }
        }
    }

    /**
     * Inner class for Death state
     */
    private class Death implements Status {
        private int time;

        /**
         * Default Constructor for death state
         */
        public Death() {
            this.time = 0;
            System.out.println("current status is death ");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void tick() {
            this.time++;
            getVel().setX(0);
            if (this.time >= 200) {
                remove();
            }
        }
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
    public void tick() {
        super.tick();
        this.status.tick();
        this.move();
    }
}
