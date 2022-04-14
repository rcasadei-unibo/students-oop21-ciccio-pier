package it.unibo.cicciopier.model.settings;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CustomFont {
    private static final CustomFont INSTANCE = new CustomFont();
    private Font font;

    public void load() throws IOException, FontFormatException {
        final InputStream is = getClass().getResourceAsStream("/font/font.ttf");
        if (is == null) {
            throw new NullPointerException("Font not found");
        }
        this.font = Font.createFont(Font.TRUETYPE_FONT, is);
    }

    public Font getFontOrDefault() {
        return this.font == null ? Font.getFont("serif").deriveFont(Font.PLAIN, (float) (20 * Screen.getScale())) : this.font.deriveFont(Font.PLAIN, (float) (20 * Screen.getScale()));
    }

    public static CustomFont getInstance() {
        return CustomFont.INSTANCE;
    }
}
