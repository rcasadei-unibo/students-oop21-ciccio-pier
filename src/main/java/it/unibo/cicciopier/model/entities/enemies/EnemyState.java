package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.entities.EntityState;

/**
 * Extension of basic entity states, adding some states used by Enemies
 */
public class EnemyState extends EntityState {

    /**
     * Represents the Enemy running while being angry
     */
    public static final EnemyState ANGERED_RUNNING = new EnemyState("aggro_running");
    /**
     * Represents and Enemy getting angry
     */
    public static final EnemyState ANGERED = new EnemyState("angered");
    /**
     * Represents the NinjaPotato being hidden
     */
    public static final EnemyState HIDDEN = new EnemyState("hidden");
    /**
     * Represents the NinjaPotato getting hidden in the ground
     */
    public static final EnemyState JUMPING_IN = new EnemyState("jumping_in");
    /**
     * Represents the NinjaPotato getting out from the ground
     */
    public static final EnemyState JUMPING_OUT = new EnemyState("jumping_out");
    /**
     * Represents the NinjaPotato putting the sword away after attacking
     */
    public static final EnemyState SLASH_IN = new EnemyState("slash_in");
    /**
     * Represents the NinjaPotato drawing the sword out to attack
     */
    public static final EnemyState SLASH_OUT = new EnemyState("slash_out");

    /**
     * Constructor for EnemyState
     *
     * @param id The state id
     */
    public EnemyState(final String id) {
        super(id);
    }
}
