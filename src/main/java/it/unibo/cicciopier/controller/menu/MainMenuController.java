package it.unibo.cicciopier.controller.menu;


import it.unibo.cicciopier.App;
import it.unibo.cicciopier.view.menu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainMenuController implements MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private int volume;
    private final MenuManagerView menu;

    public MainMenuController() {
        this.menu = new MenuManagerView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show(ViewPanels viewPanels) {
        LOGGER.info("Changing menu view to: " + viewPanels);
        menu.setVisible(viewPanels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {

    }

    public void quitAction() {
        LOGGER.info("Exiting...");
        System.exit(0);
    }


}