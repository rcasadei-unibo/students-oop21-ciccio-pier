package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.entities.enemies.EnemyView;

/**
 * Represents the enemy CryingOnion, a walking onion whom attack consists into crying
 * whenever the player gets too near and then running into him.
 */
public class CryingOnion extends SimplePathEnemy {
    private final int SCORE_VALUE = 50;
    private final double IDLE_DURATION = 2 * GameLoop.TPS;
    private final double MOVEMENT_SPEED = (0.8 * Block.SIZE)/GameLoop.TPS;
    private final int MAX_RIGHT_OFFSET = 3 * Block.SIZE;
    private final int ATTACK_RANGE = 5 * Block.SIZE;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;


    private final EnemyView view;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public CryingOnion(final World world) {
        super(EntityType.CRYING_ONION, world);
        this.setStatus(EnemyStatuses.CRYING_ONION_WALKING_CRYING);
        this.view = new EnemyView(this, Texture.CRYING_ONION);
    }

    @Override
    public int getHealValue() {
        return HEALTH_VALUE;
    }

    @Override
    public int getScoreValue() {
        return SCORE_VALUE;
    }

    @Override
    public boolean isTextureSpecular() {
        return false;
    }

    @Override
    public int getStaminaValue() {
        return STAMINA_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getIdleDuration() {
        return IDLE_DURATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackRange() {
        return ATTACK_RANGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getIdleStatus() {
        return EnemyStatuses.CRYING_ONION_IDLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getWalkingStatus() {
        return EnemyStatuses.CRYING_ONION_WALKING_NORMAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxRightOffset() {
        return MAX_RIGHT_OFFSET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyStatuses getDyingStatus() {
        return EnemyStatuses.CRYING_ONION_DYING;
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
        super.tick();
    }
}
