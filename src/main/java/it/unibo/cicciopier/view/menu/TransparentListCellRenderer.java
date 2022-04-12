package it.unibo.cicciopier.view.menu;

import it.unibo.cicciopier.model.Level;
import it.unibo.cicciopier.model.User;

import javax.swing.*;
import java.awt.*;

public class TransparentListCellRenderer extends DefaultListCellRenderer {
    private final Level level;

    public TransparentListCellRenderer(final Level level) {
        this.level = level;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setOpaque(isSelected);
        if (value instanceof User) {
            User user = (User) value;
            setText(index+1 + ") " + user.getUsername() + " : " + user.getLevelScore(this.level.getJsonId()));
        }
        return this;
    }
}
