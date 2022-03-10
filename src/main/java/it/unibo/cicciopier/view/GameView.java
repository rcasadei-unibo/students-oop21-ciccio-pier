package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;

/**
 * Simple implementation of the interface {@link View}.
 */
public class GameView implements View {
    private final Engine engine;

    /**
     * Constructor for this class.
     *
     * @param engine the game engine
     */
    public GameView(final Engine engine) {
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Engine getEngine() {
        return this.engine;
    }

}
