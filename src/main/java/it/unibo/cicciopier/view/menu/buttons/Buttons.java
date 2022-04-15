package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.view.Texture;

import java.awt.image.BufferedImage;

/**
 * This enum define all the different button types with all their parameters
 */
public enum Buttons {
    /**
     * An implementation of the Texture {@link Texture#PLAY_BUTTON}
     */
    PLAY(new BufferedImage[]{Texture.PLAY_BUTTON.getTexture(), Texture.PLAY_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#LEADERBOARD_BUTTON}
     */
    LEADERBOARD(new BufferedImage[]{Texture.LEADERBOARD_BUTTON.getTexture(), Texture.LEADERBOARD_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#QUIT_BUTTON}
     */
    QUIT(new BufferedImage[]{Texture.QUIT_BUTTON.getTexture(), Texture.QUIT_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#HOME_BUTTON}
     */
    HOME(new BufferedImage[]{Texture.HOME_BUTTON.getTexture(), Texture.HOME_BUTTON_PRESSED.getTexture(), Texture.HOME_BUTTON_HOVER.getTexture()}, true),
    /**
     * An implementation of the Texture {@link Texture#SETTINGS_BUTTON}
     */
    SETTINGS(new BufferedImage[]{Texture.SETTINGS_BUTTON.getTexture(), Texture.SETTINGS_BUTTON_PRESSED.getTexture(), Texture.SETTINGS_BUTTON_HOVER.getTexture()}, true),
    /**
     * An implementation of the Texture {@link Texture#SUBMIT_BUTTON}
     */
    SUBMIT(new BufferedImage[]{Texture.SUBMIT_BUTTON.getTexture(), Texture.SUBMIT_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#SUBMIT_BUTTON}
     */
    LOGOUT(new BufferedImage[]{Texture.LOGOUT_BUTTON.getTexture(), Texture.LOGOUT_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_1}
     */
    LEVEL1(new BufferedImage[]{Texture.LEVEL_BUTTON_1.getTexture(), Texture.LEVEL_BUTTON_1_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_2}
     */
    LEVEL2(new BufferedImage[]{Texture.LEVEL_BUTTON_2.getTexture(), Texture.LEVEL_BUTTON_2_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_3}
     */
    LEVEL3(new BufferedImage[]{Texture.LEVEL_BUTTON_3.getTexture(), Texture.LEVEL_BUTTON_3_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_BOSS}
     */
    LEVEL_BOSS(new BufferedImage[]{Texture.LEVEL_BUTTON_BOSS.getTexture(), Texture.LEVEL_BUTTON_BOSS_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#PLUS_AUDIO_BUTTON} for the game audio
     */
    PLUS_GAME_AUDIO(new BufferedImage[]{Texture.PLUS_AUDIO_BUTTON.getTexture(), Texture.PLUS_AUDIO_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#MINUS_AUDIO_BUTTON} for the game audio
     */
    MINUS_GAME_AUDIO(new BufferedImage[]{Texture.MINUS_AUDIO_BUTTON.getTexture(), Texture.MINUS_AUDIO_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#PLUS_AUDIO_BUTTON} for the music audio
     */
    PLUS_MUSIC_AUDIO(new BufferedImage[]{Texture.PLUS_AUDIO_BUTTON.getTexture(), Texture.PLUS_AUDIO_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#MINUS_AUDIO_BUTTON} for the music audio
     */
    MINUS_MUSIC_AUDIO(new BufferedImage[]{Texture.MINUS_AUDIO_BUTTON.getTexture(), Texture.MINUS_AUDIO_BUTTON_PRESSED.getTexture()}, false),
    /**
     * An implementation of the Texture {@link Texture#RESUME_BUTTON} for the music audio
     */
    RESUME(new BufferedImage[]{Texture.RESUME_BUTTON.getTexture(), Texture.RESUME_BUTTON_PRESSED.getTexture(), Texture.RESUME_BUTTON_HOVER.getTexture()}, true),
    /**
     * An implementation of the Texture {@link Texture#TUTORIAL_BUTTON} for the tutorial view
     */
    TUTORIAL(new BufferedImage[]{Texture.TUTORIAL_BUTTON.getTexture(), Texture.TUTORIAL_BUTTON_PRESSED.getTexture(), Texture.TUTORIAL_BUTTON_HOVER.getTexture()}, true),
    /**
     * An implementation of the Texture {@link Texture#RESTART_BUTTON} for the music audio
     */
    RESTART(new BufferedImage[]{Texture.RESTART_BUTTON.getTexture(), Texture.RESTART_BUTTON_PRESSED.getTexture(), Texture.RESTART_BUTTON_HOVER.getTexture()}, true);

    private final BufferedImage[] textures;
    private final boolean hover;

    /**
     * This constructor instance a new button type
     *
     * @param textures represent the texture of a button
     * @param hover    define if a button has a hover animation
     */
    Buttons(final BufferedImage[] textures, final boolean hover) {
        this.textures = textures;
        this.hover = hover;
    }

    /**
     * Get the button textures
     *
     * @return the textures
     */
    public BufferedImage[] getTextures() {
        return this.textures;
    }

    /**
     * Get if the button has a hover animation
     *
     * @return true if the button has an hover animation, false otherwise
     */
    public boolean hasHover() {
        return this.hover;
    }
}
