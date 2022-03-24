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
    private final InputController input;
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
        this.input = new InputController();
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
        Iterator<Entity> i = this.getWorld().getEntities().iterator();
        while (i.hasNext()) {
            Entity e = i.next();
            if (e.isRemoved()) {
                i.remove();
                continue;
            }
            e.tick();
        }
        // process input
        this.processInput();
        // update player
        this.getWorld().getPlayer().tick();
        // update view
        this.view.render();
    }

    /**
     * Process the input handling.
     */
    private void processInput() {
        int velX = 0;
        if (this.getInput().isPressed(Input.RIGHT)) {
            velX += this.getWorld().getPlayer().getSpeed();
        }
        if (this.getInput().isPressed(Input.LEFT)) {
            velX -= this.getWorld().getPlayer().getSpeed();
        }
        this.getWorld().getPlayer().getVel().setX(velX);
        if (this.getInput().isPressed(Input.JUMP)) {
            this.getWorld().getPlayer().jump();
        }
        if (this.getInput().isPressed(Input.ATTACK)) {
            this.getWorld().getPlayer().attackNearest();
        }
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
    public InputController getInput() {
        return this.input;
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
