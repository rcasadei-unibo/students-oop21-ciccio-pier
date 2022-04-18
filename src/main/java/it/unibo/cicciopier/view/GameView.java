package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.Input;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.level.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Simple implementation of the interface {@link View}.
 */
public class GameView extends JLayeredPane implements View, KeyListener {
    private final Engine engine;
    private final LevelView level;
    private final HudView hud;
    private final LevelPausedView paused;
    private final LevelOverView over;
    private final LevelWonView won;

    /**
     * Constructor for this class.
     *
     * @param engine the game engine
     */
    public GameView(final Engine engine) {
        this.engine = engine;
        this.level = new LevelView(engine);
        this.hud = new HudView(engine);
        this.paused = new LevelPausedView(engine);
        this.over = new LevelOverView(engine);
        this.won = new LevelWonView(engine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        // Setup pane
        this.setPreferredSize(Screen.getCurrentDimension());
        this.setLayout(null);
        // Setup level
        this.level.setPreferredSize(Screen.getCurrentDimension());
        this.level.load();
        this.add(this.level, Integer.valueOf(0));
        // Setup hud
        this.hud.setPreferredSize(Screen.getCurrentDimension());
        this.hud.load();
        this.add(this.hud, Integer.valueOf(1));
        // Setup paused view
        this.paused.setPreferredSize(Screen.getCurrentDimension());
        this.paused.load();
        this.add(this.paused, Integer.valueOf(2));
        // Setup over view
        this.over.setPreferredSize(Screen.getCurrentDimension());
        this.over.load();
        this.add(this.over, Integer.valueOf(3));
        // Setup won view
        this.won.setPreferredSize(Screen.getCurrentDimension());
        this.won.load();
        this.add(this.won, Integer.valueOf(4));
        // Setup key listener
        this.addKeyListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.requestFocus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.repaint();
    }

    /**
     * Handles key type on game view.
     */
    @Override
    public void keyTyped(final KeyEvent e) {

    }

    /**
     * Handles key press on game view.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                this.engine.pause();
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.engine.getInput().setPressed(Input.LEFT);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.engine.getInput().setPressed(Input.RIGHT);
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_SPACE:
                this.engine.getInput().setPressed(Input.JUMP);
                break;
            case KeyEvent.VK_F:
                this.engine.getInput().setPressed(Input.ATTACK);
                break;
        }
    }

    /**
     * Handles key release on game view.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.engine.getInput().setUnpressed(Input.LEFT);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.engine.getInput().setUnpressed(Input.RIGHT);
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_SPACE:
                this.engine.getInput().setUnpressed(Input.JUMP);
                break;
            case KeyEvent.VK_F:
                this.engine.getInput().setUnpressed(Input.ATTACK);
                break;
        }
    }

}
