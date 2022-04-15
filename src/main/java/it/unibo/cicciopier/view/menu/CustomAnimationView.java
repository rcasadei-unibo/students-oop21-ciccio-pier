package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Animation;
import it.unibo.cicciopier.view.Texture;

import javax.swing.*;
import java.awt.*;

/**
 * This class is an animation used to sugar the main menu
 */
public class CustomAnimationView extends JComponent {
    private final Animation animationPlayer;
    private final Animation animationBroccoli;
    private int x;
    private boolean facing;
    private int frames;

    /**
     * This constructor generates an animation used in the main menu
     */
    public CustomAnimationView() {
        this.animationPlayer = new Animation(Texture.PLAYER, 7, 6, new Pair<>(0, 64), 52, 64);
        this.animationBroccoli = new Animation(Texture.BROCCOLI, 8, 6, new Pair<>(0, 0), 252, 384);
        this.animationBroccoli.load();
        this.animationPlayer.load();
        this.facing = false;
        this.frames = 0;
    }

    @Override
    protected void paintComponent(final Graphics g) {

        if (this.facing) {
            g.drawImage(this.animationPlayer.getSprite(this.frames / this.animationPlayer.getSpeed()),
                    (int) (this.getPreferredSize().getWidth() / 38.4 + (this.x + this.animationPlayer.getWidth()) * Screen.getScale()),
                    (int) (this.getPreferredSize().height - this.animationPlayer.getHeight() * 1.25 * Screen.getScale()),
                    (int) (-(this.animationPlayer.getWidth() * 1.25) * Screen.getScale()),
                    (int) ((this.animationPlayer.getHeight() * 1.25) * Screen.getScale()),
                    null);
        } else {
            g.drawImage(this.animationPlayer.getSprite(this.frames / this.animationPlayer.getSpeed()),
                    (int) (this.getPreferredSize().getWidth() / 38.4 + this.x * Screen.getScale()),
                    (int) (this.getPreferredSize().height - this.animationPlayer.getHeight() * 1.25 * Screen.getScale()),
                    (int) ((this.animationPlayer.getWidth() * 1.25) * Screen.getScale()),
                    (int) ((this.animationPlayer.getHeight() * 1.25) * Screen.getScale()),
                    null);
        }
        g.drawImage(this.animationBroccoli.getSprite(this.frames / this.animationBroccoli.getSpeed()),
                (int) (-((this.animationBroccoli.getWidth() / 1.75) * Screen.getScale()) + this.x * Screen.getScale()),
                0,
                (int) ((this.animationBroccoli.getWidth() / 1.75) * Screen.getScale()),
                (int) ((this.animationBroccoli.getHeight() / 1.75) * Screen.getScale()),
                null);
        this.frames++;
        if (this.frames == Integer.MAX_VALUE) {
            this.frames = 0;
        }
        if (this.facing) {
            this.x -= 3 * Screen.getScale();
            if (this.x <= -this.getPreferredSize().getWidth() / 38.4) {
                this.facing = false;
            }
        } else {
            this.x += 5 * Screen.getScale();
            if (this.x >= Screen.getCurrentDimension().width) {
                this.facing = true;
            }
        }
    }
}
