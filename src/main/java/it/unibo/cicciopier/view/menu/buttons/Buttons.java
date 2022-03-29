package it.unibo.cicciopier.view.menu.buttons;

import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.ViewPanels;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Buttons {
    /**
     * An implementation of the Texture {@link Texture#PLAY_BUTTON}
     */
    PLAY(new Dimension(280, 106), new BufferedImage[]{Texture.PLAY_BUTTON.getTexture(), Texture.PLAY_BUTTON_PRESSED.getTexture()}, MenuAction.SHOW, false, ViewPanels.LEVEL_SELECTION, null),
    /**
     * An implementation of the Texture {@link Texture#LEADERBOARD_BUTTON}
     */
    LEADERBOARD(new Dimension(280, 106), new BufferedImage[]{Texture.LEADERBOARD_BUTTON.getTexture(), Texture.LEADERBOARD_BUTTON_PRESSED.getTexture()}, MenuAction.SHOW, false, ViewPanels.LEADERBOARD, null),
    /**
     * An implementation of the Texture {@link Texture#QUIT_BUTTON}
     */
    QUIT(new Dimension(280, 106), new BufferedImage[]{Texture.QUIT_BUTTON.getTexture(), Texture.QUIT_BUTTON_PRESSED.getTexture()}, MenuAction.QUIT, false, null, null),
    /**
     * An implementation of the Texture {@link Texture#HOME_BUTTON}
     */
    HOME(new Dimension(85, 85), new BufferedImage[]{Texture.HOME_BUTTON.getTexture(), Texture.HOME_BUTTON_PRESSED.getTexture(), Texture.HOME_BUTTON_HOVER.getTexture()}, MenuAction.SHOW, true, ViewPanels.MAIN_MENU, null),
    /**
     * An implementation of the Texture {@link Texture#SETTINGS_BUTTON}
     */
    SETTINGS(new Dimension(85, 85), new BufferedImage[]{Texture.SETTINGS_BUTTON.getTexture(), Texture.SETTINGS_BUTTON_PRESSED.getTexture(), Texture.SETTINGS_BUTTON_HOVER.getTexture()}, MenuAction.SHOW, true, ViewPanels.SETTINGS, null),
    /**
     * An implementation of the Texture {@link Texture#SUBMIT_BUTTON}
     */
    SUBMIT(new Dimension(150, 57), new BufferedImage[]{Texture.SUBMIT_BUTTON.getTexture(), Texture.SUBMIT_BUTTON_PRESSED.getTexture()}, MenuAction.LOGIN, false, null, null),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_1}
     */
    LEVEL1(new Dimension(90, 90), new BufferedImage[]{Texture.LEVEL_BUTTON_1.getTexture(), Texture.LEVEL_BUTTON_1_PRESSED.getTexture()}, MenuAction.PLAY_LEVEL, false, null, new GameEngine("level-1-1.tmx")),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_2}
     */
    LEVEL2(new Dimension(90, 90), new BufferedImage[]{Texture.LEVEL_BUTTON_2.getTexture(), Texture.LEVEL_BUTTON_2_PRESSED.getTexture()}, MenuAction.PLAY_LEVEL, false, null, new GameEngine("level-1-1.tmx")),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_3}
     */
    LEVEL3(new Dimension(90, 90), new BufferedImage[]{Texture.LEVEL_BUTTON_3.getTexture(), Texture.LEVEL_BUTTON_3_PRESSED.getTexture()}, MenuAction.PLAY_LEVEL, false, null, new GameEngine("level-1-1.tmx")),
    /**
     * An implementation of the Texture {@link Texture#LEVEL_BUTTON_BOSS}
     */
    LEVEL_BOSS(new Dimension(90, 90), new BufferedImage[]{Texture.LEVEL_BUTTON_BOSS.getTexture(), Texture.LEVEL_BUTTON_BOSS_PRESSED.getTexture()}, MenuAction.PLAY_LEVEL, false, null, new GameEngine("level-1-1.tmx")),
    /**
     * An implementation of the Texture {@link Texture#PLUS_AUDIO_BUTTON} for the game audio
     */
    PLUS_GAME_AUDIO(new Dimension(50, 57), new BufferedImage[]{Texture.PLUS_AUDIO_BUTTON.getTexture(), Texture.PLUS_AUDIO_BUTTON_PRESSED.getTexture()}, MenuAction.INCREASE_GAME_AUDIO, false, null, null),
    /**
     * An implementation of the Texture {@link Texture#MINUS_AUDIO_BUTTON} for the game audio
     */
    MINUS_GAME_AUDIO(new Dimension(50, 57), new BufferedImage[]{Texture.MINUS_AUDIO_BUTTON.getTexture(), Texture.MINUS_AUDIO_BUTTON_PRESSED.getTexture()}, MenuAction.DECREASE_GAME_AUDIO, false, null, null),
    /**
     * An implementation of the Texture {@link Texture#PLUS_AUDIO_BUTTON} for the music audio
     */
    PLUS_MUSIC_AUDIO(new Dimension(50, 57), new BufferedImage[]{Texture.PLUS_AUDIO_BUTTON.getTexture(), Texture.PLUS_AUDIO_BUTTON_PRESSED.getTexture()}, MenuAction.INCREASE_MUSIC_AUDIO, false, null, null),
    /**
     * An implementation of the Texture {@link Texture#MINUS_AUDIO_BUTTON} for the music audio
     */
    MINUS_MUSIC_AUDIO(new Dimension(50, 57), new BufferedImage[]{Texture.MINUS_AUDIO_BUTTON.getTexture(), Texture.MINUS_AUDIO_BUTTON_PRESSED.getTexture()}, MenuAction.DECREASE_MUSIC_AUDIO, false, null, null);


    final Dimension dimension;
    final BufferedImage[] bufferedImage;
    final MenuAction menuAction;
    final boolean hasHover;
    final ViewPanels viewPanels;
    final GameEngine gameEngine;


    Buttons(Dimension dimension, BufferedImage[] bufferedImage, MenuAction menuAction, boolean hasHover, ViewPanels viewPanels, GameEngine gameEngine) {
        this.dimension = dimension;
        this.bufferedImage = bufferedImage;
        this.menuAction = menuAction;
        this.hasHover = hasHover;
        this.viewPanels = viewPanels;
        this.gameEngine = gameEngine;
    }
}
