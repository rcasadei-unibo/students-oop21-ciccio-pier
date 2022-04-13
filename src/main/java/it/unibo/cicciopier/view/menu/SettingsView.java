package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.MenuActionButton;
import it.unibo.cicciopier.view.menu.buttons.ViewPanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsView extends JPanel {
    private final BufferedImage background;
    private final JLabel gameAudio;
    private final JLabel musicAudio;
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;
    private final JCheckBox developerMode;

    public SettingsView(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;

        this.loggedUser = new JLabel("Logged user: " + mainMenuController.getUsername());

        CustomButton home = new ViewPanelButton(mainMenuController, Buttons.HOME, ViewPanels.MAIN_MENU);

        CustomButton plusSound = new MenuActionButton(mainMenuController, Buttons.PLUS_GAME_AUDIO, MenuAction.INCREASE_GAME_AUDIO);

        CustomButton minusSound = new MenuActionButton(mainMenuController, Buttons.MINUS_GAME_AUDIO, MenuAction.DECREASE_GAME_AUDIO);

        CustomButton plusMusic = new MenuActionButton(mainMenuController, Buttons.PLUS_MUSIC_AUDIO, MenuAction.INCREASE_MUSIC_AUDIO);

        CustomButton minusMusic = new MenuActionButton(mainMenuController, Buttons.MINUS_MUSIC_AUDIO, MenuAction.DECREASE_MUSIC_AUDIO);

        CustomButton logout = new MenuActionButton(mainMenuController, Buttons.LOGOUT, MenuAction.LOGOUT);

        this.developerMode = new JCheckBox();

        this.developerMode.setSize(new Dimension(32,32));

        this.gameAudio = new JLabel(Math.round(AudioController.getInstance().getSoundVolume() * 100) + "%");
        this.musicAudio = new JLabel(Math.round(AudioController.getInstance().getMusicVolume() * 100) + "%");
        Font font = gameAudio.getFont().deriveFont(Font.BOLD, 25);
        gameAudio.setFont(font);
        musicAudio.setFont(font);

        this.loggedUser.setFont(loggedUser.getFont().deriveFont(Font.BOLD, 20));
        this.loggedUser.setForeground(Color.WHITE);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);
        this.background = Texture.SETTINGS_BACKGROUND.getTexture();

        this.setLayout(null);
        this.add(home);
        this.add(this.developerMode);
        this.add(plusSound);
        this.add(plusMusic);
        this.add(minusSound);
        this.add(minusMusic);
        this.add(this.gameAudio);
        this.add(this.musicAudio);
        this.add(this.loggedUser);
        this.add(logout);

        final Dimension sizeHome = home.getPreferredSize();
        final int homeWidthOffset = 60;
        final int settingsHeightOffset = 20;
        final int audioWidthOffset = size.width / 2 - plusMusic.getPreferredSize().width;
        final int audioHeightOffset = size.height / 2 - minusMusic.getPreferredSize().height + 50;
        final int logoutWidthOffset = size.width / 2 - logout.getPreferredSize().width / 2;
        final int logoutHeightOffset = audioHeightOffset + 210;

        home.setBounds(homeWidthOffset, settingsHeightOffset, sizeHome.width, sizeHome.height);
        plusSound.setBounds(audioWidthOffset, audioHeightOffset, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);
        plusMusic.setBounds(audioWidthOffset, audioHeightOffset + 60, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);
        minusSound.setBounds(audioWidthOffset + 150, audioHeightOffset, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);
        minusMusic.setBounds(audioWidthOffset + 150, audioHeightOffset + 60, plusSound.getPreferredSize().width, plusSound.getPreferredSize().height);
        this.gameAudio.setBounds(minusSound.getBounds().x - (minusSound.getBounds().x - plusSound.getBounds().x - plusSound.getPreferredSize().width) + 27, audioHeightOffset, 80, 50);
        this.musicAudio.setBounds(minusSound.getBounds().x - (minusSound.getBounds().x - plusSound.getBounds().x - plusSound.getPreferredSize().width) + 27, audioHeightOffset + 60, 80, 50);
        this.loggedUser.setBounds(homeWidthOffset, settingsHeightOffset + sizeHome.height + 10, 300, 30);
        logout.setBounds(logoutWidthOffset, logoutHeightOffset, logout.getPreferredSize().width, logout.getPreferredSize().height);
        developerMode.setBounds(audioWidthOffset + 20, audioHeightOffset + 150, 32,32);

    }

    public void updateGameAudioText() {
        this.gameAudio.setText(Math.round(AudioController.getInstance().getSoundVolume() * 100) + "%");
        this.gameAudio.repaint();
    }

    public void updateMusicAudioText() {
        this.musicAudio.setText(Math.round(AudioController.getInstance().getMusicVolume() * 100) + "%");
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

    public void updateLoggedUser() {
        this.loggedUser.setText("Logged user: " + this.mainMenuController.getUsername());
        this.loggedUser.repaint();
    }

    public boolean getDeveloperMode(){
        return this.developerMode.isSelected();
    }

}
