package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.view.View;

public class MenuManagerView implements View {
    private final Engine engine;

    public MenuManagerView(Engine engine) {
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
