package it.unibo.cicciopier.model.entities;

import java.util.Objects;

public class EntityState {
    public static final EntityState IDLE = new EntityState(0);
    public static final EntityState RUNNING = new EntityState(1);
    public static final EntityState JUMPING = new EntityState(2);
    public static final EntityState ATTACKING = new EntityState(3);
    public static final EntityState DAMAGED = new EntityState(4);
    public static final EntityState DEAD = new EntityState(5);

    private final int id;

    public EntityState(final int id) {
        this.id = id;
    }

    /**
     * Get the state id
     *
     * @return id
     */
    public int getId() {
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
        return this.getId() == that.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "State " + id;
    }
}
