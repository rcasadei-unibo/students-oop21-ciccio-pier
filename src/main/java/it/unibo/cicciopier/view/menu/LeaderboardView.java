package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.view.StaticView;

import javax.swing.*;

public class LeaderboardView extends JPanel implements StaticView {
    private final JFrame jframe = new JFrame();

    public LeaderboardView() {
    }

    public void hideView() {
        this.jframe.setVisible(false);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

    }
}