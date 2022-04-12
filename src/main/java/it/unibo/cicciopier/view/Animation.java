package it.unibo.cicciopier.view;

import it.unibo.cicciopier.utility.Pair;

import java.awt.image.BufferedImage;

/**
 * Simple class to create an animation from an image
 */
public class Animation {
    private final Texture texture;
    private final int frames;
    private final int speed;
    private final BufferedImage[] sprites;
    private final int height;
    private final int width;
    private final Pair<Integer> start;

    /**
     * Constructor for this class, create an instance of animation
     *
     * @param texture the texture of this sprites
     * @param frames  how many frames does the sprite has
     * @param speed   animation speed
     * @param start   starting position
     * @param width   frame width
     * @param height  frame height
     */
    public Animation(final Texture texture, final int frames, final int speed, final Pair<Integer> start, final int width, final int height) {
        this.texture = texture;
        this.frames = frames;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.start = start;
        this.sprites = new BufferedImage[this.frames];
    }

    /**
     * Load sprite
     */
    public void load() {
        BufferedImage image = this.texture.getTexture().getSubimage(
                this.start.getX(),
                this.start.getY(),
                this.width * this.frames,
                this.height);
        //load all the animation
        for (int i = 0; i < this.frames; i++) {
            this.sprites[i] = image.getSubimage(i * this.width, 0, this.width, this.height);
        }
    }

    /**
     * Get the number of frames
     *
     * @return frames number
     */
    public int getFrames() {
        return this.frames;
    }

    /**
     * Get the frame height
     *
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Get the frame width
     *
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the animation speed
     *
     * @return speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Get the frame in a certain position,
     * if the position is out of bounds restarts
     * the count of frames from the beginning of the array
     *
     * @param frame the position
     * @return a frame
     */
    public BufferedImage getSprite(final int frame) {
        if (frame < 0) {
            return null;
        }
        return this.sprites[frame % this.frames];
    }
}
