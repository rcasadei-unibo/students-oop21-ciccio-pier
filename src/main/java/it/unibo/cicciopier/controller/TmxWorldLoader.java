package it.unibo.cicciopier.controller;

import it.unibo.cicciopier.App;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.blocks.base.BlockType;
import it.unibo.cicciopier.model.entities.base.Entity;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.utility.Vector2d;
import org.mapeditor.core.*;
import org.mapeditor.io.TMXMapReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Simple implementation of the interface {@link WorldLoader} for tmx files.
 */
public class TmxWorldLoader implements WorldLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(TmxWorldLoader.class);

    private final World world;
    private final String level;
    private Map map;

    /**
     * Constructor for this class.
     *
     * @param world the world instance
     * @param level the file name
     */
    public TmxWorldLoader(final World world, final String level) {
        this.world = world;
        this.level = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLevelName() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws Exception {
        TMXMapReader reader = new TMXMapReader();
        URL url = App.class.getResource("/levels/" + this.level);
        LOGGER.info("Loading file {}", url);
        // read height and width from map.
        this.map = reader.readMap(url);
        this.getWorld().setHeight(this.map.getHeight());
        this.getWorld().setWidth(this.map.getWidth());
        LOGGER.info("Loading map - height: {} - width: {}", this.getWorld().getHeight(), this.getWorld().getWidth());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadBlocks() {
        TileLayer layer = (TileLayer) map.getLayer(0);
        LOGGER.info("Level {} - {}", layer.getId(), layer.getName());
        // get every tile and create a block from its id, then set it at its position and add it to the world.
        for (int ty = 0; ty < this.getWorld().getHeight(); ty++) {
            for (int tx = 0; tx < this.getWorld().getWidth(); tx++) {
                Tile tile = layer.getTileAt(tx, ty);
                BlockType type = BlockType.AIR;
                if (tile != null) {
                    int id = tile.getId();
                    try {
                        type = BlockType.values()[id];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        LOGGER.error("Invalid block id {} in {}, skipping...", id, level);
                        continue;
                    }
                }
                Block b = this.getWorld().getBlockFactory().createBlock(type);
                if(b == null) {
                    LOGGER.error("Error creating block of type {} in {} at coordinates {} {}, skipping...", type.name(), level, tx, ty);
                    continue;
                }
                b.setPos(new Vector2d(tx * Block.SIZE, ty * Block.SIZE));
                this.getWorld().setBlock(tx, ty, b);
                //LOGGER.info("  * Block - type: {} - x: {} - y: {}", type, tx, ty);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadEntities() {
        ObjectGroup layer = (ObjectGroup) map.getLayer(1);
        LOGGER.info("Level {} - {}", layer.getId(), layer.getName());
        // get every object and create an entity from its type, then teleport it and add it to the world.
        for (MapObject object : layer) {
            String id = object.getType();
            EntityType type;
            try {
                type = EntityType.valueOf(id);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Invalid entity type {} in {}, skipping...", id, level);
                continue;
            }
            Entity e = this.getWorld().getEntityFactory().createEntity(type);
            if(e == null) {
                LOGGER.error("Error creating entity of type {} in {} at coordinates {} {}, skipping...", type.name(), level, object.getX(), object.getY());
                continue;
            }
            e.setPos(new Vector2d(object.getX(), object.getY()));
            this.getWorld().addEntity(e);
            //LOGGER.info("  * Entity - type: {} - x: {} - y: {}", type, object.getX(), object.getY());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPlayer() {
        ObjectGroup layer = (ObjectGroup) map.getLayer(2);
        LOGGER.info("Level {} - {}", layer.getId(), layer.getName());
        // get the first object and teleport the player there.
        MapObject object = layer.iterator().next();
        this.world.getPlayer().setPos(new Vector2d(object.getX(), object.getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.world;
    }

}
