package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

/**
 * Represents the enemy NinjaPotato, a static hidden potato which attacks the player with a fast slash
 * when it gets too close.
 */
public class NinjaPotato extends SimpleEnemy {
    private static final int SCORE_VALUE = 50;
    private static final int ATTACK_RANGE = 6 * Block.SIZE;
    private static final int LOCAL_TICK_COUNT_DELIMITER = 3000;
    private static final int IDLE_DURATION = 4 * GameLoop.TPS;
    private static final int ATTACK_COOLDOWN = 3 * GameLoop.TPS;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;

    private int localTicks;
    private final EnemyView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public NinjaPotato(final World world) {
        super(EntityType.NINJA_POTATO, world);
        this.setStatus(EnemyStatuses.NINJA_POTATO_HIDDEN);
        this.view = new EnemyView(this, Texture.NINJA_POTATO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTextureSpecular() {
        return false;
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
    public EnemyStatuses getDyingStatus() {
        return EnemyStatuses.NINJA_POTATO_DYING;
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
    public void damage(int amount) {
        if (this.getStatus() != EnemyStatuses.NINJA_POTATO_HIDDEN) {
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
        switch (this.getStatus()) {
            case NINJA_POTATO_HIDDEN:
                if (this.startAggro(ATTACK_RANGE)) {
                    this.checkSpecular();
                    this.setStatus(EnemyStatuses.NINJA_POTATO_JUMPING_OUT);
                    this.localTicks = 0;
                }
                return;
            case NINJA_POTATO_JUMPING_OUT:
                if (this.localTicks >= EnemyStatuses.NINJA_POTATO_JUMPING_OUT.getDurationTicks()) {
                    this.localTicks = 0;
                    this.checkSpecular();
                    if (this.playerInAggroRange(ATTACK_RANGE)) {
                        this.setStatus(EnemyStatuses.NINJA_POTATO_SWING_1);
                    } else {
                        this.setStatus(EnemyStatuses.NINJA_POTATO_IDLE);
                    }
                }
                return;
            case NINJA_POTATO_IDLE:
                if (this.playerInAggroRange(ATTACK_RANGE)) {
                    if (this.getShootingCooldownTicks() == 0) {
                        this.checkSpecular();
                        this.setStatus(EnemyStatuses.NINJA_POTATO_SWING_1);
                        this.localTicks = 0;
                    }
                } else if (this.localTicks == IDLE_DURATION) {
                    this.setStatus(EnemyStatuses.NINJA_POTATO_JUMPING_IN);
                    this.localTicks = 0;
                }
                return;
            case NINJA_POTATO_SWING_1:
                if (this.localTicks == EnemyStatuses.NINJA_POTATO_SWING_1.getDurationTicks()) {
                    this.localTicks = 0;
                    this.checkSpecular();
                    this.shoot(this.isFacingRight() ? 1 : -1, EntityType.SLASH);
                    this.setStatus(EnemyStatuses.NINJA_POTATO_SWING_2);
                }
                return;
            case NINJA_POTATO_SWING_2:
                if (this.localTicks == EnemyStatuses.NINJA_POTATO_SWING_2.getDurationTicks()) {
                    this.localTicks = 0;
                    this.setShootingCooldownTicks(ATTACK_COOLDOWN);
                    this.setStatus(EnemyStatuses.NINJA_POTATO_IDLE);
                }
                return;
            case NINJA_POTATO_JUMPING_IN:
                if (this.localTicks == EnemyStatuses.NINJA_POTATO_JUMPING_IN.getDurationTicks()) {
                    this.setStatus(EnemyStatuses.NINJA_POTATO_HIDDEN);
                }
                return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        super.tick();
        if (this.isDead()) {
            return;
        }
        this.updateLocalTicks();
        this.attackBehaviour();
    }
}
