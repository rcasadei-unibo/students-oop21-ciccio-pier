package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.ShootingPea;
import it.unibo.cicciopier.model.entities.enemies.ShootingPeaStatuses;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

public class ShootingPeaView implements GameObjectView {

    private final ShootingPea pea;
    private ShootingPeaStatuses status;
    private final int widht;
    private final int height;
    private int interval;
    private int duration;
    private int frames;
    private int col;
    private int row;
    private int secs;

    /**
     * Constructor for this class
     *
     * @param pea The Shooting Pea entity
     */
    public ShootingPeaView(final ShootingPea pea) {
        this.pea = pea;
        this.status = this.pea.getStatus();
        this.widht = this.pea.getWidth();
        this.height = this.pea.getHeight();
        this.row = this.status.getRow()*this.height;
        this.frames = this.status.getFrames();
        this.duration = this.status.getDuration();
        this.interval = (this.duration * 100) / this.frames;
        this.col = 0;
        this.secs = 0;
    }

    //checks if status changed, return true if not
    private boolean updateAnim() {
        if (this.status != this.pea.getStatus()){
            this.status = this.pea.getStatus();
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
                this.pea.getPos().getX(),
                this.pea.getPos().getY(),
                this.pea.getPos().getX() + this.widht,
                this.pea.getPos().getY() + this.height,
                this.col, this.row,this.col+this.widht, this.row+this.height, null);
    }


    private void specularRend(Graphics g){
        g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                this.pea.getPos().getX(),
                this.pea.getPos().getY(),
                this.pea.getPos().getX() + this.widht,
                this.pea.getPos().getY() + this.height,
                this.col+this.widht-1, this.row,this.col, this.row+this.height, null);
    }

    //if status has not changed, calculate time, and if right, change sprite
    //otherwise, render the new sprite

    private void loadAnim(Graphics g) {
        if (this.updateAnim()){
            this.secs++;
            if (this.secs == this.interval){
                this.col += this.widht;
                this.secs = 0;
                if (this.col == this.widht*this.frames){
                    this.col = 0;
                }
            }
        }
        if (this.pea.isSpecular()){
            this.specularRend(g);
        } else {
            this.rend(g);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        //PRIMA AGGIORNO LO STATUS.
        //QUANDO FACCIO UN'ANIMAZIONE, RENDERIZZO UNA LINEA CON UN LOOP,
        //IL LOOP DURA L'ANIMAZIONE, OGNI CICLO CONTROLLO ANCHE NON SIA
        //CAMBIATO LO STATUS. IN TAL CASO ESCO

        this.loadAnim(g);

    }
}

/*

            g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                    this.pea.getPos().getX(),
                    this.pea.getPos().getY(),
                    this.pea.getPos().getX() + EntityType.SHOOTING_PEA.getWidth(),
                    this.pea.getPos().getY() + EntityType.SHOOTING_PEA.getHeight(),
                    0, this.height*this.row, this.widht, (this.height*this.row)+this.height, null);

 */






/*

        g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                this.pea.getPos().getX(),
                this.pea.getPos().getY(),
                this.pea.getPos().getX() + EntityType.SHOOTING_PEA.getWidth(),
                this.pea.getPos().getY() + EntityType.SHOOTING_PEA.getHeight(),
                0, 64*this.row, 32, (64*this.row)+64, null);

 */

/*

        switch (this.status) {
            case IDLE:
                g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                        this.pea.getPos().getX(),
                        this.pea.getPos().getY(),
                        this.pea.getPos().getX() + EntityType.SHOOTING_PEA.getWidth(),
                        this.pea.getPos().getY() + EntityType.SHOOTING_PEA.getHeight(),
                        0, 0, 32, 64, null);
                break;
            case WALKING:
                g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                        this.pea.getPos().getX(),
                        this.pea.getPos().getY(),
                        this.pea.getPos().getY() + EntityType.SHOOTING_PEA.getWidth(),
                        this.pea.getPos().getX() + EntityType.SHOOTING_PEA.getHeight(),
                        0, 0, 32, 64, null);
                break;
        }

 */