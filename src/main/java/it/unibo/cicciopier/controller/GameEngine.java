package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.view.GameView;
import it.unibo.cicciopier.view.View;

import java.util.Iterator;

/**
 * Simple implementation of the interface {@link Engine}.
 */
public class GameEngine implements Engine {
    private final WorldLoader loader;
    private final World world;
    private final View view;
    private final Loop loop;
    private GameState state;

    /**
     * Constructor for this class, it instantiates world, world loader, view, loop and game state.
     *
     * @param level the file name of the world
     */
    public GameEngine(final String level) {
        this.world = new GameWorld();
        this.loader = new TmxWorldLoader(this.getWorld(), level);
        this.view = new GameView(this);
        this.loop = new GameLoop(this);
        this.state = GameState.LOADING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        this.getWorldLoader().load();
        this.view.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void start() {
        this.getWorldLoader().create();
        this.state = GameState.RUNNING;
        this.getLoop().startLoop();
        this.view.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void pause() {
        if (this.state == GameState.RUNNING) {
            this.state = GameState.PAUSED;
        } else if (this.state == GameState.PAUSED) {
            this.state = GameState.RUNNING;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void restart() {
        this.getWorldLoader().create();
        this.state = GameState.RUNNING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void stop() {
        this.getLoop().stopLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        // check if player is dead, the game is over
        if (this.getWorld().getPlayer().isDead()) {
            this.state = GameState.OVER;
            return;
        }
        // for every entity check if it has to be removed, update it otherwise
        Iterator<Entity> es = this.getWorld().getEntities().iterator();
        while (es.hasNext()) {
            Entity e = es.next();
            if (e.isRemoved()) {
                es.remove();
                continue;
            }
            e.tick();
        }
        // update player
        this.getWorld().getPlayer().tick();
        // update view
        this.view.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loop getLoop() {
        return this.loop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorldLoader getWorldLoader() {
        return this.loader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.world;
    }

}
