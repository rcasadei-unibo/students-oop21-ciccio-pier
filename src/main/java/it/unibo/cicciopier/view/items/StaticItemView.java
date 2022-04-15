package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.settings.DeveloperMode;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

public class StaticItemView implements GameObjectView {
    private final Entity entity;
    private final Texture texture;

    /**
     * Constructor for this class, crate an instance of an item view
     *
     * @param entity the entity to render
     * @param texture the texture of the item
     */
    public StaticItemView(final Entity entity, final Texture texture) {
        this.entity = entity;
        this.texture = texture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        g.drawImage(this.texture.getTexture(),
                Screen.scale(this.entity.getPos().getX()),
                Screen.scale(this.entity.getPos().getY()),
                Screen.scale(this.texture.getTexture().getWidth()),
                Screen.scale(this.texture.getTexture().getHeight()),
                null
        );
        this.renderBounds(g);
    }

    /**
     * Render the bounds
     *
     * @param g graphic context
     */
    public void renderBounds(final Graphics g) {
        if (DeveloperMode.isActive()) {
            g.setColor(Color.BLACK);
            g.drawRect(Screen.scale(this.entity.getPos().getX()),
                    Screen.scale(this.entity.getPos().getY()),
                    Screen.scale(this.entity.getWidth() - 1),
                    Screen.scale(this.entity.getHeight() - 1)
            );
        }
    }
}
