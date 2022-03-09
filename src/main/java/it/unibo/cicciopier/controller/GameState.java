package it.unibo.cicciopier.controller;

/**
 * Represents the state of the game.
 */
public enum GameState {
    /**
     * The game is loading the resources.
     */
    LOADING,
    /**
     * The game is running.
     */
    RUNNING,
    /**
     * The game is paused, waiting for resume or exit.
     */
    PAUSED,
    /**
     * The game is over, waiting for level restart or exit.
     */
    OVER
}
