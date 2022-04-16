package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.boss.MeteorView;

/**
 * Simple class to create a meteor for the boss attack
 */
public class Meteor extends SimpleMovingEntity {
    private static final int MAX_SPEED = 15;
    private final MeteorView meteorView;
    private final Vector2d accel;

    /**
     * Constructor for this class, create an instance of a meteor
     *
     * @param world The game's world
     */
    public Meteor(final World world) {
        super(EntityType.METEOR, world);
        this.meteorView = new MeteorView(this);
        this.accel = new Vector2d(0, 0.2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.meteorView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        this.getVel().add(this.accel);
        this.getVel().setLimiter(MAX_SPEED);
        this.getPos().add(this.getVel());
        //start checking for collision when the meteor is shown in the map
        if (this.getPos().getY() > 64) {
            final boolean playerCollision = this.checkCollision(this.getWorld().getPlayer());
            if (playerCollision) {
                this.getWorld().getPlayer().damage(this.getType().getAttackDamage());
            }
            //remove if its collide down with something
            if (this.bottomCollision() != -1 || playerCollision) {
                this.remove();
            }
        }
    }
}

