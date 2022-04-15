package it.unibo.cicciopier.view;

/**
 * Contains view objects and manages the game windows.
 */
public interface View {

    /**
     * Load game window.
     *
     * @throws Exception error
     */
    void load() throws Exception;

    /**
     * Open the game window.
     */
    void start();

    /**
     * Close game window.
     */
    void close();

    /**
     * Update the game window one time.
     */
    void render();

}
