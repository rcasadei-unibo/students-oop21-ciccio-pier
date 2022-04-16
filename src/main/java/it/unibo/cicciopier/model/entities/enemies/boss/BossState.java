package it.unibo.cicciopier.model.entities.enemies.boss;

import it.unibo.cicciopier.model.entities.EntityState;

/**
 * Simple class to store the instance of boss states
 */
public class BossState extends EntityState {
    public static final BossState SEEK = new BossState("seek");
    public static final BossState MISSILE_LAUNCHER = new BossState("missile_launcher");
    public static final BossState LASER = new BossState("laser");
    public static final BossState METEOR_SHOWER = new BossState("meteor_shower");

    /**
     * {@link EntityState#EntityState}
     */
    public BossState(final String id) {
        super(id);
    }

}
