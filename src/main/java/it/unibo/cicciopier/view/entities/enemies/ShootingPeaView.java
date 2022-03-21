package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.enemies.ShootingPea;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

public class ShootingPeaView implements GameObjectView {

    private final ShootingPea pea;
    private ShootingPea.Statuses status;

    /**
     * Constructor for this class
     *
     * @param pea The Shooting Pea entity
     */
    public ShootingPeaView(final ShootingPea pea) {
        this.pea = pea;
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
        this.status = this.pea.getStatus();
        switch (this.status){
            case IDLE: g.drawImage(Texture.SHOOTING_PEA.getTexture(),
                    this.pea.getPos().getX(),
                    this.pea.getPos().getY(),
                    this.pea.getPos().getX()+ EntityType.SHOOTING_PEA.getWidth(),
                    this.pea.getPos().getX()+ EntityType.SHOOTING_PEA.getHeight(),
                    0,0,32,64,null);
                    break;
        }
    }
}
