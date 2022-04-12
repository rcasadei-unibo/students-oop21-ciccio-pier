package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;

public class BossState extends EntityState {
    public static final EntityState SEEK = new BossState(6);
    public static final BossState MISSILE_LAUNCHER = new BossState(7);
    public static final BossState LASER = new BossState(8);
    public static final BossState METEOR_SHOWER = new BossState(9);

    public BossState(int id) {
        super(id);
    }

}
