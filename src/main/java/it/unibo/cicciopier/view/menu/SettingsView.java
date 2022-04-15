package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.menu.MainMenuController;
import it.unibo.cicciopier.controller.menu.MenuAction;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.Sound;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.DeveloperMode;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.utility.Pair;
import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.buttons.Buttons;
import it.unibo.cicciopier.view.menu.buttons.CustomButton;
import it.unibo.cicciopier.view.menu.buttons.CustomCheckBox;
import it.unibo.cicciopier.view.menu.buttons.MenuActionButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * This class represents an instance of the settings view
 */
public class SettingsView extends JPanel implements MenuPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsView.class);
    private final JLabel gameAudio;
    private final JLabel musicAudio;
    private final CustomCheckBox developerMode;
    private final CustomButton plusSound;
    private final CustomButton minusSound;
    private final CustomButton plusMusic;
    private final CustomButton minusMusic;
    private final CustomButton logout;
    private final DefaultListModel<Dimension> resolutionSettingListModel = new DefaultListModel<>();
    private final JList<Dimension> jList;
    private final JScrollPane jScrollPane;
    private final JPanel panel;

    /**
     * This constructor creates the whole panel with his components
     *
     * @param mainMenuController the instance of the {@link MainMenuController}
     */
    public SettingsView(MainMenuController mainMenuController) {
        SettingsView.LOGGER.info("Initializing the class...");
        this.plusSound = new MenuActionButton(
                mainMenuController,
                Buttons.PLUS_GAME_AUDIO,
                MenuAction.INCREASE_GAME_AUDIO
        );
        this.minusSound = new MenuActionButton(
                mainMenuController,
                Buttons.MINUS_GAME_AUDIO,
                MenuAction.DECREASE_GAME_AUDIO
        );
        this.plusMusic = new MenuActionButton(
                mainMenuController,
                Buttons.PLUS_MUSIC_AUDIO,
                MenuAction.INCREASE_MUSIC_AUDIO
        );
        this.minusMusic = new MenuActionButton(
                mainMenuController,
                Buttons.MINUS_MUSIC_AUDIO,
                MenuAction.DECREASE_MUSIC_AUDIO
        );
        this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.jList = new JList<>(this.resolutionSettingListModel);
        this.jScrollPane = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.logout = new MenuActionButton(mainMenuController, Buttons.LOGOUT, MenuAction.LOGOUT);
        this.developerMode = new CustomCheckBox(Texture.DEVELOPER_MODE_OFF.getTexture(), Texture.DEVELOPER_MODE_ON.getTexture());
        this.gameAudio = new JLabel(Math.round(AudioController.getInstance().getSoundVolume() * 100) + "%");
        this.musicAudio = new JLabel(Math.round(AudioController.getInstance().getMusicVolume() * 100) + "%");
        this.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        SettingsView.LOGGER.info("Loading the class...");
        this.panel.setOpaque(false);
        this.resolutionSettingListModel.addAll(Screen.getResolutions());
        this.jList.setLayoutOrientation(JList.VERTICAL);
        this.jList.setOpaque(false);
        this.jList.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.jList.setVisibleRowCount(1);
        this.jList.setCellRenderer(new TransparentListCellRenderer(Level.FIRST_LEVEL));
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.getVerticalScrollBar().setBackground(new Color(210, 175, 128, 255));
        this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
            }
        });
        this.panel.add(this.jScrollPane);
        this.developerMode.setOpaque(false);
        this.developerMode.setPreferredSize(new Dimension(80, 40));
        this.developerMode.addItemListener(e -> {
            DeveloperMode.setActive(developerMode.isSelected());
            AudioController.getInstance().playSound(Sound.MAIN_BUTTON);
        });
        this.setLayout(null);
        this.add(this.panel);
        this.add(this.gameAudio);
        this.add(this.musicAudio);
        this.add(this.developerMode);
        this.add(this.plusSound);
        this.add(this.plusMusic);
        this.add(this.minusSound);
        this.add(this.minusMusic);
        this.add(this.logout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        SettingsView.LOGGER.info("Updating the class...");
        this.setPreferredSize(Screen.getCurrentDimension());

        this.panel.setPreferredSize(new Dimension((int) (225 * Screen.getScale()), (int) (105 * Screen.getScale())));
        this.jList.setFixedCellWidth(this.panel.getPreferredSize().width);
        this.jScrollPane.setPreferredSize(new Dimension(this.panel.getPreferredSize().width,
                this.panel.getPreferredSize().height));
        this.jList.setFont(CustomFont.getInstance().getFontOrDefault());
        this.gameAudio.setFont(CustomFont.getInstance().getFontOrDefault());
        this.musicAudio.setFont(CustomFont.getInstance().getFontOrDefault());
        this.gameAudio.setPreferredSize(new Dimension((int) (70 * Screen.getScale()), (int) (60 * Screen.getScale())));
        this.musicAudio.setPreferredSize(new Dimension((int) (70 * Screen.getScale()), (int) (60 * Screen.getScale())));
        final int audioWidthOffset = this.getPreferredSize().width / 2 - plusMusic.getPreferredSize().width;
        final int audioHeightOffset = (int) (this.getPreferredSize().height / 2 - minusMusic.getPreferredSize().height + this.getPreferredSize().height / 15.36);

        final Pair<Integer> plusSoundPos = new Pair<>(audioWidthOffset, audioHeightOffset);

        final Pair<Integer> plusMusicPos = new Pair<>(audioWidthOffset, (int) (audioHeightOffset + this.getPreferredSize().height / 12.8));

        final Pair<Integer> minusSoundPos = new Pair<>((int) (audioWidthOffset + this.getPreferredSize().width / 10.24), audioHeightOffset);

        final Pair<Integer> minusMusicPos = new Pair<>((int) (audioWidthOffset + this.getPreferredSize().width / 10.24),
                (int) (audioHeightOffset + this.getPreferredSize().height / 12.8));

        final Pair<Integer> logoutPos = new Pair<>(this.getPreferredSize().width / 2 + logout.getPreferredSize().width,
                (int) (audioHeightOffset + this.getPreferredSize().height / 3.66));

        final Pair<Integer> panelPos = new Pair<>(this.getPreferredSize().width / 2 - this.panel.getPreferredSize().width / 2,
                (int) (audioHeightOffset + this.getPreferredSize().height / 4.10));

        final Pair<Integer> gameAudioPos = new Pair<>((int) (plusSoundPos.getX() + (minusSoundPos.getX() - plusSoundPos.getX()) / 2 + plusSound.getPreferredSize().width - (musicAudio.getPreferredSize().width / 1.5)),
                audioHeightOffset);

        final Pair<Integer> musicAudioPos = new Pair<>(gameAudioPos.getX(),
                (int) (audioHeightOffset + this.getPreferredSize().height / 12.8));

        final Pair<Integer> developerModePos = new Pair<>(audioWidthOffset + this.getPreferredSize().width / 100,
                (int) (audioHeightOffset + this.getPreferredSize().height / 5.65));


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

        this.logout.setBounds(logoutPos.getX(), logoutPos.getY(), logout.getPreferredSize().width,
                logout.getPreferredSize().height);

        this.developerMode.setBounds(developerModePos.getX(), developerModePos.getY(),
                (int) (this.developerMode.getPreferredSize().width * Screen.getScale()),
                (int) (this.developerMode.getPreferredSize().height * Screen.getScale()));

        this.panel.setBounds(panelPos.getX(), panelPos.getY(), this.panel.getPreferredSize().width,
                this.panel.getPreferredSize().height);

        this.repaint();
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
        g.drawImage(
                Texture.SETTINGS_BACKGROUND.getTexture(),
                0,
                0,
                Screen.getCurrentDimension().width,
                Screen.getCurrentDimension().height,
                null
        );

    }

    public JList<Dimension> getList() {
        return jList;
    }
}
