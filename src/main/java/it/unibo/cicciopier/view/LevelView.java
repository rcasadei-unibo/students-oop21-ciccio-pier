package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;

import javax.swing.*;
import java.awt.*;

/**
 * Custom {@link JPanel} class for game rendering.
 */
public class LevelView extends JPanel {
    private final GameView view;
    private final GameCam cam;

    /**
     * Constructor for this class.
     */
    public LevelView(final GameView view) {
        this.view = view;
        this.cam = new GameCam();
    }

    /**
     * Load panel components.
     *
     * @throws Exception error
     */
    public void load() throws Exception {
        this.cam.setViewportSize((int) this.getPreferredSize().getWidth());
        this.cam.setOffsetMax(this.view.getEngine().getWorld().getWidth() * Block.SIZE - this.cam.getViewportSize());
        this.cam.setOffsetMin(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Player p = this.view.getEngine().getWorld().getPlayer();
        final int originX = this.cam.translate(p, g);
        // render blocks
        for (Block b : this.view.getEngine().getWorld()) {
            b.getView().render(g);
        }
        // render entities
        for (Entity e : this.view.getEngine().getWorld().getEntities()) {
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
        if (this.view.getEngine().getState() == GameState.PAUSED) {
            g.setColor(Color.BLACK);
            g.drawString("PAUSED", originX + 10, 30);
        }
        if (this.view.getEngine().getState() == GameState.OVER) {
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER!", originX + 10, 30);
        }
        // dispose
        g.dispose();
    }

}
