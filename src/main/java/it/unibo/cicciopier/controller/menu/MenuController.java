package it.unibo.cicciopier.controller.menu;

import it.unibo.cicciopier.view.menu.ViewPanels;

import javax.swing.*;

public interface MenuController {

    /**
     * Shows the menu to the player
     */
    void show(ViewPanels viewPanels);

    /**
     *  Hide the view from the player
     */
    void hide();

}
