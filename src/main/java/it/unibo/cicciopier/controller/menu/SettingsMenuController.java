package it.unibo.cicciopier.controller.menu;

import com.sun.tools.javac.Main;
import it.unibo.cicciopier.view.menu.SettingsView;

public class SettingsMenuController implements MenuController {
    private final MainMenuController menuController;
    private final SettingsView settingsView;

    SettingsMenuController(MainMenuController mainMenuController){
        this.menuController = mainMenuController;
        this.settingsView = new SettingsView();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
