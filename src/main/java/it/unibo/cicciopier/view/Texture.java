package it.unibo.cicciopier.view;

import it.unibo.cicciopier.model.settings.DeveloperMode;
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
    PLAYER("/textures/entities/player.png"),
    /**
     * Represents the texture of the boss.
     */
    BROCCOLI("/textures/entities/enemies/boss/broccoli.png"),
    /**
     * Represents the meteor texture
     */
    METEOR("/textures/entities/meteor.png"),
    /**
     * Represents the texture entity health bar decoration
     */
    ENTITY_HEALTH_BAR_DECORATION("/textures/entities/enemies/boss/boss_health_bar_decoration.png"),
    /**
     * Represents the texture entity health bar
     */
    ENTITY_HEALTH_BAR("/textures/entities/enemies/boss/boss_health_bar.png"),
    /**
     * Represents the texture of the health bar decoration
     */
    HEALTH_BAR_DECORATION("/textures/hud/health_bar_decoration.png"),
    /**
     * Represents the texture of the stamina bar decoration
     */
    STAMINA_BAR_DECORATION("/textures/hud/stamina_bar_decoration.png"),
    /**
     * Represents the texture od the health bar
     */
    HEALTH_BAR("/textures/hud/health_bar.png"),
    /**
     * Represents the texture od the stamina bar
     */
    STAMINA_BAR("/textures/hud/stamina_bar.png"),
    /**
     * Represents the texture of the ShootingPea
     */
    SHOOTING_PEA("/textures/entities/enemies/shooting_pea.png"),
    /**
     * Represents the texture of the NinjaPotato
     */
    NINJA_POTATO("/textures/entities/enemies/ninja_potato.png"),
    /**
     * Represents the texture of the RollingPeach
     */
    ROLLING_PEACH("/textures/entities/enemies/rolling_peach.png"),
    /**
     * Represents the texture of the CryingOnion
     */
    CRYING_ONION("/textures/entities/enemies/crying_onion.png"),
    /**
     * Represents the texture of the MindPineapple
     */
    MIND_PINEAPPLE("/textures/entities/enemies/mind_pineapple.png"),
    /**
     * Represents the texture of the Nut
     */
    NUT("/textures/entities/enemies/nut.png"),
    /**
     * Represents the texture of the Pea
     */
    PEA("/textures/entities/enemies/pea.png"),
    /**
     * Represents the texture of the Slash
     */
    SLASH("/textures/entities/enemies/slash.png"),
    /**
     * Represents the texture of the spikes
     */
    SPIKES("/textures/entities/enemies/spikes.png"),
    /**
     * Represents the texture of the Missile.
     */
    MISSILE("/textures/entities/missile.png"),
    /**
     * Represents the texture of the coin.
     */
    COIN("/textures/entities/items/coin.png"),
    /**
     * Represents the texture of the jump boost
     */
    JUMP_BOOST("/textures/entities/items/jumpBoost.png"),
    /**
     * Represents the texture of the speed boost
     */
    SPEED_BOOST("/textures/entities/items/jumpBoost.png"),
    /**
     * Represents the texture of the speed boost
     */
    INVULNERABILITY_BOOST("/textures/entities/items/jumpBoost.png"),
    /**
     * Represents the texture of the chicken
     */
    CHICKEN("/textures/entities/items/chicken.png"),
    /**
     * Represents the texture of an explosion.
     */
    EXPLOSION("/textures/entities/explosion.png"),
    /**
     * Represents the texture of the fire.
     */
    FIRE("/textures/entities/fire.png"),
    /**
     * Represents the background of the menu
     */
    MENU_BACKGROUND("/textures/backgrounds/menu.png"),
    /**
     * Represents the background of the level selection menu
     */
    LEVEL_SELECTION_BACKGROUND("/textures/backgrounds/level_selection.png"),
    /**
     * Represents the background of the leaderboard menu
     */
    LEADERBOARD_BACKGROUND("/textures/backgrounds/leaderboard.png"),
    /**
     * Represents the background of the login view
     */
    LOGIN_BACKGROUND("/textures/backgrounds/login.png"),
    /**
     * Represents the background of the login view
     */
    SETTINGS_BACKGROUND("/textures/backgrounds/settings.png"),
    /**
     * Represents the background of the tutorial view
     */
    TUTORIAL_BACKGROUND("/textures/backgrounds/tutorial.png"),
    /**
     * Represents the background shown when you win a level
     */
    VICTORY_BACKGROUND("/textures/backgrounds/victory.png"),
    /**
     * Represents the background shown when you pause a level
     */
    PAUSE_BACKGROUND("/textures/backgrounds/pause.png"),
    /**
     * Represents the background shown when you pause a level
     */
    GAMEOVER_BACKGROUND("/textures/backgrounds/gameover.png"),
    /**
     * Represents the background shown when you pause a level
     */
    BRICKS_BACKGROUND("/textures/backgrounds/bricks.png"),
    /**
     * Represents the button used to access the first level
     */
    SUBMIT_BUTTON("/textures/buttons/submitButton/submit.png"),
    /**
     * Represents the button used to log out from the current user
     */
    LOGOUT_BUTTON("/textures/buttons/logout/logout.png"),
    /**
     * Represents the button used to log out from the current user while pressed
     */
    LOGOUT_BUTTON_PRESSED("/textures/buttons/logout/logout_pressed.png"),
    /**
     * Represents the button used to access the first level
     */
    SUBMIT_BUTTON_PRESSED("/textures/buttons/submitButton/submit_pressed.png"),
    /**
     * Represents the button used to increase the audio
     */
    PLUS_AUDIO_BUTTON("/textures/buttons/audio/plus.png"),
    /**
     * Represents the button used to increase the audio while pressed
     */
    PLUS_AUDIO_BUTTON_PRESSED("/textures/buttons/audio/plus_pressed.png"),
    /**
     * Represents the button used to decrease the audio
     */
    MINUS_AUDIO_BUTTON("/textures/buttons/audio/minus.png"),
    /**
     * Represents the button used to decrease the audio while pressed
     */
    MINUS_AUDIO_BUTTON_PRESSED("/textures/buttons/audio/minus_pressed.png"),
    /**
     * Represents the button used to access the first level
     */
    LEVEL_BUTTON_1("/textures/buttons/level/level1.png"),
    /**
     * Represents the button used to access the first level while pressed
     */
    LEVEL_BUTTON_1_PRESSED("/textures/buttons/level/level1_pressed.png"),
    /**
     * Represents the button used to access the second level
     */
    LEVEL_BUTTON_2("/textures/buttons/level/level2.png"),
    /**
     * Represents the button used to access the second level while pressed
     */
    LEVEL_BUTTON_2_PRESSED("/textures/buttons/level/level2_pressed.png"),
    /**
     * Represents the button used to access the third level
     */
    LEVEL_BUTTON_3("/textures/buttons/level/level3.png"),
    /**
     * Represents the button used to access the third level while pressed
     */
    LEVEL_BUTTON_3_PRESSED("/textures/buttons/level/level3_pressed.png"),
    /**
     * Represents the button used to access the boss level
     */
    LEVEL_BUTTON_BOSS("/textures/buttons/level/level_boss.png"),
    /**
     * Represents the button used to access the boss level while pressed
     */
    LEVEL_BUTTON_BOSS_PRESSED("/textures/buttons/level/level_boss_pressed.png"),
    /**
     * Represents the texture of the checkbox while {@link DeveloperMode} is turn onn
     */
    DEVELOPER_MODE_ON("/textures/buttons/developer_mode/on.png"),
    /**
     * Represents the texture of the checkbox while {@link DeveloperMode} is turn off
     */
    DEVELOPER_MODE_OFF("/textures/buttons/developer_mode/off.png"),
    /**
     * Represents the button used to access the main menu
     */
    HOME_BUTTON("/textures/buttons/home/home.png"),
    /**
     * Represents the button used to access the main menu while pressed
     */
    HOME_BUTTON_PRESSED("/textures/buttons/home/home_pressed.png"),
    /**
     * Represents the button used to access the main menu while hover it
     */
    HOME_BUTTON_HOVER("/textures/buttons/home/home_hover.png"),
    /**
     * Represents the button used to access the tutorial menu
     */
    TUTORIAL_BUTTON("/textures/buttons/tutorial/tutorial.png"),
    /**
     * Represents the button used to access the tutorial while pressed
     */
    TUTORIAL_BUTTON_PRESSED("/textures/buttons/tutorial/tutorial_pressed.png"),
    /**
     * Represents the button used to access the tutorial while hover it
     */
    TUTORIAL_BUTTON_HOVER("/textures/buttons/tutorial/tutorial_hover.png"),
    /**
     * Represents the level restart button
     */
    RESTART_BUTTON("/textures/buttons/restart/restart.png"),
    /**
     * Represents the level restart button while pressed
     */
    RESTART_BUTTON_PRESSED("/textures/buttons/restart/restart_pressed.png"),
    /**
     * Represents the level restart button while hover it
     */
    RESTART_BUTTON_HOVER("/textures/buttons/restart/restart_hover.png"),
    /**
     * Represents the level resume button
     */
    RESUME_BUTTON("/textures/buttons/resume/resume.png"),
    /**
     * Represents the level resume button while pressed
     */
    RESUME_BUTTON_PRESSED("/textures/buttons/resume/resume_pressed.png"),
    /**
     * Represents the level resume button while hover it
     */
    RESUME_BUTTON_HOVER("/textures/buttons/resume/resume_hover.png"),
    /**
     * Represents the menu play button
     */
    PLAY_BUTTON("/textures/buttons/play/play.png"),
    /**
     * Represents the menu play button while pressed
     */
    PLAY_BUTTON_PRESSED("/textures/buttons/play/play_pressed.png"),
    /**
     * Represents the settings button
     */
    SETTINGS_BUTTON("/textures/buttons/settings/setting.png"),
    /**
     * Represents the settings button while pressed
     */
    SETTINGS_BUTTON_HOVER("/textures/buttons/settings/settings_hover.png"),
    /**
     * Represents the settings button while the mouse is hover it
     */
    SETTINGS_BUTTON_PRESSED("/textures/buttons/settings/settings_pressed.png"),
    /**
     * Represents the quit button
     */
    QUIT_BUTTON("/textures/buttons/quit/quit.png"),
    /**
     * Represents the quit button while pressed
     */
    QUIT_BUTTON_PRESSED("/textures/buttons/quit/quit_pressed.png"),
    /**
     * Represents the leaderboard button
     */
    LEADERBOARD_BUTTON("/textures/buttons/leaderboard/leaderboard.png"),
    /**
     * Represents the leaderboard button while pressed
     */
    LEADERBOARD_BUTTON_PRESSED("/textures/buttons/leaderboard/leaderboard_pressed.png");

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
     *
     * @throws IOException              error while reading image
     * @throws IllegalArgumentException if the file is null
     * @throws NullPointerException     if the given file is null
     */
    public void load() throws IOException, IllegalArgumentException, NullPointerException {
        LOGGER.info("Loading texture {} from file {}...", this.name(), this.fileName);
        final InputStream is = getClass().getResourceAsStream(this.fileName);
        if (is == null) {
            throw new NullPointerException("File " + this.fileName + " does not exists!");
        }
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
