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
    private final JLayeredPane pane;
    private final LevelView level;
    private final HudView hud;
    private final LevelMenuView menu;

    /**
     * Constructor for this class.
     *
     * @param engine the game engine
     */
    public GameView(final Engine engine) {
        super("Level");
        this.engine = engine;
        this.pane = new JLayeredPane();
        this.level = new LevelView(engine);
        this.hud = new HudView(engine);
        this.menu = new LevelMenuView(engine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        final int h = engine.getWorld().getHeight() * Block.SIZE;
        // Setup pane
        this.pane.setPreferredSize(new Dimension(h * 16 / 9, h));
        this.pane.setLayout(null);
        // Setup level
        this.level.setPreferredSize(new Dimension(h * 16 / 9, h));
        this.level.load();
        this.pane.add(this.level, Integer.valueOf(0));
        // Setup hud
        this.hud.setPreferredSize(new Dimension(h * 16 / 9, h));
        this.hud.load();
        this.pane.add(this.hud, Integer.valueOf(1));
        // Setup menu
        this.menu.setPreferredSize(new Dimension(h * 16 / 9, h));
        this.menu.load();
        this.pane.add(this.menu, Integer.valueOf(2));
        // Setup JFrame
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setResizable(false);
        this.add(this.pane);
        this.pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.setVisible(false);
        this.dispose();
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
        this.pane.repaint();
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
