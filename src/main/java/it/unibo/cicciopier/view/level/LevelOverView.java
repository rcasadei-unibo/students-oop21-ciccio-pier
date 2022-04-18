package it.unibo.cicciopier.view.level;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.controller.LevelMenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.LevelMenuButton;

import java.awt.*;

/**
 * Class for rendering a menu when the game is {@link GameState#OVER}
 */
public class LevelOverView extends LevelStateView {
    private final LevelMenuButton restartButton;
    private final LevelMenuButton homeButton;

    /**
     * Constructor for this class.
     *
     * @param engine engine
     */
    public LevelOverView(final Engine engine) {
        super(engine, GameState.OVER);
        this.restartButton = new LevelMenuButton(Buttons.RESTART, LevelMenuAction.RESTART, engine);
        this.homeButton = new LevelMenuButton(Buttons.HOME, LevelMenuAction.HOME, engine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        super.load();
        // Add button to this view
        this.add(this.restartButton);
        this.add(this.homeButton);
        // Set position
        final int y = (int) (this.getPreferredSize().getHeight() * 0.58);
        this.updateButton(this.homeButton, 0.45, y);
        this.updateButton(this.restartButton, 0.55, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderState(final Graphics g, final int score) {
        g.drawImage(Texture.GAMEOVER_BACKGROUND.getTexture(),
                0,
                0,
                (int) this.getPreferredSize().getWidth(),
                (int) this.getPreferredSize().getHeight(),
                null);
    }
}
