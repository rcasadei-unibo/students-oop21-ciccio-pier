package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleMovingEntity;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.ProjectileView;

import java.util.Optional;

public abstract class SimpleProjectile extends SimpleMovingEntity {
    private final ProjectileView view;
    private final Projectiles projectile;
    private final double movementPerTick;
    private int ticks;
    private int dir;

    /**
     * Constructor for this class
     *
     * @param type  The Entity's type
     * @param world The game's world
     */
    protected SimpleProjectile(final EntityType type, final World world, final Projectiles projectile) {
        super(type, world);
        this.projectile = projectile;
        this.ticks = 0;
        this.movementPerTick = this.projectile.getRange() / this.projectile.getDuration();
        this.view = new ProjectileView(this, projectile.getTexture());
    }

    @Override
    public void tick() {
        this.ticks++;
        if (this.ticks == this.projectile.getDuration()) {
            this.remove();
        }
        this.setPos(new Vector2d(this.getPos().getX() + (this.dir * this.movementPerTick), this.getPos().getY()));
        this.checkPlayerHit();
        this.checkCollisionsHit();
    }


    /**
     * int means to left, -int means to right
     *
     * @param dir
     */
    public void setDir(final int dir) {
        if (dir > 0) {
            this.dir = 1;
        } else {
            this.dir = -1;
        }
    }

    @Override
    public GameObjectView getView() {
        return this.view;
    }

    protected void createExplosion() {
        Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(EntityType.EXPLOSION);
        if (opt.isPresent()) {
            Entity e = opt.get();
            e.setPos(this.getPos());
            this.getWorld().addEntity(e);
        }
    }

    private void checkCollisionsHit() {
        if (this.upCollision() != 1 || this.leftCollision() != 1 ||
                this.bottomCollision() != -1 || this.rightCollision() != -1) {
            this.createExplosion();
            this.remove();
        }
    }

    private void checkPlayerHit() {
        if (this.checkCollision(this.getWorld().getPlayer())) {
            this.getWorld().getPlayer().damage(this.getType().getAttackDamage());
            this.createExplosion();
            this.remove();
        }
    }

}
