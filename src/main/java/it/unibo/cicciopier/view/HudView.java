package it.unibo.cicciopier.view;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.model.entities.Player;

import javax.swing.*;
import java.awt.*;

public class HudView extends JPanel {
    private final Engine engine;

    public HudView(final Engine engine) {
        this.engine = engine;
    }

    /**
     * Load panel components.
     */
    public void load() {
        // Setup panel
        this.setBounds(0, 0, (int) this.getPreferredSize().getWidth() - 1, (int) this.getPreferredSize().getHeight());
        this.setLayout(null);
    }

    /**
     * Render the game hud: health bar, stamina, score counter, coin counter
     *
     * @param p player
     * @param g graphics
     */
    private void renderHud(final Player p, final Graphics g) {
        //draw health bar decoration
        g.drawImage(Texture.HEALTH_BAR_DECORATION.getTexture(),
                20,
                20,
                null);
        final int playerHealth = (Texture.HEALTH_BAR.getTexture().getWidth() * p.getHp()) / p.getMaxHp();
        // 0 or negative width can't be drawn
        if (playerHealth > 0) {
            //draw the health bar
            g.drawImage(
                    Texture.HEALTH_BAR.getTexture().getSubimage(0, 0, playerHealth, Texture.HEALTH_BAR.getTexture().getHeight()),
                    40,
                    20,
                    null);
        }
        //draw stamina bar decoration
        g.drawImage(Texture.STAMINA_BAR_DECORATION.getTexture(),
                20,
                50,
                null);
        final int playerStamina = (Texture.STAMINA_BAR.getTexture().getWidth() * p.getStamina()) / p.getMaxStamina();
        // 0 or negative width can't be drawn
        if (playerStamina > 0) {
            //draw the stamina bar
            g.drawImage(
                    Texture.STAMINA_BAR.getTexture().getSubimage(0, 0, playerStamina, Texture.STAMINA_BAR.getTexture().getHeight()),
                    40,
                    50,
                    null);
        }
        //draw score counter
        g.drawString("Score: " + p.getScore(), 20, 110);
        //draw coin
        g.drawImage(
                Texture.COIN.getTexture().getSubimage(0, 0, 20, 20),
                20,
                130,
                null
        );
        //draw coin counter
        g.drawString(String.valueOf(p.getCoin()), 20 + 38, 144);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        //super.paintComponent(g);
        final Player p = this.engine.getWorld().getPlayer();
        // render game hud
        this.renderHud(p, g);
        // dispose
        //g.dispose();
    }
}
