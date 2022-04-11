package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.controller.LevelMenuAction;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.LevelMenuButton;

import javax.swing.*;
import java.awt.*;

public class LevelMenuView extends JPanel {
    private final LevelMenuButton restartButton;
    private final LevelMenuButton resumeButton;
    private final LevelMenuButton homeButton;
    private final Engine engine;

    public LevelMenuView(final Engine engine) {
        this.restartButton = new LevelMenuButton(Buttons.RESTART, LevelMenuAction.RESTART, engine);
        this.resumeButton = new LevelMenuButton(Buttons.RESUME, LevelMenuAction.RESUME, engine);
        this.homeButton = new LevelMenuButton(Buttons.HOME, LevelMenuAction.HOME, engine);
        this.engine = engine;
    }

    /**
     * Load panel components.
     */
    public void load() {
        // Setup panel
        this.setBounds(1, 0, (int) this.getPreferredSize().getWidth() - 1, (int) this.getPreferredSize().getHeight());
        this.setLayout(null);
        // Add button to this view
        this.add(this.restartButton);
        this.add(this.resumeButton);
        this.add(this.homeButton);
    }

    /**
     * Update buttons position.
     */
    private void updateButtons() {
        if (this.engine.getState() == GameState.RUNNING) {
            this.homeButton.setVisible(false);
            this.restartButton.setVisible(false);
            this.resumeButton.setVisible(false);
        }
        if (this.engine.getState() == GameState.PAUSED) {
            final int y = (int) (this.getPreferredSize().getHeight() * 0.58);
            this.updateButton(this.homeButton, 0.42, y);
            this.updateButton(this.resumeButton, 0.5, y);
            this.updateButton(this.restartButton, 0.58, y);
            this.homeButton.setVisible(true);
            this.restartButton.setVisible(true);
            this.resumeButton.setVisible(true);
        }
        if (this.engine.getState() == GameState.OVER) {
            final int y = (int) (this.getPreferredSize().getHeight() * 0.58);
            this.updateButton(this.homeButton, 0.45, y);
            this.updateButton(this.restartButton, 0.55, y);
            this.homeButton.setVisible(true);
            this.restartButton.setVisible(true);
            this.resumeButton.setVisible(false);
        }
        if (this.engine.getState() == GameState.WON) {
            final int y = (int) (this.getPreferredSize().getHeight() * 0.68);
            this.updateButton(this.homeButton, 0.5, y);
            this.homeButton.setVisible(true);
            this.restartButton.setVisible(false);
            this.resumeButton.setVisible(false);
        }
    }

    /**
     * Update button position.
     */
    private void updateButton(final LevelMenuButton button, final double xPos, final int y) {
        final int w = (int) button.getPreferredSize().getWidth();
        final int h = (int) button.getPreferredSize().getHeight();
        final int x = (int) (this.getPreferredSize().getWidth() * xPos) - (w / 2);
        button.setBounds(x, y, w, h);
    }

    /**
     * Render view for {@link GameState#PAUSED}
     *
     * @param g graphics
     */
    private void renderPaused(final Graphics g) {
        if (this.engine.getState() != GameState.PAUSED) {
            return;
        }
        g.drawImage(Texture.PAUSE_BACKGROUND.getTexture(),
                0,
                0,
                (int) this.getPreferredSize().getWidth(),
                (int) this.getPreferredSize().getHeight(),
                null);
    }

    /**
     * Render view for {@link GameState#OVER}
     *
     * @param g     graphics
     * @param score the final score
     */
    private void renderOver(final Graphics g, final int score) {
        if (this.engine.getState() != GameState.OVER) {
            return;
        }
        g.drawImage(Texture.GAMEOVER_BACKGROUND.getTexture(),
                0,
                0,
                (int) this.getPreferredSize().getWidth(),
                (int) this.getPreferredSize().getHeight(),
                null);
        // TODO render score
    }

    /**
     * Render view for {@link GameState#WON}
     *
     * @param g     graphics
     * @param score the final score
     */
    private void renderWon(final Graphics g, final int score) {
        if (this.engine.getState() != GameState.WON) {
            return;
        }
        g.drawImage(Texture.VICTORY_BACKGROUND.getTexture(),
                0,
                0,
                (int) this.getPreferredSize().getWidth(),
                (int) this.getPreferredSize().getHeight(),
                null);
        // TODO render score
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        //super.paintComponent(g);
        final Player p = this.engine.getWorld().getPlayer();
        // render paused
        this.renderPaused(g);
        // render over
        this.renderOver(g, p.getScore());
        // render won
        this.renderWon(g, p.getScore());
        // relocate buttons
        this.updateButtons();
        // dispose
        //g.dispose();
    }
}
