package it.unibo.cicciopier.view;

import it.unibo.cicciopier.model.GameObject;

import java.awt.*;

/**
 * Represents the game cam.
 */
public class GameCam {
    private int viewportWidth;
    private int viewportHeight;
    private int offsetMax;
    private int offsetMin;

    /**
     * Center the cam on the given {@link GameObject}
     *
     * @param object   the game object
     * @param graphics the graphics object
     */
    public void translate(final GameObject object, final Graphics graphics) {
        int camX = ((int) object.getBounds().getCenterX()) - (this.getViewportWidth() / 2);
        if (camX > this.getOffsetMax()) {
            camX = this.getOffsetMax();
        }
        if (camX < this.getOffsetMin()) {
            camX = this.getOffsetMin();
        }
        // move view horizontally
        graphics.translate(-camX, 0);
    }

    /**
     * Get the cam's horizontal viewport.
     *
     * @return the viewport
     */
    public int getViewportWidth() {
        return this.viewportWidth;
    }

    /**
     * Get the cam's vertical viewport.
     *
     * @return the viewport
     */
    public int getViewportHeight() {
        return this.viewportHeight;
    }

    /**
     * Set the cam's horizontal viewport.
     *
     * @param viewportWidth the new viewport
     */
    public void setViewportWidth(final int viewportWidth) {
        this.viewportWidth = viewportWidth;
    }

    /**
     * Set the cam's vertical viewport.
     *
     * @param viewportHeight the new viewport
     */
    public void setViewportHeight(final int viewportHeight) {
        this.viewportHeight = viewportHeight;
    }

    /**
     * Get the cam's horizontal max offset.
     *
     * @return the max offset
     */
    public int getOffsetMax() {
        return this.offsetMax;
    }

    /**
     * Set the cam's horizontal max offset.
     *
     * @param offsetMax the new max offset
     */
    public void setOffsetMax(final int offsetMax) {
        this.offsetMax = offsetMax;
    }

    /**
     * Get the cam's horizontal min offset.
     *
     * @return the min offset
     */
    public int getOffsetMin() {
        return this.offsetMin;
    }

    /**
     * Set the cam's horizontal min offset.
     *
     * @param offsetMin the new min offset
     */
    public void setOffsetMin(final int offsetMin) {
        this.offsetMin = offsetMin;
    }

}
