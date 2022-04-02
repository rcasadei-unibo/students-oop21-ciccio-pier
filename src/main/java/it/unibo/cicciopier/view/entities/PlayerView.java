package it.unibo.cicciopier.view.entities;

import it.unibo.cicciopier.model.entities.PlayerImpl;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

public class PlayerView implements GameObjectView {
    private static final int HEALTH_BAR_HEIGHT = 27;
    private static final int HEALTH_BAR_WIDTH = 179;
    private final PlayerImpl player;

    public PlayerView(final PlayerImpl player) {
        this.player = player;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(
                this.player.getPos().getX(),
                this.player.getPos().getY(),
                this.player.getWidth() - 1,
                this.player.getHeight() - 1
        );


    }
}
