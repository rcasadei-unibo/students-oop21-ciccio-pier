package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleLivingEntity;

//i should also try to extend this to path enemies idk
//maybe making a subclass abstract, where offsets for path are final, chosen at constructor time
//multiplied by some constants so to have a bit more precise control if needed
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

    /**
     * This method checks, in a given range, if the player is present
     *
     * @param range The maximum, inclusive radius of the range from the current position
     * @return True if player is in range
     */
    protected boolean checkPlayerInRange(final int range) {
        int pivot = this.getPos().getX();
        int playerPos = this.getWorld().getPlayer().getPos().getX();
        return (playerPos >= pivot - range) && (playerPos <= pivot + range);
    }

    public EnemyStatuses getStatus() {
        return this.status;
    }

    public boolean getSpecular() {
        return this.specular;
    }

    public void setStatus(final EnemyStatuses status) {
        this.status = status;
    }

    public void setSpecular(final boolean bool) {
        this.specular = bool;
    }
}
