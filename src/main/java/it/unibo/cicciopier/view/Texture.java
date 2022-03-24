package it.unibo.cicciopier.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Simple enum to reunite all the textures.
 */
public enum Texture {
    /**
     * Represents the texture of the blocks.
     */
    BLOCK("/textures/blocks.png"),
    /**
     * Represents the texture of the player.
     */
    PLAYER("/textures/blocks.png"),
    /**
     * Represents the texture of the Missile.
     */
    MISSILE("/textures/missile.png"),
    /**
     * Represents the texture of the coin.
     */
    COIN("/textures/coin.png"),
    /**
     * Represents the texture of an explosion.
     */
    EXPLOSION("/textures/explosion.png"),
    /**
     *
     */
    MENU_BACKGROUND("/menuGraphics/menuBackground.png"),
    /**
     *
     */
    PLAY_BUTTON("/menuGraphics/buttons/playButton/playButton.png"),
    /**
     *
     */
    PLAY_BUTTON_PRESSED("/menuGraphics/buttons/playButton/playButtonPressed.png"),
    /**
     *
     */
    SETTINGS_BUTTON("/menuGraphics/buttons/settingsButton/settingButton.png"),
    /**
     *
     */
    SETTINGS_BUTTON_HOVER("/menuGraphics/buttons/settingsButton/settingsButtonHover.png"),
    /**
     *
     */
    SETTINGS_BUTTON_PRESSED("/menuGraphics/buttons/settingsButton/settingsButtonPressed.png"),
    /**
     *
     */
    QUIT_BUTTON("/menuGraphics/buttons/quitButton/quitButton.png"),
    /**
     *
     */
    QUIT_BUTTON_PRESSED("/menuGraphics/buttons/quitButton/quitButtonPressed.png"),
    /**
     *
     */
    LEADERBOARD_BUTTON("/menuGraphics/buttons/leaderboardButton/leaderboardButton.png"),
    /**
     *
     */
    LEADERBOARD_BUTTON_PRESSED("/menuGraphics/buttons/leaderboardButton/leaderboardButtonPressed.png");
    private static final Logger LOGGER = LoggerFactory.getLogger(Texture.class);
    private final String fileName;
    private BufferedImage img;

    /**
     * Constructor for this Class.
     *
     * @param fileName path to the texture
     */
    Texture(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Load the specific texture.
     */
    public void load() throws IOException, IllegalArgumentException {
        LOGGER.info("Loading texture {} from file {}...", this.name(), this.fileName);
        final InputStream is = getClass().getResourceAsStream(this.fileName);
        this.img = ImageIO.read(is);
        is.close();
    }

    /**
     * Get the texture path.
     *
     * @return texture name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Get texture as a BufferImage.
     *
     * @return texture
     */
    public BufferedImage getTexture() {
        return this.img;
    }

}
