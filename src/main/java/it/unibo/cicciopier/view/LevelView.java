package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;

import javax.swing.*;
import java.awt.*;

/**
 * Custom {@link JPanel} class for game rendering.
 */
public class LevelView extends JPanel {
    private final Engine engine;
    private final GameCam cam;

    /**
     * Constructor for this class.
     *
     * @param engine the instance of the engine
     */
    public LevelView(final Engine engine) {
        this.engine = engine;
        this.cam = new GameCam();
    }

    /**
     * Load panel components.
     */
    public void load() {
        // Setup cam
        this.cam.setViewportWidth((int) this.getPreferredSize().getWidth());
        this.cam.setViewportHeight((int) this.getPreferredSize().getHeight());
        this.cam.setOffsetMax(this.engine.getWorld().getWidth() * Block.SIZE - this.cam.getViewportWidth());
        this.cam.setOffsetMin(0);
        // Setup panel
        this.setBounds(0, 0, (int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight());
        this.setBackground(Color.CYAN);
        this.setLayout(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Player p = this.engine.getWorld().getPlayer();
        this.cam.translate(p, g);
        // render blocks
        for (Block b : this.engine.getWorld()) {
            b.getView().render(g);
        }
        // render entities
        for (Entity e : this.engine.getWorld().getEntities()) {
            if (e.getView() == null) {
                // Render even if the view is null - developing purpose
                g.setColor(Color.RED);
                g.drawRect(e.getPos().getX(), e.getPos().getY(), e.getWidth() - 1, e.getHeight() - 1);
            } else {
                e.getView().render(g);
            }
        }
        // render player
        if (p.getView() == null) {
            // Render even if the view is null - developing purpose
            g.setColor(Color.BLACK);
            g.drawRect(p.getPos().getX(), p.getPos().getY(), p.getWidth() - 1, p.getHeight() - 1);
        } else {
            p.getView().render(g);
        }
        // dispose
        //g.dispose();
    }

}
