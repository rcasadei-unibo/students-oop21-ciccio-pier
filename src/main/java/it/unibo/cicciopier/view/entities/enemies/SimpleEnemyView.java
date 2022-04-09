package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.EnemyStatuses;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

//TO ADD REVERSE ANIMATIONS, CLEAN AND POLISH

/**
 * Generic abstract class for any SimpleEnemy view.
 * Render is automatic, based on the entity status and its relative information.
 */
abstract class SimpleEnemyView implements GameObjectView {
    private final SimpleEnemy entity;
    private final Texture texture;
    private EnemyStatuses status;
    private final int width;
    private final int height;
    private boolean dead;
    private double interval;
    private double durationTicks;
    private int frames;
    private int col;
    private int row;
    private int secs;

    /**
     * Generic constructor for any SimpleEnemy view
     *
     * @param enemy   The enemy instance to be associated with the view
     * @param texture The enemy relative texture
     */
    public SimpleEnemyView(final SimpleEnemy enemy, final Texture texture) {
        this.entity = enemy;
        this.texture = texture;
        this.width = this.entity.getWidth();
        this.height = this.entity.getHeight();
        this.status = this.entity.getStatus();
        this.row = this.status.getRow() * this.height;
        this.frames = this.status.getFrames();
        this.durationTicks = this.status.getDurationTicks();
        this.interval = this.durationTicks / this.frames;
        this.col = 0;
        this.secs = 0;
        this.dead = false;
    }

    private boolean isSpecular(){
        if (!this.entity.isTextureSpecular()){
            return this.entity.isFacingRight();
        } else {
            return !this.entity.isFacingRight();
        }
    }

    //checks if status changed, return true if not
    private boolean updateAnim() {
        if (this.status != this.entity.getStatus()) {
            this.status = this.entity.getStatus();
            this.secs = 0;
            this.row = this.status.getRow() * this.height;
            this.col = 0;
            this.frames = this.status.getFrames();
            this.durationTicks = this.status.getDurationTicks();
            this.interval = this.durationTicks / this.frames;
            if (this.entity.isDead()) {
                this.dead = true;
            }
            return false;
        } else {
            return true;
        }
    }

    //render the sprite //correct one
    private void rend(Graphics g) {
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                this.col, this.row, this.col + this.width, this.row + this.height, null);
    }

    private void specularRend(Graphics g) {
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                this.col + this.width - 1, this.row, this.col, this.row + this.height, null);
    }

    private void reverseRend(Graphics g) {
        int reverseCol = this.width * this.frames;
        int reverseRow = Math.abs(this.row);
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                reverseCol - this.col - 1 - this.width, reverseRow,
                reverseCol - this.col - 1, reverseRow + this.height, null);
    }

    private void reverseSpecularRend(Graphics g) {
        int reverseCol = this.width * this.frames;
        int reverseRow = Math.abs(this.row);
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                reverseCol - this.col + this.width - 1
                , reverseRow,
                reverseCol - this.col, reverseRow + this.height, null);
    }

    //if status has not changed, calculate time, and if right, change sprite
    //otherwise, render the new sprite
    //Try to fix graphic glitch
    private void loadAnim(Graphics g) {
        if (this.updateAnim()) {
            this.secs++;
            if (this.secs >= this.interval) {
                this.col += this.width;
                this.secs = 0;
                if (this.col == this.width * this.frames && !this.dead) {
                    this.col = 0;
                }
            }
        }
        if (this.status.getRow() < 0 && this.isSpecular()) {
            this.reverseSpecularRend(g);
        } else if (this.status.getRow() < 0) {
            this.reverseRend(g);
        } else if (this.isSpecular()) {
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
