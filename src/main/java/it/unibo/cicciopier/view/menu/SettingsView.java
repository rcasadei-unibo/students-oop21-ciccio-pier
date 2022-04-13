package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.menu.DeveloperMode;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.MenuActionButton;
import it.unibo.cicciopier.view.menu.buttons.ViewPanelButton;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends JPanel {
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

        this.gameAudio = new JLabel(Math.round(AudioController.getInstance().getSoundVolume() * 100) + "%");
        this.musicAudio = new JLabel(Math.round(AudioController.getInstance().getMusicVolume() * 100) + "%");
        Font font = gameAudio.getFont().deriveFont(Font.BOLD, 25);
        this.gameAudio.setFont(font);
        this.musicAudio.setFont(font);
        this.gameAudio.setPreferredSize(new Dimension(70,60));
        this.musicAudio.setPreferredSize(new Dimension(70,60));

        this.loggedUser.setFont(loggedUser.getFont().deriveFont(Font.BOLD, 20));
        this.loggedUser.setForeground(Color.WHITE);

        Dimension size = new Dimension(1536, 768);
        this.setPreferredSize(size);

        this.developerMode.setIcon(new ImageIcon(Texture.DEVELOPER_MODE_OFF.getTexture()));
        this.developerMode.setSelectedIcon(new ImageIcon(Texture.DEVELOPER_MODE_ON.getTexture()));
        this.developerMode.setOpaque(false);
        this.developerMode.addItemListener(e -> {
            DeveloperMode.setActive(developerMode.isSelected());
            AudioController.getInstance().playSound(Sound.MAIN_BUTTON);
        });

        this.setLayout(null);
        this.add(this.gameAudio);
        this.add(this.musicAudio);
        this.add(this.loggedUser);
        this.add(this.developerMode);
        this.add(home);
        this.add(plusSound);
        this.add(plusMusic);
        this.add(minusSound);
        this.add(minusMusic);

        this.add(logout);

        final int audioWidthOffset = size.width / 2 - plusMusic.getPreferredSize().width;
        final int audioHeightOffset = (int) (size.height / 2 - minusMusic.getPreferredSize().height + size.height / 15.36);

        final Pair<Integer> homePos = new Pair<>((int) (size.width / 25.6),
                (int) (size.height / 38.4));

        final Pair<Integer> loggedUserPos = new Pair<>(homePos.getX(),
                (int) (homePos.getY() + home.getPreferredSize().height + size.height / 78.6));

        final Pair<Integer> plusSoundPos = new Pair<>(audioWidthOffset, audioHeightOffset);

        final Pair<Integer> plusMusicPos = new Pair<>(audioWidthOffset, (int) (audioHeightOffset + size.height / 12.8));

        final Pair<Integer> minusSoundPos = new Pair<>((int) (audioWidthOffset + size.width / 10.24), audioHeightOffset);

        final Pair<Integer> minusMusicPos = new Pair<>((int) (audioWidthOffset + size.width / 10.24),
                (int) (audioHeightOffset + size.height / 12.8));

        final Pair<Integer> logoutPos = new Pair<>(size.width / 2 - logout.getPreferredSize().width / 2,
                (int) (audioHeightOffset + size.height / 3.66));

        final Pair<Integer> gameAudioPos = new Pair<>((int) (plusSoundPos.getX() + (minusSoundPos.getX() - plusSoundPos.getX()) / 2 + plusSound.getPreferredSize().width - (musicAudio.getPreferredSize().width/1.5)),
                audioHeightOffset);

        final Pair<Integer> musicAudioPos = new Pair<>(gameAudioPos.getX(),
                (int) (audioHeightOffset + size.height / 12.8));

        final Pair<Integer> developerModePos = new Pair<>((int) (audioWidthOffset + size.width/153.6),
                (int) (audioHeightOffset + size.height / 5.12));


        home.setBounds(homePos.getX(), homePos.getY(), home.getPreferredSize().width, home.getPreferredSize().height);

        plusSound.setBounds(plusSoundPos.getX(), plusSoundPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        plusMusic.setBounds(plusMusicPos.getX(), plusMusicPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        minusSound.setBounds(minusSoundPos.getX(), minusSoundPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        minusMusic.setBounds(minusMusicPos.getX(), minusMusicPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        this.gameAudio.setBounds(gameAudioPos.getX(), gameAudioPos.getY(), this.gameAudio.getPreferredSize().width,
                this.gameAudio.getPreferredSize().height);

        this.musicAudio.setBounds(musicAudioPos.getX(), musicAudioPos.getY(), this.musicAudio.getPreferredSize().width,
                this.musicAudio.getPreferredSize().height);

        this.loggedUser.setBounds(loggedUserPos.getX(), loggedUserPos.getY(), this.loggedUser.getPreferredSize().width,
                this.loggedUser.getPreferredSize().height);

        logout.setBounds(logoutPos.getX(), logoutPos.getY(), logout.getPreferredSize().width,
                logout.getPreferredSize().height);

        this.developerMode.setBounds(developerModePos.getX(), developerModePos.getY(),
                this.developerMode.getPreferredSize().width, this.developerMode.getPreferredSize().height);

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
        g.drawImage(Texture.SETTINGS_BACKGROUND.getTexture(), 0, 0, null);
    }

    public void updateLoggedUser() {
        this.loggedUser.setText("Logged user: " + this.mainMenuController.getUsername());
        this.loggedUser.setBounds(loggedUser.getBounds().x,loggedUser.getBounds().y,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);}
}
