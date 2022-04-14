package it.unibo.cicciopier.view.level;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.controller.LevelMenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.LevelMenuButton;

import java.awt.*;

/**
 * Class for rendering a menu when the game is {@link GameState#WON}
 */
public class LevelWonView extends LevelStateView {
    private final LevelMenuButton homeButton;

    /**
     * Constructor for this class.
     *
     * @param engine engine
     */
    public LevelWonView(final Engine engine) {
        super(engine, GameState.WON);
        this.homeButton = new LevelMenuButton(Buttons.HOME, LevelMenuAction.HOME, engine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        super.load();
        // Add button to this view
        this.add(this.homeButton);
        // Set position
        final int y = (int) (this.getPreferredSize().getHeight() * 0.68);
        this.updateButton(this.homeButton, 0.5, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderState(final Graphics g, final int score) {
        g.drawImage(Texture.VICTORY_BACKGROUND.getTexture(),
                0,
                0,
                (int) this.getPreferredSize().getWidth(),
                (int) this.getPreferredSize().getHeight(),
                null);
        // TODO render score
    }
}
