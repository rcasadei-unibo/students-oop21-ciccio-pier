package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.NinjaPotatoView;

/**
 * Represents the enemy NinjaPotato, a static hidden potato which attacks the player with a fast slash
 * when it gets too close.
 */
public class NinjaPotato extends SimpleEnemy {
    private static final int SCORE_VALUE = 50;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;
    private static final int ATTACK_RANGE = 6 * Block.SIZE;
    private static final int IDLE_DURATION = 4 * GameLoop.TPS;

    private static final int ATTACK_COOLDOWN = 3 * GameLoop.TPS;
    private static final double ATTACK_SPEED = 15d * Block.SIZE / GameLoop.TPS;
    public static final int PROJECTILE_DURATION_TICKS = 30;
    private static final int LOCAL_TICK_COUNT_DELIMITER = 3000;
    public static final int SLASH_OUT_TICK_DURATION = 20;
    public static final int SLASH_IN_TICK_DURATION = 60;
    public static final int JUMP_TICKS = 30;

    private final NinjaPotatoView view;
    private int localTicks;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public NinjaPotato(final World world) {
        super(EntityType.NINJA_POTATO, world);
        this.resetCurrentState(EnemyState.HIDDEN);
        this.view = new NinjaPotatoView(this);
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
    public int getScoreValue() {
        return SCORE_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealValue() {
        return HEALTH_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStaminaValue() {
        return STAMINA_VALUE;
    }





    /**
     * {@inheritDoc}
     */
    @Override
    public void damage(int amount) {
        if (this.getCurrentState() != EnemyState.HIDDEN) {
            super.damage(amount);
        }
    }

    /**
     * Utility method to determine the NinjaPotato facing direction
     */
    private void checkSpecular() {
        if (this.getWorld().getPlayer().getPos().getX() < this.getPos().getX()) {
            this.setFacingRight(false);
        } else {
            this.setFacingRight(true);
        }
    }

    /**
     * Utility method used to update the local ticks used by all the
     * NinjaPotato actions. The local ticks increase every tick upon reaching
     * a delimiter, so to avoid eventual Overflows
     */
    private void updateLocalTicks() {
        if (this.localTicks < LOCAL_TICK_COUNT_DELIMITER) {
            this.localTicks++;
        }
    }

    /**
     * Method that defines the NinjaPotato attack behaviour.
     * It is called every tick, unless the Entity has died
     */
    private void attackBehaviour() {
        if (EnemyState.HIDDEN.equals(this.getCurrentState())) {
            if (this.startAggro(ATTACK_RANGE)) {
                this.checkSpecular();
                this.resetCurrentState(EnemyState.JUMPING_OUT);
                this.localTicks = 0;
            }
        } else if (EnemyState.JUMPING_OUT.equals(this.getCurrentState())) {
            if (this.localTicks == JUMP_TICKS) {
                this.localTicks = 0;
                this.checkSpecular();
                if (this.playerInAggroRange(ATTACK_RANGE)) {
                    this.resetCurrentState(EnemyState.SLASH_OUT);
                } else {
                    this.resetCurrentState(EnemyState.IDLE);
                }
            }
        } else if (EnemyState.IDLE.equals(this.getCurrentState())) {
            if (this.startAggro(ATTACK_RANGE)) {
                if (this.getShootingCooldownTicks() == 0) {
                    this.checkSpecular();
                    this.resetCurrentState(EnemyState.SLASH_OUT);
                    this.localTicks = 0;
                }
            } else if (this.localTicks == IDLE_DURATION) {
                this.resetCurrentState(EnemyState.JUMPING_IN);
                this.localTicks = 0;
            }
        } else if (EnemyState.SLASH_OUT.equals(this.getCurrentState())) {
            if (this.localTicks == SLASH_OUT_TICK_DURATION) {
                this.localTicks = 0;
                this.checkSpecular();
                this.shoot(ATTACK_SPEED, EntityType.SLASH);
                this.resetCurrentState(EnemyState.SLASH_IN);
            }
        } else if (EnemyState.SLASH_IN.equals(this.getCurrentState())) {
            if (this.localTicks == SLASH_IN_TICK_DURATION) {
                this.localTicks = 0;
                this.setShootingCooldownTicks(ATTACK_COOLDOWN);
                this.resetCurrentState(EnemyState.IDLE);
            }
        } else if (EnemyState.JUMPING_IN.equals(this.getCurrentState())) {
            if (this.localTicks == JUMP_TICKS) {
                this.resetCurrentState(EnemyState.HIDDEN);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
        if (this.isDead()) {
            return;
        }
        this.updateLocalTicks();
        this.attackBehaviour();
    }
}
