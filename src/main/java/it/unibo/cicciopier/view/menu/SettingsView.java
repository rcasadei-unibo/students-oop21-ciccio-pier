package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.DeveloperMode;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.controller.menu.ViewPanels;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.MenuActionButton;
import it.unibo.cicciopier.view.menu.buttons.ViewPanelButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsView.class);
    private final JLabel gameAudio;
    private final JLabel musicAudio;
    private final JLabel loggedUser;
    private final MainMenuController mainMenuController;
    private final JCheckBox developerMode;
    private final CustomButton home;
    private final CustomButton plusSound;
    private final CustomButton minusSound;
    private final CustomButton plusMusic;
    private final CustomButton minusMusic;
    private final CustomButton logout;

    public SettingsView(MainMenuController mainMenuController) {
        SettingsView.LOGGER.info("Initializing the class...");
        this.mainMenuController = mainMenuController;
        this.loggedUser = new JLabel("Logged user: " + mainMenuController.getUsername());
        this.home = new ViewPanelButton(mainMenuController, Buttons.HOME, ViewPanels.MAIN_MENU);
        this.plusSound = new MenuActionButton(mainMenuController, Buttons.PLUS_GAME_AUDIO, MenuAction.INCREASE_GAME_AUDIO);
        this.minusSound = new MenuActionButton(mainMenuController, Buttons.MINUS_GAME_AUDIO, MenuAction.DECREASE_GAME_AUDIO);
        this.plusMusic = new MenuActionButton(mainMenuController, Buttons.PLUS_MUSIC_AUDIO, MenuAction.INCREASE_MUSIC_AUDIO);
        this.minusMusic = new MenuActionButton(mainMenuController, Buttons.MINUS_MUSIC_AUDIO, MenuAction.DECREASE_MUSIC_AUDIO);
        this.logout = new MenuActionButton(mainMenuController, Buttons.LOGOUT, MenuAction.LOGOUT);
        this.developerMode = new JCheckBox();
        this.gameAudio = new JLabel(Math.round(AudioController.getInstance().getSoundVolume() * 100) + "%");
        this.musicAudio = new JLabel(Math.round(AudioController.getInstance().getMusicVolume() * 100) + "%");

        this.load();


    }

    public void updateGameAudioText() {
        this.gameAudio.setText(Math.round(AudioController.getInstance().getSoundVolume() * 100) + "%");
        this.update();
    }

    public void updateMusicAudioText() {
        this.musicAudio.setText(Math.round(AudioController.getInstance().getMusicVolume() * 100) + "%");
        this.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Texture.SETTINGS_BACKGROUND.getTexture(), 0, 0, Screen.getCurrentDimension().width, Screen.getCurrentDimension().height, null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLoggedUser() {
        this.loggedUser.setText(("Logged user: " + this.mainMenuController.getUsername()));
        this.loggedUser.setBounds(this.loggedUser.getBounds().x, this.loggedUser.getBounds().y,
                this.loggedUser.getPreferredSize().width, this.loggedUser.getPreferredSize().height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimations() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        SettingsView.LOGGER.info("Updating the class...");
        this.setPreferredSize(Screen.getCurrentDimension());

        this.gameAudio.setFont(CustomFont.getInstance().getFontOrDefault());
        this.musicAudio.setFont(CustomFont.getInstance().getFontOrDefault());
        this.loggedUser.setFont(CustomFont.getInstance().getFontOrDefault());
        this.gameAudio.setPreferredSize(new Dimension((int) (70 * Screen.getScale()), (int) (60 * Screen.getScale())));
        this.musicAudio.setPreferredSize(new Dimension((int) (70 * Screen.getScale()), (int) (60 * Screen.getScale())));
        this.updateLoggedUser();
        final int audioWidthOffset = this.getPreferredSize().width / 2 - plusMusic.getPreferredSize().width;
        final int audioHeightOffset = (int) (this.getPreferredSize().height / 2 - minusMusic.getPreferredSize().height + this.getPreferredSize().height / 15.36);

        final Pair<Integer> homePos = new Pair<>((int) (this.getPreferredSize().width / 25.6),
                (int) (this.getPreferredSize().height / 38.4));

        final Pair<Integer> loggedUserPos = new Pair<>(homePos.getX(),
                (int) (homePos.getY() + home.getPreferredSize().height + this.getPreferredSize().height / 78.6));

        final Pair<Integer> plusSoundPos = new Pair<>(audioWidthOffset, audioHeightOffset);

        final Pair<Integer> plusMusicPos = new Pair<>(audioWidthOffset, (int) (audioHeightOffset + this.getPreferredSize().height / 12.8));

        final Pair<Integer> minusSoundPos = new Pair<>((int) (audioWidthOffset + this.getPreferredSize().width / 10.24), audioHeightOffset);

        final Pair<Integer> minusMusicPos = new Pair<>((int) (audioWidthOffset + this.getPreferredSize().width / 10.24),
                (int) (audioHeightOffset + this.getPreferredSize().height / 12.8));

        final Pair<Integer> logoutPos = new Pair<>(this.getPreferredSize().width / 2 - logout.getPreferredSize().width / 2,
                (int) (audioHeightOffset + this.getPreferredSize().height / 3.66));

        final Pair<Integer> gameAudioPos = new Pair<>((int) (plusSoundPos.getX() + (minusSoundPos.getX() - plusSoundPos.getX()) / 2 + plusSound.getPreferredSize().width - (musicAudio.getPreferredSize().width /1.5)),
                audioHeightOffset);

        final Pair<Integer> musicAudioPos = new Pair<>(gameAudioPos.getX(),
                (int) (audioHeightOffset + this.getPreferredSize().height / 12.8));

        final Pair<Integer> developerModePos = new Pair<>((int) (audioWidthOffset + this.getPreferredSize().width / 153.6),
                (int) (audioHeightOffset + this.getPreferredSize().height / 5.12));


        this.home.setBounds(homePos.getX(), homePos.getY(), home.getPreferredSize().width, home.getPreferredSize().height);

        this.plusSound.setBounds(plusSoundPos.getX(), plusSoundPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        this.plusMusic.setBounds(plusMusicPos.getX(), plusMusicPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        this.minusSound.setBounds(minusSoundPos.getX(), minusSoundPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        this.minusMusic.setBounds(minusMusicPos.getX(), minusMusicPos.getY(), plusSound.getPreferredSize().width,
                plusSound.getPreferredSize().height);

        this.gameAudio.setBounds(gameAudioPos.getX(), gameAudioPos.getY(), this.gameAudio.getPreferredSize().width,
                this.gameAudio.getPreferredSize().height);

        this.musicAudio.setBounds(musicAudioPos.getX(), musicAudioPos.getY(), this.musicAudio.getPreferredSize().width,
                this.musicAudio.getPreferredSize().height);

        this.loggedUser.setBounds(loggedUserPos.getX(), loggedUserPos.getY(), this.loggedUser.getPreferredSize().width,
                this.loggedUser.getPreferredSize().height);

        this.logout.setBounds(logoutPos.getX(), logoutPos.getY(), logout.getPreferredSize().width,
                logout.getPreferredSize().height);

        this.developerMode.setBounds(developerModePos.getX(), developerModePos.getY(),
                this.developerMode.getPreferredSize().width, this.developerMode.getPreferredSize().height);

        this.repaint();
    }

    @Override
    public void load() {
        SettingsView.LOGGER.info("Loading the class...");
        this.loggedUser.setForeground(Color.WHITE);
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
    }
}
