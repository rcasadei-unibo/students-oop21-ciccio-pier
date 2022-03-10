package it.unibo.cicciopier.controller;

/**
 * Simple implementation of the interface {@link Loop}.
 */
public class GameLoop extends Thread implements Loop {
    private static final int TICK_TIME = 10;

    private final Engine engine;
    private boolean running;

    /**
     * Constructor for this class.
     *
     * @param engine the game engine
     */
    public GameLoop(final Engine engine) {
        super("Game Thread");
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startLoop() {
        this.running = true;
        this.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        long lastLoopTime = 0;
        long deltaTime;
        while (this.running) {
            // Get delta time
            deltaTime = System.currentTimeMillis() - lastLoopTime;
            if (deltaTime < TICK_TIME) {
                try {
                    Thread.sleep(TICK_TIME - deltaTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lastLoopTime = System.currentTimeMillis();
            if (this.engine.getState() == GameState.RUNNING
                    || this.engine.getState() == GameState.PAUSED) {
                this.engine.update();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopLoop() {
        this.running = false;
    }

}
