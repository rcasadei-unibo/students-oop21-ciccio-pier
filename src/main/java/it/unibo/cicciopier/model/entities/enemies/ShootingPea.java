package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.ShootingPeaView;

public class ShootingPea extends SimpleEnemy {

    public static final int MAX_RIGHT_OFFSET = 32*3;
    public static final int IDLE_DURATION = 200;
    public static final int ATTACK_RANGE = 32*7;
    private final GameObjectView view;
    //Can't be final due to later initialization of the pos
    private int leftPathfurthest;
    private int rightPathfurthest;
    private int currentDest;
    private boolean specular;
    private int idleTicks;
    private boolean pathInitialized;
    private ShootingPeaStatuses status;


    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public ShootingPea(final World world) {
        super(EntityType.SHOOTING_PEA, world);
        this.status = ShootingPeaStatuses.IDLE;
        this.view = new ShootingPeaView(this);
        this.specular = true;
        this.idleTicks = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        //TODO : per contatto, fai danno con metodo ereditato. Spawn proiettile, individua inoltre un modo per aggrare?
        if (!this.pathInitialized){
            this.leftPathfurthest = this.getPos().getX();
            this.rightPathfurthest = this.leftPathfurthest + this.MAX_RIGHT_OFFSET;
            this.currentDest = this.leftPathfurthest;
            this.pathInitialized = true;
        }

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

        if (this.getPos().getX() == this.currentDest && this.idleTicks < this.IDLE_DURATION){
            this.status = ShootingPeaStatuses.IDLE;
            this.setVel(new Vector2d(0,0));
            this.idleTicks++;
        } else if (this.getPos().getX() == this.currentDest){
            this.currentDest = this.currentDest == leftPathfurthest ? rightPathfurthest : leftPathfurthest;
            this.idleTicks = 0;
            this.status = ShootingPeaStatuses.WALKING;
            this.specular = this.specular ? false : true;
        } else if (this.status == ShootingPeaStatuses.WALKING) {
            this.setVel(new Vector2d(this.currentDest == leftPathfurthest? -0.4 : 0.4,0));
        }
        this.move();
    }

    /**
     * Does nothing, this entity does not jump
     */
    @Override
    public void jump() {

    }

    /**
     * Method used to retrieve the entity's status
     *
     * @return The status
     */
    public ShootingPeaStatuses getStatus() {
        return this.status;
    }

    public boolean isSpecular(){
        return this.specular;
    }
}
