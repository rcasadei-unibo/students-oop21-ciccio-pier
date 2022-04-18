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
import java.util.Optional;

/**
 * Simple implementation of the interface {@link WorldLoader} for tmx files.
 */
public final class TmxWorldLoader implements WorldLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(TmxWorldLoader.class);
    private final World world;
    private final String level;
    private String background;
    private String music;
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
    public void load() throws Exception {
        final TMXMapReader reader = new TMXMapReader();
        final URL url = App.class.getResource("/levels/" + this.getLevelName());
        LOGGER.info("Loading file {}", url);
        // read height and width from map.
        this.map = reader.readMap(url);
        this.getWorld().setHeight(this.map.getHeight());
        this.getWorld().setWidth(this.map.getWidth());
        LOGGER.info("Loading map - height: {} - width: {}", this.getWorld().getHeight(), this.getWorld().getWidth());
        final Properties properties = map.getProperties();
        // read background from map proprieties.
        this.background = properties.getProperty("background");
        LOGGER.info("Map background: {}", background);
        // read music from map proprieties.
        this.music = properties.getProperty("music");
        LOGGER.info("Map sound: {}", music);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadBlocks() {
        final TileLayer layer = (TileLayer) this.map.getLayer(0);
        LOGGER.info("Level {} - {}", layer.getId(), layer.getName());
        // get every tile and create a block from its id, then set it at its position and add it to the world.
        for (int ty = 0; ty < this.getWorld().getHeight(); ty++) {
            for (int tx = 0; tx < this.getWorld().getWidth(); tx++) {
                final Tile tile = layer.getTileAt(tx, ty);
                BlockType type = BlockType.AIR;
                if (tile != null) {
                    final int id = tile.getId();
                    try {
                        type = BlockType.values()[id];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        LOGGER.error("Invalid block id {} in {}, skipping...", id, this.getLevelName());
                        continue;
                    }
                }
                final Optional<Block> opt = this.getWorld().getBlockFactory().createBlock(type);
                if (opt.isEmpty()) {
                    LOGGER.error("Error creating block of type {} in {} at coordinates {} {}, skipping...",
                            type.name(), this.getLevelName(), tx, ty);
                    continue;
                }
                final Block b = opt.get();
                b.setPos(new Vector2d(tx * Block.SIZE, ty * Block.SIZE));
                this.getWorld().setBlock(tx, ty, b);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadEntities() {
        final ObjectGroup layer = (ObjectGroup) this.map.getLayer(1);
        LOGGER.info("Level {} - {}", layer.getId(), layer.getName());
        // get every object and create an entity from its type, then teleport it and add it to the world.
        for (final MapObject object : layer) {
            final String id = object.getType();
            EntityType type;
            try {
                type = EntityType.valueOf(id);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Invalid entity type {} in {}, skipping...", id, this.getLevelName());
                continue;
            }
            final Optional<Entity> opt = this.getWorld().getEntityFactory().createEntity(type);
            if (opt.isEmpty()) {
                LOGGER.error("Error creating entity of type {} in {} at coordinates {} {}, skipping...",
                        type.name(), this.getLevelName(), object.getX(), object.getY());
                continue;
            }
            final Entity e = opt.get();
            e.setPos(new Vector2d(object.getX(), object.getY()));
            e.load();
            this.getWorld().addEntity(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPlayer() {
        final ObjectGroup layer = (ObjectGroup) this.map.getLayer(2);
        LOGGER.info("Level {} - {}", layer.getId(), layer.getName());
        // get the first object and teleport the player there.
        final MapObject object = layer.iterator().next();
        this.getWorld().getPlayer().setPos(new Vector2d(object.getX(), object.getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.world;
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
    public String getBackground() {
        return background;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMusic() {
        return music;
    }
}
