package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.model.GameWorld;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.view.GameView;
import it.unibo.cicciopier.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple implementation of the interface {@link Engine}.
 */
public class GameEngine implements Engine {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);
    private final MainMenuController menu;
    private final InputController input;
    private final WorldLoader loader;
    private final World world;
    private final View view;
    private final Loop loop;
    private GameState state;
    private long ticks;

    /**
     * Constructor for this class, it instantiates world, world loader, view, loop and game state.
     *
     * @param menu  the menu
     * @param level the file name of the world
     */
    public GameEngine(final MainMenuController menu, final String level) {
        this.menu = menu;
        this.input = new InputController();
        this.world = new GameWorld();
        this.loader = new TmxWorldLoader(this.getWorld(), level);
        this.view = new GameView(this);
        this.loop = new GameLoop(this);
        this.state = GameState.LOADING;
        this.ticks = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        LOGGER.info("Loading game...");
        this.getWorldLoader().load();
        this.view.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void start() {
        LOGGER.info("Starting game...");
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
            LOGGER.info("Pausing game...");
            this.state = GameState.PAUSED;
        } else if (this.state == GameState.PAUSED) {
            LOGGER.info("Resuming game...");
            this.state = GameState.RUNNING;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void restart() {
        LOGGER.info("Restarting game...");
        this.getWorldLoader().create();
        this.state = GameState.RUNNING;
        this.ticks = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void stop() {
        LOGGER.info("Stopping game...");
        this.getLoop().stopLoop();
        this.view.close();
        // TODO call function in MainMenuController to update user score
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (this.getState() == GameState.RUNNING) {
            // check if player is dead, the game is over
            if (this.getWorld().getPlayer().isDead()) {
                this.state = GameState.OVER;
                return;
            }
            // check if player has won, the game has ended
            if (this.getWorld().getPlayer().hasWon()) {
                this.state = GameState.WON;
                return;
            }
            // for every entity check if it has to be removed, update it otherwise
            for (Entity e : this.getWorld().getEntities()) {
                if (e.isRemoved()) {
                    this.getWorld().removeEntity(e);
                    continue;
                }
                e.tick(this.ticks);
            }
            // process input
            this.processInput();
            // update player
            this.getWorld().getPlayer().tick(this.ticks);
            // update ticks
            this.ticks++;
        }
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
    public long getTicks() {
        return this.ticks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(final LevelMenuAction action) {
        switch (action) {
            case RESTART:
                this.restart();
                break;
            case RESUME:
                this.pause();
                break;
            case HOME:
                this.stop();
                break;
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
