package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.utility.Vector2d;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

import java.util.Optional;

/**
 * Represents the enemy ShootingPea, a walking pea whom attack consists into shooting
 * peas bursting from the pod's bottom.
 */
public class ShootingPea extends SimpleEnemy {
    private static final int MAX_RIGHT_OFFSET = 32 * 3;
    private static final int IDLE_DURATION = 200;
    private static final int ATTACK_RANGE = 32 * 7;
    private static final int ATTACK_COOLDOWN = 2 * GameLoop.TPS;
    private static final int HIT_COOLDOWN = 2 * GameLoop.TPS;
    private final EnemyView view;
    private boolean aggro;
    private int attackDurationTicks;
    private int shootingTicks;
    private int deathTicks;
    private int hitTicks;
    private boolean shot;
    private final double deathDurationTicks;
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
        this.deathDurationTicks = EnemyStatuses.SHOOTING_PEA_DYING.getDurationTicks();
        this.shootingTicks = ATTACK_COOLDOWN;
        this.attackDurationTicks = 0;
        this.shot = false;
        this.aggro = false;
        this.hitTicks = HIT_COOLDOWN;
        this.view = new EnemyView(this, Texture.SHOOTING_PEA);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

    private void initializePath() {
        if (!this.pathInitialized) {
            this.leftPathfurthest = this.getPos().getX();
            this.rightPathfurthest = this.leftPathfurthest + MAX_RIGHT_OFFSET;
            this.currentDest = this.leftPathfurthest;
            this.pathInitialized = true;
        }
    }

    private void updateAttackTicks() {
        if (this.shootingTicks < ATTACK_COOLDOWN) {
            this.shootingTicks++;
        }
    }

    private void updateHitTicks() {
        if (this.hitTicks < HIT_COOLDOWN) {
            this.hitTicks++;
        }
    }



    private void checkAttackConditions() {
        if (this.startAggro(ATTACK_RANGE) && this.facingPlayer()) {
            this.aggro = true;
        }
        if (!this.facingPlayer() || !this.playerInAggroRange(ATTACK_RANGE)){
            this.aggro = false;
        }
    }

    private void attacking() {
        this.setStatus(EnemyStatuses.SHOOTING_PEA_SHOOTING);
        this.attackDurationTicks++;
        if (this.attackDurationTicks >= EnemyStatuses.SHOOTING_PEA_SHOOTING.getDurationTicks() / 2 && !this.shot) {
            this.shoot();
            this.shot = true;
        }
        if (this.attackDurationTicks >= EnemyStatuses.SHOOTING_PEA_SHOOTING.getDurationTicks()) {
            this.shootingTicks = 0;
            this.attackDurationTicks = 0;
            this.shot = false;
        }
        return;
    }

    private void shoot() {
        Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(EntityType.PEA);
        if (opt.isPresent()) {
            int dir = this.getSpecular() ? -1 : 1;
            Pea e = ((Pea) opt.get());
            e.setPos(this.getPos().addVector(new Vector2d(dir, this.getType().getHeight() / 2)));
            e.setDir(dir);
            this.getWorld().addEntity(e);
        }
    }

    private void checkPlayerCollision() {
        if (this.getWorld().getPlayer().checkCollision(this) && this.hitTicks == HIT_COOLDOWN) {
            this.attackPlayer();
            this.hitTicks = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
        this.initializePath();
        if (this.isDead()) {
            this.setStatus(EnemyStatuses.SHOOTING_PEA_DYING);
        }
        if (this.getStatus() == EnemyStatuses.SHOOTING_PEA_DYING) {
            this.deathTicks++;
            if (this.deathTicks >= this.deathDurationTicks) {
                this.remove();
            }
            return;
        }
        this.checkPlayerCollision();
        this.updateHitTicks();
        this.updateAttackTicks();
        this.checkAttackConditions();
        if (this.aggro) {
            this.getVel().setX(0);
            if (shootingTicks == ATTACK_COOLDOWN) {
                this.attacking();
            } else {
                this.setStatus(EnemyStatuses.SHOOTING_PEA_IDLE);
            }
            return;
        }
        this.setStatus(EnemyStatuses.SHOOTING_PEA_WALKING);
        if (this.getPos().getX() == this.currentDest && this.idleTicks < IDLE_DURATION) {
            this.setStatus(EnemyStatuses.SHOOTING_PEA_IDLE);
            this.getVel().setX(0);
            this.idleTicks++;
        } else if (this.getPos().getX() == this.currentDest) {
            this.currentDest = this.currentDest == leftPathfurthest ? rightPathfurthest : leftPathfurthest;
            this.idleTicks = 0;
            this.setStatus(EnemyStatuses.SHOOTING_PEA_WALKING);
            this.setSpecular(!this.getSpecular());
        } else {
            this.getVel().setX(this.currentDest == leftPathfurthest ? -0.4 : 0.4);
        }
        this.move();
    }
}
