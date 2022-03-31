package it.unibo.cicciopier.view.entities.enemies;

import it.unibo.cicciopier.model.entities.enemies.SimpleEnemy;
import it.unibo.cicciopier.view.Texture;

/**
 * Class extending {@link SimpleEnemyView} for cleaner code.
 */
public class EnemyView extends SimpleEnemyView{

    /**
     * Simple constructor for any enemy view.
     *
     * @param enemy the enemy of this view
     * @param texture the texture of this enemy
     */
    public EnemyView(final SimpleEnemy enemy, final Texture texture) {
        super(enemy,texture);
    }
}
