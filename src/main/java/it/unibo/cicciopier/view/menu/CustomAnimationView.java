package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;

import javax.swing.*;
import java.awt.*;

public class CustomAnimationView extends JComponent {
    private final Animation animationPlayer;
    private final Animation animationBroccoli;
    private int frames;

    public CustomAnimationView() {
        this.animationPlayer = new Animation(Texture.PLAYER, 7, 6, new Pair<>(0, 64), 52, 64);
        this.animationBroccoli = new Animation(Texture.BROCCOLI, 8, 6, new Pair<>(0, 0), 252, 384);
        this.animationBroccoli.load();
        this.animationPlayer.load();
        this.frames = 0;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        int x = 20;
        g.drawImage(this.animationPlayer.getSprite(this.frames / this.animationPlayer.getSpeed()),
                (int) (x + animationBroccoli.getWidth() / 1.75 + this.getPreferredSize().getWidth() / 38.4),
                (int) (this.getPreferredSize().height - this.animationPlayer.getHeight() * 1.25 * Screen.getScale()),
                (int) ((this.animationPlayer.getWidth() * 1.25) * Screen.getScale()),
                (int) ((this.animationPlayer.getHeight() * 1.25) * Screen.getScale()),
                null);
        g.drawImage(this.animationBroccoli.getSprite(this.frames / this.animationBroccoli.getSpeed()),
                x,
                0,
                (int) ((this.animationBroccoli.getWidth() / 1.75) * Screen.getScale()),
                (int) ((this.animationBroccoli.getHeight() / 1.75) * Screen.getScale()),
                null);
        this.frames++;
        if (this.frames == Integer.MAX_VALUE) {
            this.frames = 0;
        }
    }
}
