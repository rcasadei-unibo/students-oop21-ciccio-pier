package it.unibo.cicciopier.model.entities;

import java.util.Objects;

/**
 * Simple class to store the instance of entities states
 */
public class EntityState {
    public static final EntityState IDLE = new EntityState("idle");
    public static final EntityState RUNNING = new EntityState("running");
    public static final EntityState JUMPING = new EntityState("jumping");
    public static final EntityState ATTACKING = new EntityState("attacking");
    public static final EntityState DEAD = new EntityState("dead");

    private final String id;

    /**
     * Constructor for this class, create a state instance
     *
     * @param id name to differentiate an id from another
     */
    public EntityState(final String id) {
        this.id = id;
    }

    /**
     * Get the state id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityState that = (EntityState) o;
        return this.getId().equals(that.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.id;
    }
}
