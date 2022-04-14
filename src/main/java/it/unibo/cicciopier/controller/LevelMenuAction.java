package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.view.level.LevelView;

/**
 * Represents an action executed pressing a button in the {@link LevelView}.
 */
public enum LevelMenuAction {
    /**
     * Restart the level.
     */
    RESTART,
    /**
     * Resume playing.
     */
    RESUME,
    /**
     * Return to the home menu.
     */
    HOME
}
