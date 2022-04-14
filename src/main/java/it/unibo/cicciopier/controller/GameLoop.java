package it.unibo.cicciopier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple implementation of the interface {@link Loop}.
 */
public class GameLoop extends Thread implements Loop {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameLoop.class);
    /**
     * Tick per second.
     */
    public static final int TPS = 60;
    /**
     * Duration of a single tick in millis.
     */
    private static final int LAPSE = 1_000 / TPS;

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
            if (deltaTime < LAPSE) {
                while (deltaTime < LAPSE) {
                    // Yield until it has been at least the target time between updates. This saves the CPU from hogging.
                    Thread.yield();
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        // do nothing
                    }
                    deltaTime = System.currentTimeMillis() - lastLoopTime;
                }
            } else if (lastLoopTime != 0 && deltaTime > LAPSE) {
                long behind = deltaTime - LAPSE;
                LOGGER.warn("Can't keep up! Did the system time change, or is the game overloaded? Running {}ms behind, skipping {} tick(s)", behind, behind / TPS);
            }
            lastLoopTime = System.currentTimeMillis();
            if (this.engine.getState() != GameState.LOADING) {
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
