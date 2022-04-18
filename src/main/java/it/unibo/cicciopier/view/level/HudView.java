package it.unibo.cicciopier.view.level;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.model.entities.Player;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.Texture;

import javax.swing.*;
import java.awt.*;

public final class HudView extends JPanel {
    private final Engine engine;

    public HudView(final Engine engine) {
        this.engine = engine;
    }

    /**
     * Load panel components.
     */
    public void load() {
        // Setup panel
        this.setBounds(0, 0, (int) (this.getPreferredSize().getWidth()), (int) (this.getPreferredSize().getHeight()));
        this.setOpaque(false);
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
                Screen.scale(20),
                Screen.scale(20),
                Screen.scale(Texture.HEALTH_BAR_DECORATION.getTexture().getWidth()),
                Screen.scale(Texture.HEALTH_BAR_DECORATION.getTexture().getHeight()),
                null);
        final int health = Texture.HEALTH_BAR.getTexture().getWidth() * p.getHp() / p.getMaxHp();
        // 0 or negative width can't be drawn
        if (health > 0) {
            //draw the health bar
            g.drawImage(Texture.HEALTH_BAR.getTexture().getSubimage(0, 0, health, Texture.HEALTH_BAR.getTexture().getHeight()),
                    Screen.scale(40),
                    Screen.scale(20),
                    Screen.scale(health),
                    Screen.scale(Texture.HEALTH_BAR.getTexture().getHeight()),
                    null);
        }
        //draw stamina bar decoration
        g.drawImage(Texture.STAMINA_BAR_DECORATION.getTexture(),
                Screen.scale(20),
                Screen.scale(50),
                Screen.scale(Texture.STAMINA_BAR_DECORATION.getTexture().getWidth()),
                Screen.scale(Texture.STAMINA_BAR_DECORATION.getTexture().getHeight()),
                null);
        final int stamina = Texture.STAMINA_BAR.getTexture().getWidth() * p.getStamina() / p.getMaxStamina();
        // 0 or negative width can't be drawn
        if (stamina > 0) {
            //draw the stamina bar
            g.drawImage(Texture.STAMINA_BAR.getTexture().getSubimage(0, 0, stamina, Texture.STAMINA_BAR.getTexture().getHeight()),
                    Screen.scale(40),
                    Screen.scale(50),
                    Screen.scale(stamina),
                    Screen.scale(Texture.STAMINA_BAR.getTexture().getHeight()),
                    null);
        }
        //draw score counter
        g.setFont(CustomFont.getInstance().getFontOrDefault());
        g.drawString("Score: " + p.getScore(), Screen.scale(20), Screen.scale(110));
        //draw coin
        g.drawImage(Texture.COIN.getTexture().getSubimage(0, 0, Texture.COIN.getTexture().getHeight(), Texture.COIN.getTexture().getHeight()),
                Screen.scale(20),
                Screen.scale(130),
                Screen.scale(Texture.COIN.getTexture().getHeight()),
                Screen.scale(Texture.COIN.getTexture().getHeight()),
                null
        );
        //draw coin counter
        g.setFont(CustomFont.getInstance().getFontOrDefault());
        g.drawString("x" + p.getCoin(), Screen.scale(50), Screen.scale(147));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Player p = this.engine.getWorld().getPlayer();
        // render game hud
        this.renderHud(p, g);
        // dispose
        g.dispose();
    }
}
