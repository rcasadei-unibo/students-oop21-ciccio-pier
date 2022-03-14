package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.model.blocks.base.Block;

import javax.swing.*;
import java.awt.*;

/**
 * Simple implementation of the interface {@link View}.
 */
public class GameView extends JFrame implements View {
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
        this.add(level);
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

}
