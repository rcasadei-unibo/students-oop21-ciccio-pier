package it.unibo.cicciopier.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Simple enum to reunite all the textures
 */
public enum Texture {
    /**
     * Represents the texture of the blocks
     */
    BLOCK("/textures/blocks.png"),
    /**
     * Represents the texture of the player
     */
    PLAYER("/textures/blocks.png");

    private final String textureName;
    private BufferedImage img;

    /**
     * Constructor for this Class
     *
     * @param textureName path to the texture
     */
    Texture(final String textureName) {
        this.textureName = textureName;
    }

    /**
     * Load the specific texture
     */
    public void load() {
        final InputStream is = getClass().getResourceAsStream(this.textureName);
        try {
            assert is != null;
            this.img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the texture name
     *
     * @return texture name
     */
    public String getTextureName() {
        return this.textureName;
    }

    /**
     * Get texture as a BufferImage
     *
     * @return texture
     */
    public BufferedImage getTexture() {
        return this.img;
    }

}
