package it.unibo.cicciopier.controller;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for input handling.
 */
public final class InputController {
    private final Set<Input> inputs;

    public InputController() {
        this.inputs = new HashSet<>();
    }

    /**
     * Check if an input is currently active.
     *
     * @param input the input
     * @return true if active, false otherwise
     */
    public boolean isPressed(final Input input) {
        return this.inputs.contains(input);
    }

    /**
     * Set an input as active.
     *
     * @param input the input
     */
    public void setPressed(final Input input) {
        this.inputs.add(input);
    }

    /**
     * Set an input as inactive.
     *
     * @param input the input
     */
    public void setUnpressed(final Input input) {
        this.inputs.remove(input);
    }

}
