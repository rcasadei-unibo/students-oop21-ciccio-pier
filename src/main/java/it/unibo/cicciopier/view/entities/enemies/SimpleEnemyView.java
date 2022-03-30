package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.EnemyStatuses;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

//TO ADD REVERSE ANIMATIONS, CLEAN AND POLISH
abstract class SimpleEnemyView implements GameObjectView {

    private final SimpleEnemy entity;
    private EnemyStatuses status;
    private final int width;
    private final int height;
    private int interval;
    private int duration;
    private int frames;
    private int col;
    private int row;
    private int secs;

    public SimpleEnemyView(final SimpleEnemy entity){
        this.entity = entity;
        this.width = this.entity.getWidth();
        this.height = this.entity.getHeight();
        this.status = this.entity.getStatus();
        this.row = this.status.getRow()*this.height;
        this.frames = this.status.getFrames();
        this.duration = this.status.getDuration();
        this.interval = (this.duration * 100) / this.frames;
        this.col = 0;
        this.secs = 0;
    }

    //checks if status changed, return true if not
    private boolean updateAnim() {
        if (this.status != this.entity.getStatus()){
            this.status = this.entity.getStatus();
            this.secs = 0;
            this.row = this.status.getRow()*this.height;
            this.col = 0;
            this.frames = this.status.getFrames();
            this.duration = this.status.getDuration();
            return false;
        } else {
            return true;
        }
    }

    //render the sprite //correct one
    private void rend(Graphics g){
        g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                this.col, this.row,this.col+this.width, this.row+this.height, null);
    }

    private void specularRend(Graphics g){
        g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                this.col+this.width -1, this.row,this.col, this.row+this.height, null);
    }

    //if status has not changed, calculate time, and if right, change sprite
    //otherwise, render the new sprite
    private void loadAnim(Graphics g) {
        if (this.updateAnim()){
            this.secs++;
            if (this.secs == this.interval){
                this.col += this.width;
                this.secs = 0;
                if (this.col == this.width *this.frames){
                    this.col = 0;
                }
            }
        }
        if (this.entity.getSpecular()){
            this.specularRend(g);
        } else {
            this.rend(g);
        }
    }

    /**
     * {{@inheritDoc}}
     */
    @Override
    public void render(Graphics g) {
        this.loadAnim(g);
    }
}
