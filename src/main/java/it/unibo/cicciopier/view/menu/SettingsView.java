package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsView extends JPanel {
    private final BufferedImage background;
    private final JLabel gameAudio;
    private final JLabel musicAudio;

    public SettingsView(MainMenuController mainMenuController) {

        BufferedImage[] bufferedImage = new BufferedImage[3];
        bufferedImage[0] = Texture.HOME_BUTTON.getTexture();
        bufferedImage[1] = Texture.HOME_BUTTON_PRESSED.getTexture();
        bufferedImage[2] = Texture.HOME_BUTTON_HOVER.getTexture();
        CustomButton home = new CustomButton(mainMenuController, new Dimension(85, 85), bufferedImage, MenuAction.SHOW, true, ViewPanels.MAIN_MENU);

        bufferedImage = new BufferedImage[2];
        bufferedImage[0] = Texture.PLUS_AUDIO_BUTTON.getTexture();
        bufferedImage[1] = Texture.PLUS_AUDIO_BUTTON_PRESSED.getTexture();
        CustomButton plusSound = new CustomButton(mainMenuController, new Dimension(50, 57), bufferedImage, MenuAction.INCREASE_GAME_AUDIO, false);

        bufferedImage = new BufferedImage[2];
        bufferedImage[0] = Texture.MINUS_AUDIO_BUTTON.getTexture();
        bufferedImage[1] = Texture.MINUS_AUDIO_BUTTON_PRESSED.getTexture();
        CustomButton minusSound = new CustomButton(mainMenuController, new Dimension(50, 57), bufferedImage, MenuAction.DECREASE_GAME_AUDIO, false);

        bufferedImage = new BufferedImage[2];
        bufferedImage[0] = Texture.PLUS_AUDIO_BUTTON.getTexture();
        bufferedImage[1] = Texture.PLUS_AUDIO_BUTTON_PRESSED.getTexture();
        CustomButton plusMusic = new CustomButton(mainMenuController, new Dimension(50, 57), bufferedImage, MenuAction.INCREASE_MUSIC_AUDIO, false);

        bufferedImage = new BufferedImage[2];
        bufferedImage[0] = Texture.MINUS_AUDIO_BUTTON.getTexture();
        bufferedImage[1] = Texture.MINUS_AUDIO_BUTTON_PRESSED.getTexture();
        CustomButton minusMusic = new CustomButton(mainMenuController, new Dimension(50, 57), bufferedImage, MenuAction.DECREASE_MUSIC_AUDIO, false);

        this.gameAudio = new JLabel(Math.round(AudioController.getAudioController().getSoundVolume() * 100) + "%");
        this.musicAudio = new JLabel(Math.round(AudioController.getAudioController().getMusicVolume() * 100) + "%");
        Font font = gameAudio.getFont().deriveFont(Font.BOLD,25);
        gameAudio.setFont(font);
        musicAudio.setFont(font);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.SETTINGS_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(plusSound);
        this.add(plusMusic);
        this.add(minusSound);
        this.add(minusMusic);
        this.add(gameAudio);
        this.add(musicAudio);

        final Dimension sizeHome = home.getPreferredSize();
        final int homeWidthOffset = 60;
        final int settingsHeightOffset = 20;
        final int audioWidthOffset = size.width / 2 - plusMusic.getPreferredSize().width;
        final int audioHeightOffset = size.height / 2 - minusMusic.getPreferredSize().height + 50;

        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeHome.width, sizeHome.height);
        plusSound.setBounds(audioWidthOffset, audioHeightOffset, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);
        plusMusic.setBounds(audioWidthOffset, audioHeightOffset + 60, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);
        minusSound.setBounds(audioWidthOffset + 150, audioHeightOffset, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);
        minusMusic.setBounds(audioWidthOffset + 150, audioHeightOffset + 60, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);

        //TODO fix how the distance is calculated
        gameAudio.setBounds (minusSound.getBounds().x - (minusSound.getBounds().x - plusSound.getBounds().x -plusSound.getPreferredSize().width) + 20 , audioHeightOffset, 80, 50);
        musicAudio.setBounds(minusSound.getBounds().x - (minusSound.getBounds().x - plusSound.getBounds().x -plusSound.getPreferredSize().width) + 20 , audioHeightOffset + 60, 80, 50);
    }

    public void updateGameAudioText(){
        this.gameAudio.setText(Math.round(AudioController.getAudioController().getSoundVolume() * 100) + "%");
        this.gameAudio.repaint();
    }

    public void updateMusicAudioText(){
        this.musicAudio.setText(Math.round(AudioController.getAudioController().getMusicVolume() * 100) + "%");
        this.musicAudio.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

}
