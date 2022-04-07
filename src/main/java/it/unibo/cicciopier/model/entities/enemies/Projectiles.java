package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.view.Texture;

/**
 * Enum representing the enemies projectiles with their relative information, such as:
 * their duration, their range (travel distance) and their texture.
 */
public enum Projectiles {

    /**
     * Information relative the Pea projectile
     */
    PEA(0.7, 10, Texture.PEA),
    /**
     * Information relative the Nut projectile
     */
    NUT(0, 0, Texture.NUT),
    /**
     * Information relative the Slash projectile
     */
    SLASH(1, 3, Texture.SLASH),
    /**
     * Information relative the Spikes projectile
     */
    SPIKES(3, 2, Texture.SPIKES);

    private final double duration;
    private final double range;
    private final Texture texture;

    /**
     * Constructor used to store information about any Projectile
     *
     * @param duration The duration, in seconds, of the Projectile lasting
     * @param range    The travel distance of the Projectile
     * @param texture  The Projectile's texture
     */
    Projectiles(final double duration, final double range, final Texture texture) {
        this.duration = duration;
        this.range = range;
        this.texture = texture;
    }

    /**
     * Method to get the duration of the projectile
     *
     * @return The game ticks of the Projectile duration
     */
    public double getDuration() {
        return this.duration * GameLoop.TPS;
    }

    /**
     * Method to get the travel distance of the projectile
     *
     * @return The blocks distance
     */
    public double getRange() {
        return this.range * Block.SIZE;
    }

    /**
     * Method to get the projectile texture
     *
     * @return The projectile texture
     */
    public Texture getTexture() {
        return this.texture;
    }

}
