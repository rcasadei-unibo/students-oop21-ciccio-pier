package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.settings.User;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a custom cell renderer used to make the cells transparent
 * and to properly show the users in the leaderboard
 */
public class TransparentListCellRenderer extends DefaultListCellRenderer {
    private final Level level;

    /**
     * This constructor generates a transparent cell renderer used in the leaderboard
     *
     * @param level the level that will be showed the scores of
     */
    public TransparentListCellRenderer(final Level level) {
        this.level = level;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setOpaque(isSelected);
        if (value instanceof User) {
            User user = (User) value;
            setText(index + 1 + ") " + user.getUsername() + " : " + user.getLevelScore(this.level.getJsonId()));
        }
        return this;
    }
}
