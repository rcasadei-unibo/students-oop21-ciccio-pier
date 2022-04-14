package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;

import java.util.Optional;

/**
 * Abstract class for Projectiles
 */
public abstract class SimpleProjectile extends SimpleMovingEntity {
    private final int durationTicks;
    private int dir;
    private double projectileSpeed;
    private int localTicks;

    /**
     * Constructor for this class
     *
     * @param type          The type of this projectile
     * @param world         The game's world
     * @param durationTicks The duration ticks of this projectile
     */
    protected SimpleProjectile(final EntityType type, final World world, final int durationTicks) {
        super(type, world);
        this.durationTicks = durationTicks;
        this.localTicks = 0;
    }

    /**
     * Method used to set the direction and the speed of the projectile
     *
     * @param dir             The direction of the projectile
     * @param projectileSpeed The speed of the projectile
     */
    public void setDirAndSpeed(final int dir, final double projectileSpeed) {
        this.dir = dir;
        this.projectileSpeed = projectileSpeed;
        this.createView();
    }

    /**
     * Method used to create the view of the Projectile.
     * Left empty, is to be overridden in individual Projectile's classes
     */
    protected void createView() {

    }

    /**
     * Retrieve the direction of the Projectile
     *
     * @return The Projectile's direction
     */
    protected int getDir() {
        return this.dir;
    }

    /**
     * Method to check if projectile hit something, eventually destroying it
     */
    private void checkCollisionsHit() {
        if (this.upCollision() != 1 || this.leftCollision() != 1 ||
                this.bottomCollision() != -1 || this.rightCollision() != -1) {
            this.createExplosion();
            this.remove();
        }
    }

    /**
     * Method to check if projectile hit the Player, eventually damaging it
     */
    private void checkPlayerHit() {
        if (this.checkCollision(this.getWorld().getPlayer())) {
            this.getWorld().getPlayer().damage(this.getType().getAttackDamage());
            this.createExplosion();
            this.remove();
        }
    }

    /**
     * Utility method to generate an explosion, needed when the projectile gets destroyed
     */
    protected void createExplosion() {
        Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(EntityType.EXPLOSION);
        if (opt.isPresent()) {
            Entity e = opt.get();
            e.setPos(this.getPos());
            this.getWorld().addEntity(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        this.localTicks++;
        if (this.localTicks > this.durationTicks) {
            this.remove();
        }
        this.setPos(new Vector2d(this.getPos().getX() + this.dir * this.projectileSpeed, this.getPos().getY()));
        this.checkPlayerHit();
        this.checkCollisionsHit();
    }

}
