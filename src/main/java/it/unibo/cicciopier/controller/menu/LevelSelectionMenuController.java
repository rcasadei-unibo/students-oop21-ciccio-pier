package it.unibo.cicciopier.controller.menu;

import it.unibo.cicciopier.view.Texture;
import it.unibo.cicciopier.view.menu.LevelSelectionView;
import it.unibo.cicciopier.view.menu.buttons.LeaderboardButton;
import it.unibo.cicciopier.view.menu.buttons.PlayButton;
import it.unibo.cicciopier.view.menu.buttons.QuitButton;
import it.unibo.cicciopier.view.menu.buttons.SettingsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelSelectionMenuController implements MenuController{
    private final MainMenuController menuController;
    private final LevelSelectionView levelSelectionView;

    LevelSelectionMenuController(MainMenuController mainMenuController){
        this.menuController = mainMenuController;
        levelSelectionView = new LevelSelectionView(mainMenuController);

    }
    @Override
    public void show() {
        levelSelectionView.start();
    }

    @Override
    public void hide() {
        levelSelectionView.hideView();
    }


}
