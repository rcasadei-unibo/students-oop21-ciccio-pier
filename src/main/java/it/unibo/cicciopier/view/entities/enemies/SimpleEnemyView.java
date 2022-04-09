package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.EnemyStatuses;
import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Generic abstract class for any SimpleEnemy view.
 * Render is automatic, based on the entity status and its relative information.
 */
abstract class SimpleEnemyView implements GameObjectView {
    private final SimpleEnemy entity;
    private final Texture texture;
    private final int width;
    private final int height;
    private int frames;
    private int col;
    private int row;
    private int secs;
    private boolean dead;
    private double interval;
    private double durationTicks;
    private EnemyStatuses status;

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

    /**
     * Utility method to check if the current animation has to be displayed specularly or not
     *
     * @return True, if the render has to be specular
     */
    private boolean isSpecular() {
        if (!this.entity.isTextureSpecular()) {
            return this.entity.isFacingRight();
        } else {
            return !this.entity.isFacingRight();
        }
    }

    /**
     * Utility method to check if the status has changed.
     * In that case, it returns false, and it reloads every information
     * regarding the render of the current animation
     *
     * @return True, if the status has not changed
     */
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

    /**
     * Utility method to render a texture normally
     *
     * @param g The graphics
     */
    private void rend(Graphics g) {
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                this.col, this.row, this.col + this.width, this.row + this.height, null);
    }

    /**
     * Utility method to render a texture specularly
     *
     * @param g The graphics
     */
    private void specularRend(Graphics g) {
        g.drawImage(this.texture.getTexture(),
                this.entity.getPos().getX(),
                this.entity.getPos().getY(),
                this.entity.getPos().getX() + this.width,
                this.entity.getPos().getY() + this.height,
                this.col + this.width - 1, this.row, this.col, this.row + this.height, null);
    }

    /**
     * Utility method to render a texture normally,
     * with the animation played backward
     *
     * @param g The graphics
     */
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

    /**
     * Utility method to render a texture specularly,
     * with the animation played backward
     *
     * @param g The graphics
     */
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

    /**
     * If status has not changed, it updates the animation ticks. If they
     * are equal to the animation frame interval, the next frame gets set to be rendered
     * The animation gets then rendered
     *
     * @param g The graphics
     */
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
