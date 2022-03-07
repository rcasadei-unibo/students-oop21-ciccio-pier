package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.model.World;

/**
 * Used for loading world elements from a file.
 * Call {@link #load()} and then {@link #create()} to populate the {@link World}.
 */
public interface WorldLoader {

    /**
     * The file name of the map to load.
     * @return the file name
     */
    String getLevelName();

    /**
     * Load world map in memory and set basic world elements.
     * @throws Exception if file is invalid or doesn't exist.
     */
    void load() throws Exception;

    /**
     * Load blocks from map and populate the {@link World}.
     */
    void loadBlocks();

    /**
     * Load entities from map and populate the {@link World}.
     */
    void loadEntities();

    /**
     * Load player from map and populate the {@link World}.
     */
    void loadPlayer();

    /**
     * Load elements to the {@link World} object.
     * Must be called after {@link #load()}.
     */
    default void create() {
        this.getWorld().clear();
        this.loadBlocks();
        this.loadEntities();
        this.loadPlayer();
    }

    /**
     * Get the created {@link World}.
     * @return the world
     */
    World getWorld();

}
