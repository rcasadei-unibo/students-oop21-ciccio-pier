package it.unibo.cicciopier.view.entities.items;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.model.entities.base.SimpleEntity;
import it.unibo.cicciopier.model.entities.items.Item;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

public abstract class SimpleItem extends SimpleEntity implements Item {
    private final GameObjectView view;

    /**
     * Constructor for this class
     *
     * @param world the game's world
     */
    public SimpleItem(final EntityType type, final World world, final Texture texture) {
        super(type, world);
        this.view = new StaticItemView(this, texture);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        //check if item collides with the player
        if (this.checkCollision(this.getWorld().getPlayer())) {
            this.onPickup(ticks);
        }
    }

}
