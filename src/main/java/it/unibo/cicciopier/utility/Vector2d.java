package it.unibo.cicciopier.utility;

/**
 * A simple 2d Vector class
 */
public class Vector2d {
    private double x;
    private double y;

    /**
     * Default constructor sets x,y to 0
     */
    public Vector2d() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * @param x coordinate of the cartesian plane
     * @param y coordinate of the cartesian plane
     */
    public Vector2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x coordinate
     */
    public int getX() {
        return (int) Math.round(this.x);
    }

    /**
     * @return y coordinate
     */
    public int getY() {
        return (int) Math.round(this.y);
    }

    /**
     * Add a vector to the current vector
     *
     * @param v1 vector to add
     */
    public void add(final Vector2d v1) {
        this.x += v1.getX();
        this.y += v1.getY();
    }

    /**
     * @return The squared length of the vector
     */
    public double getMagnitudeSq() {
        return Math.pow(this.x, 2) + Math.pow(this.y, 2);
    }

    /**
     * @return the length of the vector
     */
    public double getMagnitude() {
        return Math.sqrt(this.getMagnitudeSq());
    }

    /**
     * Set the length of the vector to 1
     */
    public void normalize() {
        final double length = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));

        this.x /= length;
        this.y /= length;
    }

    /**
     * Multiply the length of the vector scalar time
     *
     * @param scalar to multiply
     */
    public void scale(final double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * Set the maximus length that the vector can get
     *
     * @param max length of the vector
     */
    public void setLimiter(final double max) {
        if (this.getMagnitudeSq() > Math.pow(max, 2)) {
            this.normalize();
            this.scale(max);
        }
    }

    /**
     * Set the length of a vector
     *
     * @param length desired to be
     */
    public void setMagnitude(final double length) {
        this.normalize();
        this.scale(length);
    }

    /**
     * Set the variables
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void set(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the angle of the vector in radians
     *
     * @return angle
     */
    public double getAngle() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Rotate the vector by x radiant
     *
     * @param radiant how much the vector need to rotate
     */
    public void rotate(final double radiant) {
        final double x0 = this.x;
        final double y0 = this.y;

        this.x = x0 * Math.cos(radiant) - y0 * Math.sin(radiant);
        this.y = x0 * Math.sin(radiant) + y0 * Math.cos(radiant);
    }

    /**
     * Rotate the vector by x degree
     *
     * @param degree how much the vector need to rotate
     */
    public void rotateInDegree(final double degree) {
        final double x0 = this.x;
        final double y0 = this.y;

        this.x = x0 * Math.cos(degree) - y0 * Math.sin(degree);
        this.y = x0 * Math.sin(degree) + y0 * Math.cos(degree);
    }

    /**
     * Add two vectors
     *
     * @param v2 second vector
     * @return a vector
     */
    public Vector2d addVector(final Vector2d v2) {
        return new Vector2d(this.x + v2.getX(), this.y + v2.getY());
    }

    /**
     * Subtract the second vector from the first
     * Current vector - v2
     *
     * @param v2 second vector
     * @return a vector
     */
    public Vector2d subVector(final Vector2d v2) {
        return new Vector2d(this.x - v2.getX(), this.y - v2.getY());
    }

    /**
     * Get a vector that points from this to the second with the same length
     * Current vector ----> v2
     *
     * @param v2 second vector
     * @return a vector
     */
    public Vector2d directionVector(final Vector2d v2) {
        return new Vector2d(v2.getX() - this.x, v2.getY() - this.y);
    }

    /**
     * Get a vector with  length scaled by x times
     *
     * @param scalar input scalar
     * @return a vector
     */
    public Vector2d scaleVector(final double scalar) {
        final double x = this.x * scalar;
        final double y = this.y * scalar;

        return new Vector2d(x, y);
    }

    /**
     * Get the dot product between current and passed vector
     *
     * @param v2 second vector
     * @return the dot product
     */
    public double dot(final Vector2d v2) {
        return this.x * v2.getX() + this.y * v2.getY();
    }

    /**
     * Get the euclidean distance of 2 vectors current and v2
     *
     * @param v2 second vector
     * @return a vector
     */
    public double euclidDistance(final Vector2d v2) {
        final double first = Math.pow(this.x - v2.getX(), 2);
        final double second = Math.pow(this.y - v2.getY(), 2);

        return Math.sqrt(first + second);
    }
}
