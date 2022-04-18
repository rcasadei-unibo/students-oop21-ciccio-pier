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
     * Show the game window.
     */
    void start();

    /**
     * Update the game window one time.
     */
    void render();

}
