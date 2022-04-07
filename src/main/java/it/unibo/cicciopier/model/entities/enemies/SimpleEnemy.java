package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;

//Personal note : try to extend this to pathEnemies or something similar, for similar enemies patrolling a path;
//maybe making a subclass abstract, where offsets for path are final, chosen at constructor time
//multiplied by some constants so to have a bit more precise control if needed

/**
 * Represents a generic enemy with his basic features such as status and if specular (relatively to the view)
 * with their respective getter and setters.
 */
public abstract class SimpleEnemy extends SimpleLivingEntity implements Enemy {
    private final int attackDamage;
    private EnemyStatuses status;
    private boolean specular;

    /**
     * Constructor for this class
     *
     * @param type  The Entity's type
     * @param world The game's world
     */
    protected SimpleEnemy(final EntityType type, final World world) {
        super(type, world);
        this.attackDamage = this.getType().getAttackDamage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attackPlayer() {
        this.getWorld().getPlayer().damage(this.attackDamage);
    }


    protected boolean startAggro(final int range) {
        int pivot = this.getPos().getX();
        int playerPos = this.getWorld().getPlayer().getPos().getX();
        return ((playerPos >= pivot - range) && (playerPos <= pivot + range) && this.getPos().getY() == getWorld().getPlayer().getPos().getY());
    }

    protected boolean playerInAggroRange(final int range){
        int pivot = this.getPos().getX();
        int playerPos = this.getWorld().getPlayer().getPos().getX();
        return (playerPos >= pivot - range) && (playerPos <= pivot + range);
    }

    protected boolean facingPlayer() {
        return (this.getWorld().getPlayer().getPos().getX() < this.getPos().getX() && this.getSpecular()) ||
                (this.getWorld().getPlayer().getPos().getX() > this.getPos().getX() && !this.getSpecular());
    }

    /**
     * Returns the entity's status
     *
     * @return the status
     */
    public EnemyStatuses getStatus() {
        return this.status;
    }

    /**
     * Returns true if the entity is facing the same direction as the representing texture
     *
     * @return true, if facing the same direction
     */
    public boolean getSpecular() {
        return this.specular;
    }

    /**
     * Set the entity's status
     *
     * @param status
     */
    public void setStatus(final EnemyStatuses status) {
        this.status = status;
    }

    /**
     * Set the entity either specular or not
     *
     * @param bool
     */
    public void setSpecular(final boolean bool) {
        this.specular = bool;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
