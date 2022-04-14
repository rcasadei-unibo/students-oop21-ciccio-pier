package it.unibo.cicciopier.view.level;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.view.menu.buttons.LevelMenuButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Base class for rendering a panel during a specific {@link GameState}
 */
public abstract class LevelStateView extends JPanel {
    private final Engine engine;
    private final GameState state;
    private final Set<LevelMenuButton> buttons;

    /**
     * Constructor for this class.
     *
     * @param engine engine
     * @param state  game state
     */
    public LevelStateView(final Engine engine, final GameState state) {
        this.engine = engine;
        this.state = state;
        this.buttons = new HashSet<>();
    }

    /**
     * Load panel components.
     */
    public void load() {
        // Setup panel
        this.setBounds(0, 0, (int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight());
        this.setOpaque(false);
        this.setLayout(null);
    }

    /**
     * Set button position.
     */
    public void updateButton(final LevelMenuButton button, final double xPos, final int y) {
        final int w = (int) button.getPreferredSize().getWidth();
        final int h = (int) button.getPreferredSize().getHeight();
        final int x = (int) (this.getPreferredSize().getWidth() * xPos) - (w / 2);
        button.setBounds(x, y, w, h);
        this.buttons.add(button);
    }

    /**
     * Render view for a specific {@link GameState}
     *
     * @param g     graphics
     * @param score score
     */
    public abstract void renderState(final Graphics g, final int score);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Player p = this.engine.getWorld().getPlayer();
        // render state
        if (this.engine.getState() == this.state) {
            this.renderState(g, p.getScore());
        }
        // update buttons visibility
        for (final LevelMenuButton button : this.buttons) {
            button.setVisible(this.engine.getState() == this.state);
        }
        // dispose
        //g.dispose();
    }
}
