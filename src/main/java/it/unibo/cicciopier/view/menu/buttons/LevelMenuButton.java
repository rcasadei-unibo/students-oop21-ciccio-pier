package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.Engine;
import it.unibo.cicciopier.controller.LevelMenuAction;

/**
 * Define a button implementation with his {@link LevelMenuAction}.
 */
public class LevelMenuButton extends CustomButton {
    private final LevelMenuAction action;
    private final Engine engine;

    /**
     * This constructor calls the fathers constructor and adds the implementation of a mouse listener
     *
     * @param button define the button type
     * @param engine the instance of the engine
     */
    public LevelMenuButton(final Buttons button, final LevelMenuAction action, final Engine engine) {
        super(button);
        this.action = action;
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonAction() {
        this.engine.action(this.action);
    }
}
