package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.controller.LevelMenuAction;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.LevelMenuButton;

import javax.swing.*;
import java.awt.*;

/**
 * Custom {@link JPanel} class for game rendering.
 */
public class LevelView extends JPanel {
    private static final int HEALTH_BAR_HEIGHT = 27;
    private static final int HEALTH_BAR_WIDTH = 179;

    private final LevelMenuButton restartButton;
    private final LevelMenuButton resumeButton;
    private final LevelMenuButton homeButton;
    private final Engine engine;
    private final GameCam cam;

    /**
     * Constructor for this class.
     *
     * @param engine the instance of the engine
     */
    public LevelView(final Engine engine) {
        this.restartButton = new LevelMenuButton(Buttons.RESTART, LevelMenuAction.RESTART, engine);
        this.resumeButton = new LevelMenuButton(Buttons.RESUME, LevelMenuAction.RESUME, engine);
        this.homeButton = new LevelMenuButton(Buttons.HOME, LevelMenuAction.HOME, engine);
        this.engine = engine;
        this.cam = new GameCam();
        this.setLayout(null);
    }

    /**
     * Load panel components.
     *
     * @throws Exception error
     */
    public void load() throws Exception {
        // Setup cam
        this.cam.setViewportWidth((int) this.getPreferredSize().getWidth());
        this.cam.setViewportHeight((int) this.getPreferredSize().getHeight());
        this.cam.setOffsetMax(this.engine.getWorld().getWidth() * Block.SIZE - this.cam.getViewportWidth());
        this.cam.setOffsetMin(0);
        // Add button to this view
        this.add(this.restartButton);
        this.add(this.resumeButton);
        this.add(this.homeButton);
    }

    /**
     * Update buttons position.
     */
    private void updateButtons(final int originX) {
        if (this.engine.getState() == GameState.RUNNING) {
            this.homeButton.setVisible(false);
            this.restartButton.setVisible(false);
            this.resumeButton.setVisible(false);
        }
        if (this.engine.getState() == GameState.PAUSED) {
            final int y = (int) (this.cam.getViewportHeight() * 0.58);
            this.updateButton(originX, this.homeButton, 0.42, y);
            this.updateButton(originX, this.resumeButton, 0.5, y);
            this.updateButton(originX, this.restartButton, 0.58, y);
            this.homeButton.setVisible(true);
            this.restartButton.setVisible(true);
            this.resumeButton.setVisible(true);
        }
        if (this.engine.getState() == GameState.OVER) {
            final int y = (int) (this.cam.getViewportHeight() * 0.58);
            this.updateButton(originX, this.homeButton, 0.45, y);
            this.updateButton(originX, this.restartButton, 0.55, y);
            this.homeButton.setVisible(true);
            this.restartButton.setVisible(true);
            this.resumeButton.setVisible(false);
        }
        if (this.engine.getState() == GameState.WON) {
            final int y = (int) (this.cam.getViewportHeight() * 0.68);
            this.updateButton(originX, this.homeButton, 0.5, y);
            this.homeButton.setVisible(true);
            this.restartButton.setVisible(false);
            this.resumeButton.setVisible(false);
        }
    }

    /**
     * Update button position.
     */
    private void updateButton(final int originX, final LevelMenuButton button, double xPos, int y) {
        final int w = (int) this.restartButton.getPreferredSize().getWidth();
        final int h = (int) this.restartButton.getPreferredSize().getHeight();
        final int x = (int) (this.cam.getViewportWidth() * xPos) + originX - (w / 2);
        button.setBounds(x, y, w, h);
    }

    /**
     * Render the game hud: health bar, stamina, score counter, coin counter
     *
     * @param p       player
     * @param g       graphics
     * @param originX camera starting x
     */
    private void renderHud(final Player p, final Graphics g, final int originX) {
        //draw health bar decoration
        g.drawImage(Texture.HEALTH_BAR_DECORATION.getTexture(),
                originX + 20,
                20,
                null);
        final int playerHealth = (LevelView.HEALTH_BAR_WIDTH * p.getHp()) / p.getMaxHp();
        // 0 or negative width can't be drawn
        if (playerHealth > 0) {
            //draw the health bar
            g.drawImage(
                    Texture.HEALTH_BAR.getTexture().getSubimage(0, 0, playerHealth, LevelView.HEALTH_BAR_HEIGHT),
                    originX + 40,
                    20,
                    null);
        }
        //draw stamina bar decoration
        g.drawImage(Texture.STAMINA_BAR_DECORATION.getTexture(),
                originX + 20,
                50,
                null);
        final int playerStamina = (LevelView.HEALTH_BAR_WIDTH * p.getStamina()) / p.getMaxStamina();
        // 0 or negative width can't be drawn
        if (playerStamina > 0) {
            //draw the stamina bar
            g.drawImage(
                    Texture.STAMINA_BAR.getTexture().getSubimage(0, 0, playerStamina, LevelView.HEALTH_BAR_HEIGHT),
                    originX + 40,
                    50,
                    null);
        }
        //draw score counter
        g.drawString("Score: " + p.getScore(), originX + 20, 110);
        //draw coin
        g.drawImage(
                Texture.COIN.getTexture().getSubimage(0, 0, 20, 20),
                originX + 20,
                130,
                null
        );
        //draw coin counter
        g.drawString("" + p.getCoin(), originX + 20 + 38, 144);
    }

    /**
     * Render view for {@link GameState#PAUSED}
     *
     * @param g       graphics
     * @param originX camera starting x
     */
    private void renderPaused(final Graphics g, final int originX) {
        if (this.engine.getState() != GameState.PAUSED) {
            return;
        }
        g.drawImage(Texture.PAUSE_BACKGROUND.getTexture(),
                originX,
                0,
                this.cam.getViewportWidth(),
                this.cam.getViewportHeight(),
                null);
    }

    /**
     * Render view for {@link GameState#OVER}
     *
     * @param g       graphics
     * @param originX camera starting x
     * @param score   the final score
     */
    private void renderOver(final Graphics g, final int originX, final int score) {
        if (this.engine.getState() != GameState.OVER) {
            return;
        }
        g.drawImage(Texture.GAMEOVER_BACKGROUND.getTexture(),
                originX,
                0,
                this.cam.getViewportWidth(),
                this.cam.getViewportHeight(),
                null);
        // TODO render score
    }

    /**
     * Render view for {@link GameState#WON}
     *
     * @param g       graphics
     * @param originX camera starting x
     * @param score   the final score
     */
    private void renderWon(final Graphics g, final int originX, final int score) {
        if (this.engine.getState() != GameState.WON) {
            return;
        }
        g.drawImage(Texture.VICTORY_BACKGROUND.getTexture(),
                originX,
                0,
                this.cam.getViewportWidth(),
                this.cam.getViewportHeight(),
                null);
        // TODO render score
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Player p = this.engine.getWorld().getPlayer();
        final int originX = this.cam.translate(p, g);
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
        // render game hud
        this.renderHud(p, g, originX);
        // render paused
        this.renderPaused(g, originX);
        // render over
        this.renderOver(g, originX, p.getScore());
        // render won
        this.renderWon(g, originX, p.getScore());
        // relocate buttons
        this.updateButtons(originX);
        // dispose
        //g.dispose();
    }

}
