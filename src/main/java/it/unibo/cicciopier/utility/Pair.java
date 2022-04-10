package it.unibo.cicciopier.utility;

import java.util.Objects;

/**
 * Utility class to represent pairs of two instances of the same type.
 *
 * @param <T> the type
 */
public class Pair<T> {
    private T x;
    private T y;

    /**
     * Creates a new pair given X and Y values.
     *
     * @param x the x value
     * @param y the y value
     */
    public Pair(final T x, final T y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X for this pair.
     *
     * @return the X
     */
    public T getX() {
        return x;
    }

    /**
     * Gets the Y for this pair.
     *
     * @return the Y
     */
    public T getY() {
        return y;
    }

    /**
     * Test this Pair for equality with another Object.
     *
     * @param o the other object
     * @return true if equals, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(this.x, pair.x) && Objects.equals(this.y, pair.y);
    }

    /**
     * Generate a hash code for this Pair.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int result = this.x != null ? this.x.hashCode() : 0;
        result = 31 * result + (this.y != null ? this.y.hashCode() : 0);
        return result;
    }

    /**
     * String representation of this Pair.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Pair{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }
}
