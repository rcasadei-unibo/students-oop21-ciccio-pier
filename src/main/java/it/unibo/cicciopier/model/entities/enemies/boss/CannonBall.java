package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;

/**
 * Create a simple cannon ball that will be shot by the Boss
 */
public class CannonBall extends SimpleMovingEntity {
    private static final Vector2d GRAVITY = new Vector2d(0, 0.05f);
    private static final double MAX_SPEED = 5d;

    /**
     * Constructor for this class, create a cannon ball instance
     *
     * @param world The game's world
     */
    public CannonBall(final World world) {
        super(EntityType.CANNON_BALL, world);
        this.findPlayerAndSetVel();
    }

    /**
     * Find the player and set the velocity of the Cannon Ball
     */
    private void findPlayerAndSetVel() {
        //TO SEE IF IT WORKS OR NOT
        // playerPos.x = player.x, playerPos.y = player.y + player.height
        final Vector2d playerPos = new Vector2d(this.getWorld().getPlayer().getPos().getX(),
                this.getWorld().getPlayer().getPos().getY() + this.getWorld().getPlayer().getType().getHeight());
        // vel.x = p.x - this.pos.x, vel.y = p.y - this.pos.y
        this.setVel(new Vector2d(playerPos.getX() - this.getPos().getX(),
                playerPos.getY() - this.getPos().getY()));
        //Set the maximum speed it can travel each tick
        this.getVel().setMagnitude(CannonBall.MAX_SPEED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        this.getPos().add(this.getVel());
        this.getPos().add(CannonBall.GRAVITY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return null;
    }
}
