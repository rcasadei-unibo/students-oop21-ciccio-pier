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
     * Represents the texture of the ShootingPea
     */
    SHOOTING_PEA("/textures/enemies/shootingPea.png"),
    /**
     * Represents the texture of the NinjaPotato
     */
    NINJA_POTATO("/textures/enemies/ninjaPotato.png"),
    /**
     * Represents the texture of the Missile.
     */
    MISSILE("/textures/missile.png"),
    /**
     * Represents the texture of the coin.
     */
    COIN("/textures/coin.png"),
    /**
     * Represents the texture of the chicken
     */
    CHICKEN("/textures/chicken.png"),
    /**
     * Represents the texture of an explosion.
     */
    EXPLOSION("/textures/explosion.png"),
    /**
     * Represents the texture of the fire.
     */
    FIRE("/textures/fire.png"),
    /**
     * Represents the background of the menu
     */
    MENU_BACKGROUND("/menuGraphics/menuBackground.png"),
    /**
     * Represents the background of the level selection menu
     */
    LEVEL_SELECTION_BACKGROUND("/menuGraphics/levelSelectionBackground.png"),
    /**
     * Represents the background of the leaderboard menu
     */
    LEADERBOARD_BACKGROUND("/menuGraphics/leaderboardBackground.png"),
    /**
     * Represents the background of the login view
     */
    LOGIN_BACKGROUND("/menuGraphics/loginBackground.png"),
    /**
     * Represents the background of the login view
     */
    SETTINGS_BACKGROUND("/menuGraphics/settingsBackground.png"),
    /**
     * Represents the button used to access the first level
     */
    SUBMIT_BUTTON("/menuGraphics/buttons/submitButton/submitButton.png"),
    /**
     * Represents the button used to logout from the current user
     */
    LOGOUT_BUTTON("/menuGraphics/buttons/logoutButton/logoutButton.png"),
    /**
     * Represents the button used to logout from the current user while pressed
     */
    LOGOUT_BUTTON_PRESSED("/menuGraphics/buttons/logoutButton/logoutButtonPressed.png"),
    /**
     * Represents the button used to access the first level
     */
    SUBMIT_BUTTON_PRESSED("/menuGraphics/buttons/submitButton/submitButtonPressed.png"),
    /**
     * Represents the button used to increase the audio
     */
    PLUS_AUDIO_BUTTON("/menuGraphics/buttons/audioButtons/plusAudioButton.png"),
    /**
     * Represents the button used to increase the audio while pressed
     */
    PLUS_AUDIO_BUTTON_PRESSED("/menuGraphics/buttons/audioButtons/plusAudioButtonPressed.png"),
    /**
     * Represents the button used to decrease the audio
     */
    MINUS_AUDIO_BUTTON("/menuGraphics/buttons/audioButtons/minusAudioButton.png"),
    /**
     * Represents the button used to decrease the audio while pressed
     */
    MINUS_AUDIO_BUTTON_PRESSED("/menuGraphics/buttons/audioButtons/minusAudioButtonPressed.png"),
    /**
     * Represents the button used to access the first level
     */
    LEVEL_BUTTON_1("/menuGraphics/buttons/levelButtons/levelButton1.png"),
    /**
     * Represents the button used to access the first level while pressed
     */
    LEVEL_BUTTON_1_PRESSED("/menuGraphics/buttons/levelButtons/levelButton1Pressed.png"),
    /**
     * Represents the button used to access the second level
     */
    LEVEL_BUTTON_2("/menuGraphics/buttons/levelButtons/levelButton2.png"),
    /**
     * Represents the button used to access the second level while pressed
     */
    LEVEL_BUTTON_2_PRESSED("/menuGraphics/buttons/levelButtons/levelButton2Pressed.png"),
    /**
     * Represents the button used to access the third level
     */
    LEVEL_BUTTON_3("/menuGraphics/buttons/levelButtons/levelButton3.png"),
    /**
     * Represents the button used to access the third level while pressed
     */
    LEVEL_BUTTON_3_PRESSED("/menuGraphics/buttons/levelButtons/levelButton3Pressed.png"),
    /**
     * Represents the button used to access the boss level
     */
    LEVEL_BUTTON_BOSS("/menuGraphics/buttons/levelButtons/levelButtonBoss.png"),
    /**
     * Represents the button used to access the boss level while pressed
     */
    LEVEL_BUTTON_BOSS_PRESSED("/menuGraphics/buttons/levelButtons/levelButtonBossPressed.png"),
    /**
     * Represents the button used to access the main menu
     */
    HOME_BUTTON("/menuGraphics/buttons/homeButton/homeButton.png"),
    /**
     * Represents the button used to access the main menu while pressed
     */
    HOME_BUTTON_PRESSED("/menuGraphics/buttons/homeButton/homeButtonPressed.png"),
    /**
     * Represents the button used to access the main menu while hover it
     */
    HOME_BUTTON_HOVER("/menuGraphics/buttons/homeButton/homeButtonHover.png"),
    /**
     * Represents the menu play button
     */
    PLAY_BUTTON("/menuGraphics/buttons/playButton/playButton.png"),
    /**
     * Represents the menu play button while pressed
     */
    PLAY_BUTTON_PRESSED("/menuGraphics/buttons/playButton/playButtonPressed.png"),
    /**
     * Represents the settings button
     */
    SETTINGS_BUTTON("/menuGraphics/buttons/settingsButton/settingButton.png"),
    /**
     * Represents the settings button while pressed
     */
    SETTINGS_BUTTON_HOVER("/menuGraphics/buttons/settingsButton/settingsButtonHover.png"),
    /**
     * Represents the settings button while the mouse is hover it
     */
    SETTINGS_BUTTON_PRESSED("/menuGraphics/buttons/settingsButton/settingsButtonPressed.png"),
    /**
     * Represents the quit button
     */
    QUIT_BUTTON("/menuGraphics/buttons/quitButton/quitButton.png"),
    /**
     * Represents the quit button while pressed
     */
    QUIT_BUTTON_PRESSED("/menuGraphics/buttons/quitButton/quitButtonPressed.png"),
    /**
     * Represents the leaderboard button
     */
    LEADERBOARD_BUTTON("/menuGraphics/buttons/leaderboardButton/leaderboardButton.png"),
    /**
     * Represents the leaderboard button while pressed
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
        assert is != null;
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
