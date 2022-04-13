package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;

public class BossState extends EntityState {
    public static final EntityState SEEK = new BossState("seek");
    public static final BossState MISSILE_LAUNCHER = new BossState("missile_launcher");
    public static final BossState LASER = new BossState("laser");
    public static final BossState METEOR_SHOWER = new BossState("meteor_shower");

    public BossState(final String id) {
        super(id);
    }

}
