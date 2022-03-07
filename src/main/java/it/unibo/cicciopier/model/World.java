package it.unibo.cicciopier.model;

/**
 * Contains game objects, like blocks, entities and player.
 */
public interface World {

    /**
     * Get the world's height in blocks.
     */
    int getHeight();

    /**
     * Set the world's height in blocks.
     * @param height the new height
     */
    void setHeight(int height);

    /**
     * Get the world's width in blocks.
     */
    int getWidth();

    /**
     * Set the world's width in blocks.
     * @param width the new width
     */
    void setWidth(int width);

    /**
     * Reset the world.
     */
    void clear();

}
