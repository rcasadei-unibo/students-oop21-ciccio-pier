package it.unibo.cicciopier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple implementation of the interface {@link Loop}.
 */
public class GameLoop extends Thread implements Loop {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameLoop.class);
    private static final int TPS = 100;
    private static final int TICK_TIME = 1000 / TPS;

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
            } else if (lastLoopTime != 0 && deltaTime > TICK_TIME) {
                long behind = deltaTime - TICK_TIME;
                LOGGER.warn("Can't keep up! Did the system time change, or is the game overloaded? Running {}ms behind, skipping {} tick(s)", behind, behind / TPS);
            }
            lastLoopTime = System.currentTimeMillis();
            if (this.engine.getState() == GameState.RUNNING
                    || this.engine.getState() == GameState.PAUSED
                    || this.engine.getState() == GameState.OVER) {
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
