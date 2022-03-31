package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.Input;
import it.unibo.cicciopier.model.blocks.base.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Simple implementation of the interface {@link View}.
 */
public class GameView extends JFrame implements View, KeyListener {
    private final Engine engine;
    private final LevelView level;

    /**
     * Constructor for this class.
     *
     * @param engine the game engine
     */
    public GameView(final Engine engine) {
        super("Level");
        this.engine = engine;
        this.level = new LevelView(this);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setResizable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        int h = engine.getWorld().getHeight() * Block.SIZE;
        this.level.setPreferredSize(new Dimension(h * 16 / 9, h));
        this.level.setBackground(Color.CYAN);
        this.level.load();
        this.add(this.level);
        this.pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.level.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Engine getEngine() {
        return this.engine;
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
            case KeyEvent.VK_SPACE:
                this.engine.getInput().setUnpressed(Input.JUMP);
                break;
            case KeyEvent.VK_F:
                this.engine.getInput().setUnpressed(Input.ATTACK);
                break;
        }
    }

}
