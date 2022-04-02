package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.view.entities.PlayerView;
import it.unibo.cicciopier.view.items.CoinView;

import javax.swing.*;
import java.awt.*;

/**
 * Custom {@link JPanel} class for game rendering.
 */
public class LevelView extends JPanel {
    private static final int HEALTH_BAR_HEIGHT = 27;
    private static final int HEALTH_BAR_WIDTH = 179;

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
     * Render the game hub: health bar, stamina, score counter, coin counter
     *
     * @param p       player
     * @param g       graphics
     * @param originX camera starting x
     */
    private void hud(Player p, Graphics g, int originX) {
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
        //stamina
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
                Texture.COIN.getTexture().getSubimage(0, 0, CoinView.COIN_SIZE, CoinView.COIN_SIZE),
                originX + 20,
                130,
                null
        );
        //draw coin counter
        g.drawString("" + p.getCoin(), originX + CoinView.COIN_SIZE + 38, 144);
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
        //render game hub
        this.hud(p, g, originX);
        // dispose
        g.dispose();
    }

}
