package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.view.Texture;

public enum Projectiles {

    PEA(0.7,10,Texture.PEA),
    NUT(0,0,Texture.NUT),
    SLASH(1,3,Texture.SLASH),
    SPIKES(3,2,Texture.SPIKES);


    private final double duration;
    private final double range;
    private final Texture texture;

    Projectiles(final double duration, final double range, final Texture texture){
        this.duration = duration;
        this.range = range;
        this.texture = texture;
    }

    /**
     * Returns duration ticks
     */
    public double getDuration(){
        return this.duration * GameLoop.TPS;
    }

    /**
     * Return range
     */
    public double getRange(){
        return this.range * Block.SIZE;
    }

    /**
     * Return texture
     * @return
     */
    public Texture getTexture(){
        return this.texture;
    }

}
