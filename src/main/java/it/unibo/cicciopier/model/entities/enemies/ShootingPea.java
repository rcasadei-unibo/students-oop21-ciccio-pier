package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

public class ShootingPea extends SimpleEnemy {

    public static final int MAX_RIGHT_OFFSET = 32 * 3;
    public static final int IDLE_DURATION = 200;
    public static final int ATTACK_RANGE = 32 * 7;
    private final EnemyView view;
    private int deathTicks;
    private final int deathDurationTicks;
    //Can't be final due to later initialization of the pos
    private int leftPathfurthest;
    private int rightPathfurthest;
    private int currentDest;
    private int idleTicks;
    private boolean pathInitialized;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public ShootingPea(final World world) {
        super(EntityType.SHOOTING_PEA, world);
        this.setStatus(EnemyStatuses.SHOOTING_PEA_IDLE);
        this.setSpecular(true);
        this.idleTicks = 0;
        this.deathTicks = 0;
        this.deathDurationTicks = EnemyStatuses.SHOOTING_PEA_DYING.getDuration() * 100;
        this.view = new EnemyView(this);
    }

    @Override
    public GameObjectView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        //TODO
        //RN ONLY USED FOR TESTING
        if (!this.pathInitialized) {
            this.leftPathfurthest = this.getPos().getX();
            this.rightPathfurthest = this.leftPathfurthest + MAX_RIGHT_OFFSET;
            this.currentDest = this.leftPathfurthest;
            this.pathInitialized = true;
        }

        /*
        if (this.checkPlayerInRange(ATTACK_RANGE) &&
                ((this.getWorld().getPlayer().getPos().getX() < this.getPos().getX() && this.isSpecular()) ||
                        (this.getWorld().getPlayer().getPos().getX() > this.getPos().getX() && !this.isSpecular()))){
            this.status = ShootingPeaStatuses.SHOOTING;
            this.setVel(new Vector2d(0,0));
            if (this.getWorld().getPlayer().checkCollision(this)){
                this.attackPlayer();
            }
        } else {
            this.status = ShootingPeaStatuses.WALKING;
        }
        */

        //testing
        if (this.getBounds().intersects(this.getWorld().getPlayer().getBounds())) {
            this.die();
            this.setStatus(EnemyStatuses.SHOOTING_PEA_DYING);
        }
        if (this.getStatus() == EnemyStatuses.SHOOTING_PEA_DYING) {
            this.deathTicks++;
            if (this.deathTicks == this.deathDurationTicks) {
                this.remove();
            }
            return;
        }

        if (this.getPos().getX() == this.currentDest && this.idleTicks < IDLE_DURATION) {
            this.setStatus(EnemyStatuses.SHOOTING_PEA_IDLE);
            this.setVel(new Vector2d(0, 0));
            this.idleTicks++;
        } else if (this.getPos().getX() == this.currentDest) {
            this.currentDest = this.currentDest == leftPathfurthest ? rightPathfurthest : leftPathfurthest;
            this.idleTicks = 0;
            this.setStatus(EnemyStatuses.SHOOTING_PEA_WALKING);
            this.setSpecular(!this.getSpecular());
        } else if (this.getStatus() == EnemyStatuses.SHOOTING_PEA_WALKING) {
            this.setVel(new Vector2d(this.currentDest == leftPathfurthest ? -0.4 : 0.4, 0));
        }
        this.move();
    }

    /**
     * Does nothing, this entity does not jump
     */
    @Override
    public void jump() {

    }


}
