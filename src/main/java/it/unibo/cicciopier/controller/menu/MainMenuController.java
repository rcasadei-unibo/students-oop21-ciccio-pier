package it.unibo.cicciopier.controller.menu;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.unibo.cicciopier.controller.AudioController;
import it.unibo.cicciopier.controller.GameEngine;
import it.unibo.cicciopier.controller.GameState;
import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.Music;
import it.unibo.cicciopier.model.User;
import it.unibo.cicciopier.view.menu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public final class MainMenuController implements MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class);
    private static final int MAX_VOLUME = 1;
    private static final int MIN_VOLUME = 0;
    public static boolean isDeveloperModeOn = false;
    private final Gson gson;
    private final MenuManagerView menu;
    private final File users_file;
    private User player;
    private List<User> users;
    private String username;

    public MainMenuController() throws URISyntaxException {
        //Testing purpose
        this.gson = new Gson().newBuilder().serializeNulls().create();
        this.users = new ArrayList<>();
        this.users_file = new File("src/main/resources/users/users.json");
        this.loadUsers();
        this.menu = new MenuManagerView(this);
        AudioController.getAudioController().playMusic(Music.BACKGROUND);
        this.show(ViewPanels.LOGIN);
    }

    public void action(MenuAction menuAction) {
        switch (menuAction) {
            case INCREASE_MUSIC_AUDIO: {
                if (AudioController.getAudioController().getMusicVolume() != MAX_VOLUME) {
                    AudioController.getAudioController().setMusicVolume((float) ((Math.round(AudioController.getAudioController().getMusicVolume() * 100) + 10)) / 100);
                    this.player.setMusicVolume((Math.round(AudioController.getAudioController().getMusicVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateMusicAudioText();
                    LOGGER.info("Music volume set to:" + AudioController.getAudioController().getMusicVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_MUSIC_AUDIO: {
                if (AudioController.getAudioController().getMusicVolume() != MIN_VOLUME) {
                    AudioController.getAudioController().setMusicVolume((float) ((Math.round(AudioController.getAudioController().getMusicVolume() * 100) - 10)) / 100);
                    this.player.setMusicVolume((Math.round(AudioController.getAudioController().getMusicVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateMusicAudioText();
                    LOGGER.info("Music volume set to:" + AudioController.getAudioController().getMusicVolume() * 100 + "%");
                }
                break;
            }
            case INCREASE_GAME_AUDIO: {
                if (AudioController.getAudioController().getSoundVolume() != MAX_VOLUME) {
                    AudioController.getAudioController().setSoundVolume((float) ((Math.round(AudioController.getAudioController().getSoundVolume() * 100) + 10)) / 100);
                    this.player.setSoundVolume((Math.round(AudioController.getAudioController().getSoundVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateGameAudioText();
                    LOGGER.info("Game volume set to:" + AudioController.getAudioController().getSoundVolume() * 100 + "%");
                }
                break;
            }
            case DECREASE_GAME_AUDIO: {
                if (AudioController.getAudioController().getSoundVolume() != MIN_VOLUME) {
                    AudioController.getAudioController().setSoundVolume((float) ((Math.round(AudioController.getAudioController().getSoundVolume() * 100) - 10)) / 100);
                    this.player.setSoundVolume((Math.round(AudioController.getAudioController().getSoundVolume() * 100)));
                    this.updateUsers();
                    this.menu.getSettingsView().updateGameAudioText();
                    LOGGER.info("Game volume set to:" + AudioController.getAudioController().getSoundVolume() * 100 + "%");
                }
                break;
            }
            case QUIT: {
                quitAction();
            }
            case LOGIN: {
                if (!menu.getLoginView().getUsername().isBlank()) {
                    this.username = menu.getLoginView().getUsername().toLowerCase().trim();
                    LOGGER.info("Username: " + this.username);
                    LOGGER.info("Users: " + this.users);
                    this.player = this.users.stream().filter(user -> user.getUsername().equals(this.username)).findFirst().orElseGet(this::createUser);


                    AudioController.getAudioController().setSoundVolume((float) this.player.getSoundVolume() / 100);
                    this.menu.getSettingsView().updateGameAudioText();
                    AudioController.getAudioController().setMusicVolume((float) this.player.getMusicVolume() / 100);
                    this.menu.getSettingsView().updateMusicAudioText();


                    this.show(ViewPanels.MAIN_MENU);
                    break;
                }
            }
            case LOGOUT: {
                this.username = null;
                this.menu.getLoginView().logout();
                this.show(ViewPanels.LOGIN);

            }
        }
    }


    private User createUser() {

        LOGGER.info("Creating a new User: " + this.username);
        User newUser = new User(this.username);
        this.users.add(newUser);
        this.updateUsers();
        return newUser;
    }

    private void updateUsers() {
        try (FileWriter writer = new FileWriter(this.users_file); JsonWriter jsonWriter = new JsonWriter(writer)) {
            this.gson.toJson(users, List.class, jsonWriter);
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startLevel(final Level level) {
        LOGGER.info("Starting level...");

        try {
            AudioController.getAudioController().stopMusic(Music.BACKGROUND);
            this.menu.setVisible(false);
            AudioController.getAudioController().playMusic(Music.GAME);
            GameEngine gameEngine = new GameEngine(this, level);
            gameEngine.load();
            gameEngine.start();
        } catch (Exception e) {
            LOGGER.error("Error starting game...", e);
            System.exit(1);
        }
    }

    private void loadUsers() {
        try (FileReader reader = new FileReader(this.users_file); JsonReader jsonReader = new JsonReader(reader)) {
            this.users = this.gson.fromJson(jsonReader, new TypeToken<List<User>>() {
            }.getType());
            System.out.println("Successfully loaded users...!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.users == null) this.users = new ArrayList<>();
        this.users.forEach(User::updatelevels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show(ViewPanels viewPanels) {
        LOGGER.info("Changing menu view to: " + viewPanels);
        this.menu.setVisible(viewPanels);
    }

    public void quitAction() {
        LOGGER.info("Exiting...");
        System.exit(0);
    }

    public void endOfLevel(int score, GameState gameState, Level level){
        if (gameState == GameState.WON && player.getLevelScore(level.getJsonId()) < score){
           player.setLevelScore(level.getJsonId(),score);
           updateUsers();
        }
        AudioController.getAudioController().stopMusic(Music.GAME);
        AudioController.getAudioController().playMusic(Music.BACKGROUND);
        this.menu.setVisible(true);


    }

    public String getUsername() {
        return username;
    }

    public User getPlayer() {
        return player;
    }

    public List<User> getUsers() {
        return users;
    }

    public MenuManagerView getMenu(){
        return this.menu;
    }
}