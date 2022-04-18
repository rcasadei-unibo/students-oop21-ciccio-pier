package it.unibo.cicciopier.controller.menu;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.Music;
import it.unibo.cicciopier.model.settings.User;
import it.unibo.cicciopier.model.settings.CustomFont;
import it.unibo.cicciopier.model.settings.Screen;
import it.unibo.cicciopier.model.settings.UsersFile;
import it.unibo.cicciopier.view.menu.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the controller of everything that regards the main menu and the users loading and managing
 */
public final class MainMenuController implements MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class);
    private static final int MAX_VOLUME = 1;
    private static final int MIN_VOLUME = 0;
    private final Gson gson;
    private final MenuManagerView menu;
    private final File usersFile;
    private File jarFolder;
    private User player;
    private List<User> users;
    private String username;

    /**
     * This constructor initializes the controller loading the users from json, initialize his variables,
     * starts the actual game by reproducing the {@link Music#BACKGROUND} and showing the initial login view
     */
    public MainMenuController() {
        MainMenuController.LOGGER.info("Initializing MainMenuController... ");
        this.gson = new Gson().newBuilder().serializeNulls().create();
        this.users = new ArrayList<>();
        try {
            this.jarFolder = new File(MainMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            MainMenuController.LOGGER.info("JarPath : " + this.jarFolder.getPath());
        } catch (URISyntaxException e) {
            MainMenuController.LOGGER.error("Jar folder not found!!!", e);
            System.exit(1);
        }
        this.usersFile = new File(this.jarFolder, "users.json");
        this.menu = new MenuManagerView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        this.loadUsers();
        try {
            LOGGER.info("Loading font...");
            CustomFont.getInstance().load();
            LOGGER.info("Font loaded successfully");
        } catch (IOException | FontFormatException e) {
            LOGGER.error("Error loading font!", e);
        }
        if (this.player == null) {
            this.show(ViewPanels.LOGIN);
        } else {
            this.loadPlayer();
            this.show(ViewPanels.HOME);
        }
        AudioController.getInstance().playMusic(Music.BACKGROUND);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(MenuAction menuAction) {
        switch (menuAction) {
            case INCREASE_MUSIC_AUDIO: {
                if (AudioController.getInstance().getMusicVolume() != MAX_VOLUME) {
                    AudioController.getInstance().setMusicVolume((float) ((Math.round(AudioController.getInstance().getMusicVolume() * 100) + 10)) / 100);
                    this.player.setMusicVolume((Math.round(AudioController.getInstance().getMusicVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateMusicAudioText();
                    MainMenuController.LOGGER.info("Music volume set to:" + AudioController.getInstance().getMusicVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_MUSIC_AUDIO: {
                if (AudioController.getInstance().getMusicVolume() != MIN_VOLUME) {
                    AudioController.getInstance().setMusicVolume((float) ((Math.round(AudioController.getInstance().getMusicVolume() * 100) - 10)) / 100);
                    this.player.setMusicVolume((Math.round(AudioController.getInstance().getMusicVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateMusicAudioText();
                    MainMenuController.LOGGER.info("Music volume set to:" + AudioController.getInstance().getMusicVolume() * 100 + "%");
                }
                break;
            }
            case INCREASE_GAME_AUDIO: {
                if (AudioController.getInstance().getSoundVolume() != MAX_VOLUME) {
                    AudioController.getInstance().setSoundVolume((float) ((Math.round(AudioController.getInstance().getSoundVolume() * 100) + 10)) / 100);
                    this.player.setSoundVolume((Math.round(AudioController.getInstance().getSoundVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateGameAudioText();
                    MainMenuController.LOGGER.info("Game volume set to:" + AudioController.getInstance().getSoundVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_GAME_AUDIO: {
                if (AudioController.getInstance().getSoundVolume() != MIN_VOLUME) {
                    AudioController.getInstance().setSoundVolume((float) ((Math.round(AudioController.getInstance().getSoundVolume() * 100) - 10)) / 100);
                    this.player.setSoundVolume((Math.round(AudioController.getInstance().getSoundVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateGameAudioText();
                    MainMenuController.LOGGER.info("Game volume set to:" + AudioController.getInstance().getSoundVolume() * 100 + "%");
                }
                break;
            }
            case QUIT: {
                quitAction();
            }
            case LOGIN: {
                if (!menu.getLoginView().getUsername().isBlank() && menu.getLoginView().getUsername().length() < 15) {
                    this.username = menu.getLoginView().getUsername().toLowerCase().trim();
                    this.player = this.users.stream().filter(user -> user.getUsername().equals(this.username)).findFirst().orElseGet(this::createUser);
                    MainMenuController.LOGGER.info("User logged in: " + this.player.getUsername());
                    this.loadPlayer();
                    this.show(ViewPanels.HOME);
                    this.updateUsers();
                    break;
                }
            }
            case LOGOUT: {
                MainMenuController.LOGGER.info("Logging out...");
                this.username = null;
                this.menu.getLoginView().logout();
                AudioController.getInstance().setMusicVolume(0.5F);
                AudioController.getInstance().setSoundVolume(0.5F);
                Screen.setCurrentDimension(Screen.getScreenMaxSize());
                this.show(ViewPanels.LOGIN);
                this.player = null;
                this.updateUsers();
                break;
            }
            case CHANGE_RESOLUTION: {
                LOGGER.info("Setting screen size to " + this.menu.getSettingsView().getList().getSelectedValue());
                Screen.setCurrentDimension(this.menu.getSettingsView().getList().getSelectedValue());
                this.player.setResolution(this.menu.getSettingsView().getList().getSelectedValue());
                this.updateUsers();
            }
        }
    }

    /**
     * This function creates a new {@link User} using default values and the username if is not found among the saved
     * users
     *
     * @return The user that was just created
     */
    private User createUser() {
        MainMenuController.LOGGER.info("Creating a new User: " + this.username);
        User newUser = new User(this.username);
        this.users.add(newUser);
        this.updateUsers();
        return newUser;
    }

    /**
     * This function is called whenever a change in a user is made and updates the json file with the new information
     */
    private void updateUsers() {
        try (FileWriter writer = new FileWriter(this.usersFile); JsonWriter jsonWriter = new JsonWriter(writer)) {
            final UsersFile usersFile = new UsersFile();
            usersFile.setUsers(this.users);
            usersFile.setLastUser(this.username);
            this.gson.toJson(usersFile, UsersFile.class, jsonWriter);
            MainMenuController.LOGGER.info("Successfully updated json object to file...!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void startLevel(final Level level) {
        LOGGER.info("Starting level...");

        try {
            //this.menu.setVisible(false);
            GameEngine gameEngine = new GameEngine(this, level);
            gameEngine.load();
            this.menu.setVisible(gameEngine);
            AudioController.getInstance().stopMusic();
            gameEngine.getMusic().ifPresent(m -> AudioController.getInstance().playMusic(m));
            gameEngine.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

    /**
     * This function is called to load the users from the json file and update their levels if they are missing using
     * {@link User#updateLevels()} called after any update to the users and after the creation of
     * {@link MainMenuController}
     */
    private void loadUsers() {
        LOGGER.info("Loading users file...");
        if (this.usersFile.getParentFile().mkdirs()) {
            LOGGER.info("Generated dir path for users file");
        }
        boolean isCreated = false;
        try {
            isCreated = this.usersFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isCreated) {
            try (FileReader reader = new FileReader(this.usersFile); JsonReader jsonReader = new JsonReader(reader)) {
                UsersFile usersFile = this.gson.fromJson(jsonReader, UsersFile.class);
                this.users = usersFile.getUsers();
                this.username = usersFile.getLastUser();
                this.player = this.users.stream().filter(user -> user.getUsername().equals(this.username)).findFirst().orElse(null);
                MainMenuController.LOGGER.info("Successfully loaded users...!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (this.users == null) {
                this.users = new ArrayList<>();
            }
            this.users.forEach(User::updateLevels);
        } else {
            LOGGER.info("Generated users file");
            this.users = new ArrayList<>();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show(final ViewPanels viewPanels) {
        LOGGER.info("Changing menu view to: " + viewPanels);
        this.menu.setVisible(viewPanels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quitAction() {
        LOGGER.info("Exiting...");
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endOfLevel(final int score, final GameState gameState, final Level level) {
        if (gameState == GameState.WON && player.getLevelScore(level.getJsonId()) < score) {
            player.setLevelScore(level.getJsonId(), score);
            updateUsers();
        }
        AudioController.getInstance().playMusic(Music.BACKGROUND);
        this.show(ViewPanels.LEVEL_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@inheritDoc}
     */
    public User getPlayer() {
        return player;
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers() {
        return new ArrayList<>(this.users);
    }

    /**
     * {@inheritDoc}
     */
    public MenuManagerView getMenu() {
        return this.menu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPlayer() {
        this.menu.getSettingsView().getList().setSelectedValue(this.player.getResolution(), true);
        Screen.setCurrentDimension(this.player.getResolution());
        AudioController.getInstance().setSoundVolume((float) this.player.getSoundVolume() / 100);
        this.menu.getSettingsView().updateGameAudioText();
        AudioController.getInstance().setMusicVolume((float) this.player.getMusicVolume() / 100);
        this.menu.getSettingsView().updateMusicAudioText();
    }
}